
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class OpenLitecart extends TestBase {

    @Test
    public void testOpenLitecart() {
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }


    @Test
    boolean isElementPresent(WebDriver driver, By by) {
        try{
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException ex){
            return false;
        }
    }

    @Test
    boolean isElementPresentShort (WebDriver driver, By by) {
        return driver.findElements(by).size()>0;
    }
}
