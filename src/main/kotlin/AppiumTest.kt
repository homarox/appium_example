import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.options.UiAutomator2Options
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.net.URL
import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

const val DELAY_TXN = 1000L
const val MAX_TXN = 300
const val DEFAULT_DURATION = 30L

fun main() {

    val driver = setUpEnvironment()

    try {
        println("App launched!")
        var count = 1
        while (count < MAX_TXN) {
            countingTxn(count)

            val wait = WebDriverWait(driver, Duration.ofSeconds(DEFAULT_DURATION))
            amountScreen(wait)
            tearOffReceipt(wait)

            Thread.sleep(DELAY_TXN)
            count++
        }
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        driver.quit()
    }
}

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

private fun countingTxn(count: Int = 0){
    val millis = System.currentTimeMillis()

    val readable = Instant.ofEpochMilli(millis)
        .atZone(ZoneId.systemDefault()) // e.g., ZoneId.of("UTC")
        .format(DateTimeFormatter.ofPattern("MM-dd HH:mm:ss"))

    println("Start Transaction: $count - Current Time: $readable")
}

private fun amountScreen(wait: WebDriverWait) {
    val sixBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.ingenico.ctap.app.main:id/btnSix")))
    sixBtn.click()

    val enterBtn =
        wait.until(ExpectedConditions.elementToBeClickable(By.id("com.ingenico.ctap.app.main:id/button_enter")))
//    println("enterBtn amountScreen")
    enterBtn.click()
}

private fun tearOffReceipt(wait: WebDriverWait) {
//    println("okBtn merchant")
    var okBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.ingenico.ctap.app.main:id/btnDonePrint")))
    okBtn.click()

//    println("okBtn client")
    okBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.ingenico.ctap.app.main:id/btnDonePrint")))
    okBtn.click()
}