![CatOS](https://github.com/kcallow/CatOS/raw/master/logo.png "CatOS Logo") 
# CatOS: _A Distributed Web App Ecosystem_
### Productivity.  Creativity.  Freedom.

CatOS = Unix + NodeJS + Browser

This project is the browser part of CatOS, for Android. Thanks Mozilla people for the quality code! (^.^)

<a href="https://github.com/kcallow/catos-android/blob/master/apk/CatOS_0.1.9.apk" target="_blank"><img src="git-it.png" alt="Get it on GitHub" height="70%"/></a>


Build instructions
------------------

0. Install Android Studio. _Or be a command line hacker like me! ;)_

1. Clone the repository:

  ```shell
  git clone https://github.com/kcallow/catos-android
  ```

2. Import the project into Android Studio or build on the command line:

  ```shell
  ./gradlew clean app:assembleFocusWebviewDebug
  ```

3. Make sure to select the right build variant in Android Studio: **focusWebviewDebug**

License
-------

    This Source Code Form is subject to the terms of the Mozilla Public
    License, v. 2.0. If a copy of the MPL was not distributed with this
    file, You can obtain one at http://mozilla.org/MPL/2.0/
