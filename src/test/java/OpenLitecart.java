
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class OpenLitecart extends TestBase {

    @Test
    public void testOpenLitecartAdmin() throws InterruptedException {
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//span[contains(text(),'Appearence')]")).click();
        driver.findElement(By.xpath("//h1[contains(text(),'Template')]"));
        Thread.sleep(5000);
    }

    @Test
    public void checkStickers() throws InterruptedException {
        driver.get("http://localhost/litecart/");
        Thread.sleep(5000);
        System.out.println(areElementsPresent(driver, By.xpath("//div[@id='box-latest-products']")));
        driver.findElement(By.xpath("//span[contains(text(),'Appearence')]")).getAttribute("textContent");
    }


    boolean isElementPresent(WebDriver driver, By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    boolean areElementsPresent(WebDriver driver, By by) {
        return driver.findElements(by).size() > 0;
    }
}
