# FOR APPICATION SIDE

```
private fun setUpEnvironment(): AndroidDriver {
    println("Set Up Environment!")
    val options = UiAutomator2Options()
    options.setDeviceName("215MCD824378")
    options.setPlatformName("Android")
    options.setPlatformVersion("10")
    options.setAppPackage("com.ingenico.ctap.app.main")
    options.setAppActivity("com.ingenico.app.main.MainActivity") // Appium cố gắng khởi chạy một Activity không được khai báo là "exported" trong AndroidManifest
    options.setAutoGrantPermissions(true)
    //prevent Appium from clearing the app’s data (i.e., database)
    options.setNoReset(true)
    options.setFullReset(false)
    return AndroidDriver(URL("http://127.0.0.1:4723"), options)
}
```

# FOR INSPECTOR SIDE

```
{
  "platformName": "Android",
  "appium:deviceName": "215MCD824378",
  "appium:automationName": "UiAutomator2",
  "appium:platformVersion": "10",
  "appium:appPackage": "com.ingenico.ctap.app.main",
  "appium:appWaitActivity": "*", // nên bỏ
  "appium:appActivity": "com.ingenico.ctap.uikit.impl.view.CtapLauncherActivity",
  "appium:noReset": true
}
```

# HOW TO GET INFO

- deviceName - can get using command **adb devices**
- platformName - can get using command **appium driver list**
- platformVersion - can get using command **adb shell getprop ro.build.version.release**
  - If you have multiple devices connected first get the device id using adb devices then run command **adb -s DEVICE_ID shell getprop ro.build.version.release**
- **adb shell dumpsys window | grep -E 'mCurrentFocus'** OR **adb shell dumpsys window | findstr "mCurrentFocus"** -> check appPackage & appActivity

---
- appium driver install uiautomator2
- appium driver list --installed