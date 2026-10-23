# NexVora Terminal - Notice

NexVora Terminal is derived from Termux (https://github.com/termux/termux-app), Copyright (c) the Termux developers, licensed under GPLv3 only. All upstream copyright and license notices are retained. NexVora Lab's Ofc and Prince AR Abdur Rahman claim no ownership of upstream code.

## Original NexVora modifications

- `app/build.gradle`: display app name placeholder, versionName/versionCode.
- `app/src/main/res/values/strings.xml`, `termux-shared/src/main/res/values/strings.xml`: `TERMUX_APP_NAME` entity.
- `app/src/main/res/values/colors.xml`, `values/themes.xml`, `values-night/themes.xml`, and drawables (`current_session_black`, `session_ripple_black`, `terminal_scroll_shape`): theme.
- Launcher icons: `mipmap-*`, `drawable/ic_foreground.xml`, `ic_launcher_background.xml`, `ic_monochrome.xml`, `art/nexvora-icon-512.png`.
- `app/src/main/java/com/termux/app/NexVoraBranding.java` (new), `SettingsActivity.java` (About text), `TermuxSessionsListViewController.java` (always-dark drawer).
- `.github/workflows/debug_build.yml`, `attach_debug_apks_to_release.yml`: APK naming, SDK/NDK install, APK verification.
- `terminal-emulator/.../TerminalColorScheme.java`: three default color constants (foreground, background, cursor) only.
- `.github/workflows/nexvora_release_build.yml` (new), `fastlane/metadata` text.
- `activity_termux.xml` (drawer buttons, toolbar color), `root_preferences.xml`, `strings.xml` (Licenses entry), `SettingsActivity.java` (Licenses handler).
- `README.md`, this file.

Modifications are distributed under the same GPLv3 terms. Distributors must supply the corresponding source code.
