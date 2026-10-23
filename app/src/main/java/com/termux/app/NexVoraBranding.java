package com.termux.app;

import android.content.Context;
import android.content.pm.PackageInfo;

import androidx.annotation.NonNull;

/**
 * NexVora Terminal branding and attribution text.
 *
 * Only user-facing text lives here. The application id, package names and the terminal
 * engine are intentionally left as upstream Termux so that the official bootstrap and
 * package repositories keep working.
 */
public final class NexVoraBranding {

    public static final String APP_NAME = "NexVora Terminal";
    public static final String DEVELOPER = "Prince AR Abdur Rahman";
    public static final String PUBLISHER = "NexVora Lab's Ofc";
    public static final String DISPLAY_VERSION = "1.0.0";

    private NexVoraBranding() {}

    /** Version without build metadata (the CI appends "+<commit>"). */
    @NonNull
    public static String getDisplayVersion(@NonNull Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            if (info.versionName != null) {
                int plus = info.versionName.indexOf('+');
                return plus > 0 ? info.versionName.substring(0, plus) : info.versionName;
            }
        } catch (Exception ignored) {
        }
        return DISPLAY_VERSION;
    }

    /** Markdown shown at the top of Settings -> About. */
    @NonNull
    public static String getAboutMarkdown(@NonNull Context context) {
        return "## " + APP_NAME + "\n\n" +
            "**Version:** " + getDisplayVersion(context) + "  \n" +
            "**Developer:** " + DEVELOPER + "  \n" +
            "**Publisher:** " + PUBLISHER + "\n\n" +
            "### Open-source credits\n\n" +
            APP_NAME + " is a modified version of [Termux](https://github.com/termux/termux-app), " +
            "which is released under the GNU GPL v3 (GPLv3 only). " +
            "Termux and its contributors own the upstream code; NexVora Lab's Ofc only authored " +
            "the branding, theme, icon and documentation changes listed in `NEXVORA_NOTICE.md`.\n\n" +
            "- Terminal emulator code derived from [Terminal Emulator for Android]" +
            "(https://github.com/jackpal/Android-Terminal-Emulator) (Apache 2.0)\n" +
            "- Other libraries keep their own licenses; see `LICENSE.md` and `termux-shared/LICENSE.md` " +
            "in the source repository.\n\n" +
            "This build uses the official Termux bootstrap and package repositories. " +
            "It is not an official Termux release and is not endorsed by the Termux project.";
    }

    /** Markdown for Settings -> Open-source licenses. Mirrors LICENSE.md and termux-shared/LICENSE.md. */
    @NonNull
    public static String getLicensesMarkdown() {
        return "## Open-source licenses\n\n" +
            APP_NAME + " is distributed under the **GNU GPL v3 only**, like the upstream " +
            "[Termux app](https://github.com/termux/termux-app). The corresponding source code is " +
            "available from the repository this APK was built from.\n\n" +
            "### Components with other licenses\n\n" +
            "- `terminal-emulator` and `terminal-view`: code from " +
            "[Terminal Emulator for Android](https://github.com/jackpal/Android-Terminal-Emulator), " +
            "**Apache 2.0**.\n" +
            "- `termux-shared`: **MIT**, except `shared/termux/*` (**GPLv3 only**), " +
            "`shared/file/filesystem/*` (**GPLv2 with Classpath exception**, code from libcore/ojluni) " +
            "and `StreamGobbler.java` (**Apache 2.0**, code from libsuperuser).\n\n" +
            "### Third-party libraries\n\n" +
            "The app also uses AndroidX, Material Components, Guava, Markwon, commons-io, " +
            "HiddenApiBypass and termux-am-library. Each remains under its own license; " +
            "see the upstream projects for their terms.\n\n" +
            "### Linux packages\n\n" +
            "Packages installed with `pkg`/`apt` come from the Termux repositories and carry " +
            "their own licenses (see `/data/data/com.termux/files/usr/share/doc`).\n\n" +
            "### NexVora modifications\n\n" +
            "Branding, theme, icon and documentation changes by " + PUBLISHER + " are released " +
            "under the same GPLv3 terms. See `NEXVORA_NOTICE.md`.";
    }
}
