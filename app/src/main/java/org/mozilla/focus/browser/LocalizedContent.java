/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.focus.browser;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.webkit.WebView;

import org.mozilla.focus.R;
import org.mozilla.focus.locale.Locales;
import org.mozilla.focus.utils.HtmlLoader;
import org.mozilla.focus.utils.SupportUtils;

import java.util.Map;

public class LocalizedContent {
    // We can't use "about:" because webview silently swallows about: pages, hence we use
    // a custom scheme.
    public static final String URL_ABOUT = "cat:about";
    public static final String URL_APPS = "cat:apps";
    public static final String URL_MAKER = "cat:maker";
    public static final String URL_NET = "cat:net";
    public static final String URL_TALK = "cat:talk";
    public static final String URL_RIGHTS = "cat:rights";

    public static boolean handleInternalContent(String url, WebView webView) {
        if (URL_ABOUT.equals(url)) {
            loadAbout(webView);
            return true;
        } else if (URL_APPS.equals(url)) {
            loadApps(webView);
            return true;
        } else if (URL_MAKER.equals(url)) {
            loadMaker(webView);
            return true;
        } else if (URL_NET.equals(url)) {
            loadNet(webView);
            return true;
        } else if (URL_TALK.equals(url)) {
            loadTalk(webView);
            return true;
        } else if (URL_RIGHTS.equals(url)) {
            loadRights(webView);
            return true;
        }

        return false;
    }

    /**
     * Load the content for cat:apps
     */
    private static void loadAbout(@NonNull final WebView webView) {
        final Context context = webView.getContext();
        final Resources resources = Locales.getLocalizedResources(context);

        final Map<String, String> substitutionMap = new ArrayMap<>();
        final String appName = context.getResources().getString(R.string.app_name);
        final String learnMoreURL = SupportUtils.getManifestoURL();

        String aboutVersion = "";
        try {
            final PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            aboutVersion = String.format("%s (Build #%s)", packageInfo.versionName, packageInfo.versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            // Nothing to do if we can't find the package name.
        }
        substitutionMap.put("%about-version%", aboutVersion);

        final String aboutContent = resources.getString(R.string.about_content, appName, learnMoreURL);
        substitutionMap.put("%about-content%", aboutContent);

        final String wordmark = HtmlLoader.loadPngAsDataURI(context, R.drawable.wordmark);
        substitutionMap.put("%wordmark%", wordmark);

        putLayoutDirectionIntoMap(webView, substitutionMap);

        final String data = HtmlLoader.loadResourceFile(context, R.raw.about, substitutionMap);
        // We use a file:/// base URL so that we have the right origin to load file:/// css and image resources.
        webView.loadDataWithBaseURL("file:///android_res/raw/about.html", data, "text/html", "UTF-8", null);
    }


    /**
     * Load the content for cat:apps
     */
    private static void loadApps(@NonNull final WebView webView) {
        webView.loadUrl("http://localhost:8088/apps");
    }

    /**
     * Load the content for cat:maker
     */
    private static void loadMaker(@NonNull final WebView webView) {
        webView.loadUrl("http://localhost:8088/maker");
    }

    /**
     * Load the content for cat:net
     */
    private static void loadNet(@NonNull final WebView webView) {
        webView.loadUrl("http://localhost:8088/net");
    }

    /**
     * Load the content for cat:talk
     */
    private static void loadTalk(@NonNull final WebView webView) {
        webView.loadUrl("http://localhost:8088/talk");
    }

    /**
     * Load the content for cat:rights
     */
    private static void loadRights(@NonNull final WebView webView) {
        final Context context = webView.getContext();
        final Resources resources = Locales.getLocalizedResources(context);

        final Map<String, String> substitutionMap = new ArrayMap<>();

        final String appName = context.getResources().getString(R.string.app_name);
        final String mplUrl = "https://www.mozilla.org/en-US/MPL/";
        final String trademarkPolicyUrl = "https://www.mozilla.org/foundation/trademarks/policy/";
        final String gplUrl = "gpl.html";
        final String trackingProtectionUrl = "https://wiki.mozilla.org/Security/Tracking_protection#Lists";
        final String licensesUrl = "licenses.html";

        final String content1 = resources.getString(R.string.your_rights_content1, appName);
        substitutionMap.put("%your-rights-content1%", content1);

        final String content2 = resources.getString(R.string.your_rights_content2, appName, mplUrl);
        substitutionMap.put("%your-rights-content2%", content2);

        final String content3 = resources.getString(R.string.your_rights_content3, appName, trademarkPolicyUrl);
        substitutionMap.put("%your-rights-content3%", content3);

        final String content4 = resources.getString(R.string.your_rights_content4, appName, licensesUrl);
        substitutionMap.put("%your-rights-content4%", content4);

        final String content5 = resources.getString(R.string.your_rights_content5, appName, gplUrl, trackingProtectionUrl);
        substitutionMap.put("%your-rights-content5%", content5);

        putLayoutDirectionIntoMap(webView, substitutionMap);

        final String data = HtmlLoader.loadResourceFile(context, R.raw.rights, substitutionMap);
        // We use a file:/// base URL so that we have the right origin to load file:/// css and image resources.
        webView.loadDataWithBaseURL("file:///android_asset/rights.html", data, "text/html", "UTF-8", null);
    }

    private static void putLayoutDirectionIntoMap(WebView webView, Map<String, String> substitutionMap) {
        ViewCompat.setLayoutDirection(webView, View.LAYOUT_DIRECTION_LOCALE);
        final int layoutDirection = ViewCompat.getLayoutDirection(webView);

        final String direction;

        if (layoutDirection == View.LAYOUT_DIRECTION_LTR) {
            direction = "ltr";
        } else if (layoutDirection == View.LAYOUT_DIRECTION_RTL) {
            direction = "rtl";
        } else {
            direction = "auto";
        }

        substitutionMap.put("%dir%", direction);
    }
}
