# NexVora Terminal

**NexVora Terminal** is a branded, dark-themed build of the open-source [Termux](https://github.com/termux/termux-app) Android terminal, published by **NexVora Lab's Ofc** and developed by **Prince AR Abdur Rahman**. Display version: **1.0.0**.

> This is a modified version of Termux, licensed under **GPLv3 only**. It is **not** an official Termux release and is not endorsed by the Termux project. See [NEXVORA_NOTICE.md](NEXVORA_NOTICE.md) and [LICENSE.md](LICENSE.md).

## Design decision: application ID stays `com.termux`

Termux's bootstrap and the official package repositories contain binaries with the path `/data/data/com.termux/files/usr` compiled in. Changing the application ID would break `pkg`/`apt` unless you build and host your own bootstrap and repository. To keep the real terminal, `pkg` and `apt` working, this build keeps the upstream application ID and package names and changes only the user-facing identity.

Consequence: it **cannot be installed side by side with, or over, another Termux build signed with a different key** (uninstall that one first; its data will be lost, so back it up).

## What was changed (NexVora modifications)

- App name `NexVora Terminal` (launcher label, settings, notifications) and `versionName 1.0.0` (`versionCode 10000`).
- New launcher icon (adaptive, round, legacy PNG and monochrome themed icon), source PNG in `art/nexvora-icon-512.png`.
- Dark theme with cyan (`#22D3EE`) and violet (`#8B5CF6`) accents for the terminal activity, drawer, session list and extra-keys row. The sessions drawer is always dark.
- Settings → About now starts with NexVora version, developer, publisher and open-source credits (`NexVoraBranding.java`), followed by the original Termux app/device info.
- Sessions drawer buttons restyled (rounded, 48dp touch targets); toolbar background themed.
- Settings → **Open-source licenses** screen (summarizes `LICENSE.md` and `termux-shared/LICENSE.md`).
- GitHub Actions workflow builds and verifies debug APKs.
- This README and `NEXVORA_NOTICE.md`.

Not changed: terminal emulator, native code, shell/session handling, bootstrap download/installation, `pkg`/`apt`, storage setup, package names, build tooling versions.

## Build (GitHub Actions, recommended)

1. Push this project to a GitHub repository (branch `master`), or open **Actions → NexVora Terminal - Debug APK → Run workflow**.
2. The workflow installs JDK 17, the pinned Android platform and NDK (read from `gradle.properties`), builds `assembleDebug` for the `apt-android-7` and `apt-android-5` package variants, verifies the APK (package `com.termux`, label `NexVora Terminal`, version `1.0.0`), and uploads artifacts.
3. Download the artifact `nexvora-terminal_v1.0.0+<commit>-apt-android-7-github-debug_arm64-v8a` (or `_universal`) from the workflow run page, unzip it, and install the `.apk`.

Debug APKs need no signing secrets. For signed builds use the manual workflow **NexVora Terminal - Signed Release APK (manual)**; add the repository secrets `NEXVORA_KEYSTORE_BASE64`, `NEXVORA_KEYSTORE_PASSWORD`, `NEXVORA_KEY_ALIAS`, `NEXVORA_KEY_PASSWORD` first (it fails clearly if any is missing). It has not been run yet.

## Build locally

Requirements: JDK 17, Android SDK with platform 36, NDK `29.0.14206865`, internet access (dependencies and bootstrap zips are downloaded and checksum-verified by `app/build.gradle`).

```bash
export ANDROID_HOME=/path/to/android-sdk
./gradlew assembleDebug
# APKs: app/build/outputs/apk/debug/
```

## Install and first run

1. Allow installing from unknown sources and install the APK (use `arm64-v8a` on most phones; `universal` if unsure).
2. First launch extracts the bootstrap that was embedded into the APK at build time and shows a progress message; no internet is needed for this step.
3. Run `pkg update && pkg upgrade` (internet required), then `termux-setup-storage` for shared-storage access.

## Compatibility

Upstream settings are preserved: `minSdk 21`, `targetSdk 28` (required for Termux's executable-from-data-dir behavior), `compileSdk 36`, NDK `29.0.14206865`. Behavior on Android 14/15 has not been verified by NexVora; see "Verification status".

## Troubleshooting

- **Bootstrap/build download fails:** `app/build.gradle` downloads `bootstrap-<arch>.zip` from `github.com/termux/termux-packages` releases during the build; check the Actions log and re-run.
- **`pkg update` fails:** check internet and run `termux-change-repo`.
- **Storage:** run `termux-setup-storage` and accept the permission prompt.
- **App will not install:** another Termux build with a different signature is installed; uninstall it first.

## Verification status (honest report)

Done in the authoring environment: XML well-formedness, resource reference checks, workflow YAML parse. **Not done there** (no Android SDK/NDK/network/device): Gradle build, native compile, unit tests, APK inspection, and any on-device test of shell, keyboard, sessions, storage and `pkg`. Run the GitHub Actions workflow and test on a device before treating this as release-ready.

## Known limitations

- Application ID/package names remain `com.termux` (see above).
- Default terminal foreground/background/cursor colors were changed to the NexVora palette (3 constants in `TerminalColorScheme.java`); a user's `~/.termux/colors.properties` still overrides them. The 16-color ANSI palette is unchanged.
- About and Licenses are Markdown report screens (existing Termux `ReportActivity`), not custom activities. No new session-management UI beyond restyling.
- Extra-keys default layout is unchanged from upstream (editable in `~/.termux/termux.properties`). Crash-report titles and internal log tags still say "Termux" on purpose, to avoid log-tag length problems on old Android versions.
- The signed release workflow is untested.

## Licenses

GPLv3 only for the Termux app code (see `LICENSE.md`); Apache 2.0 for code derived from Terminal Emulator for Android (`terminal-emulator`, `terminal-view`); see `termux-shared/LICENSE.md` for further exceptions. If you distribute the APK you must provide the corresponding source under GPLv3, keep these notices, and mark your changes. The upstream README is preserved in `docs/UPSTREAM_TERMUX_README.md`.
