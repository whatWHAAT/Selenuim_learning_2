import com.google.common.io.LittleEndianDataInputStream;
import com.sun.jdi.LocalVariable;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
        driver.findElement(By.name("z")).getAttribute("value");

    }


    @Test
    public void testOrder() {
        loginToAdminPart();
        driver.findElement(Locators.COUNTRIES).click();
        Assert.assertTrue(getOrder());
    }


    public boolean getOrder() {
        ArrayList<String> obtainedList = new ArrayList<>();
        List<WebElement> elementList = driver
                .findElements(By.xpath("//form[@name='countries_form']//*[@class='odd' or @class='even']//a[contains(@href,'') and not(@title='Edit')]"));
        for (WebElement we : elementList) {
            obtainedList.add(we.getText());
        }
        System.out.println(obtainedList);

        String previous = ""; // empty string: guaranteed to be less than or equal to any other

        for (final String current : obtainedList) {
            if (current.compareTo(previous) < 0)
                return false;
            System.out.println(current);
            previous = current;
        }
        return true;
    }

    @Test
    public void testZones() {
        loginToAdminPart();
        driver.findElement(Locators.COUNTRIES).click();
        checkNumberOfGeoZones();
    }


    public void checkNumberOfGeoZones() {
        var list = driver.findElements(By.xpath("//tr[@class='odd' or @class='even']//td[5]")).stream();
        list.forEach(number -> {
            if (Integer.parseInt(number.getAttribute("textContent")) != 0) {
                System.out.println(number.getAttribute("parentElement"));
            }
        });
    }
}
