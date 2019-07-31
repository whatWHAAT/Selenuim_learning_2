import com.google.common.io.LittleEndianDataInputStream;
import com.sun.jdi.LocalVariable;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LitecartTests extends TestBase {

    @Test
    public void addNewProduct() {
        loginToAdminPart();
        driver.findElement(Locators.CATALOG).click();
        driver.findElement(Locators.ADD_NEW_PRODUCT).click();
        driver.findElement(By.name("name[en]")).sendKeys("Pikachu");
        driver.findElement(By.name("code")).sendKeys("1337");
        driver.findElement(By.xpath("//input[@data-name='Weirdo']")).isSelected();
        driver.findElement(By.xpath("//td[contains(text(),'Unisex')]/..//input")).isSelected();
        driver.findElement(By.name("quantity")).sendKeys("1337");
        new Select(driver.findElement(By.name("sold_out_status_id"))).selectByVisibleText("-- Select --");
        new WebDriverWait(driver, 5);

//        driver.switchTo().frame(
                driver.findElement(By.name("date_valid_from")).click();
                driver.switchTo().frame("iframe");
        SetDatepicker(driver, "#datepicker", "02/20/2002");
        new WebDriverWait(driver, 5);

    }

    public void SetDatepicker(WebDriver driver, String cssSelector, String date)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        new WebDriverWait(driver, 30).until(
                d -> driver.findElement(By.cssSelector(cssSelector)).isDisplayed());
        (js).executeScript(
            String.format("$('{0}').datepicker('setDate', '{1}')", cssSelector, date));
    }

    @Test
    public void testRegistrationAndLogin() throws InterruptedException {
        int rand = (int) (Math.random()*1000000);
        String password = "password";
        String email = "email" + rand + "@yandex.ru";

        driver.navigate().to("http://localhost/litecart/en/");
        driver.findElement(By.linkText("New customers click here")).click();
        driver.findElement(By.name("firstname")).sendKeys("FirstName");
        driver.findElement(By.name("lastname")).sendKeys("LastName");
        driver.findElement(By.name("address1")).sendKeys("Address1");
        driver.findElement(By.name("postcode")).sendKeys("Postcode");
        driver.findElement(By.name("city")).sendKeys("City");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("confirmed_password")).sendKeys(password);
        new Select(driver.findElement(By.name("country_code"))).selectByVisibleText("United States");
        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.findElement(By.name("create_account")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(Locators.LOGOUT_BUTTON));
        Thread.sleep(5000);
        driver.findElement(By.xpath("//div[@class='content']//a[contains(text(),'Logout')]")).click();

        loginToStore(password, email, wait);
        logout();
    }

    @Test
    public void clickOnLabelsAndCheckheaders() {
        loginToAdminPart();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(Locators.APPEARENCE).click();
        String labelText = driver.findElement(Locators.TEMAPLATE).getText();
        String headerText = driver.findElement(Locators.HEADER).getText();
        Assert.assertEquals(labelText, headerText);
        driver.findElement(Locators.TEMAPLATE).click();
        driver.findElement(Locators.HEADER);
        driver.findElement(Locators.LOGOTYPE).click();
        String x = driver.findElement(Locators.HEADER).getAttribute("style");
        System.out.println(x);
    }

    @Test
    public void checkStickers() throws InterruptedException {
        String password = "password";
        String email = "admin@admin.admin";
        loginToStore(password, email, wait);
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





    public void checkNumberOfGeoZones() {
        var list = driver.findElements(By.xpath("//tr[@class='odd' or @class='even']//td[5]")).stream();
        list.forEach(number -> {
            if (Integer.parseInt(number.getAttribute("textContent")) != 0) {
                System.out.println(number.getAttribute("parentElement"));
            }
        });
    }
}
