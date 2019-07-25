import com.sun.jdi.LocalVariable;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class LitecartTests extends TestBase {

    @Test
    public void clickOnLabelsAndCheckheaders() {
        loginToAdminPart();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(Locators.APPEARENCE).click();
//        String labelText = driver.findElement(Locators.TEMAPLATE).getText();
//        String headerText = driver.findElement(Locators.HEADER).getText();
//        Assert.assertEquals(labelText, headerText);
//        driver.findElement(Locators.TEMAPLATE).click();
//        driver.findElement(Locators.HEADER);
        driver.findElement(Locators.LOGOTYPE).click();
        String x = driver.findElement(Locators.HEADER).getAttribute("style");
        System.out.println(x);
    }

    @Test
    public void checkStickers() throws InterruptedException {
        loginToStore();
        System.out.println(driver.findElements(Locators.STICKER).toString());
        Thread.sleep(5000);

    }
}
