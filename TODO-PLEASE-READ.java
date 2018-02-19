/*
Hello, fellow CatOS hacker~
I'm almost done with this lovely GUI based on Firefox Focus mobile browser.
But the buttons are not connected to the code! Java code is so hard for me 
that I dropped out of college! Any help is much appreciated. 

For loading apps, maker, net, talk we need the function loadUrl, located in 
	app/src/main/java/org/mozilla/focus/fragment/BrowserFragment.java

Please, kindly make the buttons in file:
	app/src/main/res/layout/buttons.xml 
run the following (Kotlin) functions:
*/

import org.mozilla.focus.fragment.BrowserFragment.*

    fun openApps(view: View) {
        loadUrl("cat:apps")
    }

    fun openMaker(view: View) {
        loadUrl("cat:maker")
    }

    fun openNet(view: View) {
        loadUrl("cat:net")
    }

    fun openTalk(view: View) {
        loadUrl("cat:talk")
    }

/*
Useful reference files:
main/java/org/mozilla/focus/fragment/BrowserFragment.java
main/java/org/mozilla/focus/fragment/UrlInputFragment.kt
main/java/org/mozilla/focus/browser/LocalizedContent.java

Less useful but possibly insightful:
main/java/org/mozilla/focus/fragment/WebFragment.java
webview/java/org/mozilla/focus/webview/SystemWebView.java
*/ // MAY THE CAT BE WITH YOU
//                       -kc
