import org.openqa.selenium.By;

public class Locators extends TestBase {
    public static final By APPEARENCE = By.xpath("//span[contains(text(),'Appearence')]");
    public static final By TEMAPLATE = By.xpath("//h1[contains(text(),'Template')]");
    public static final By GEOZONES = By.xpath("//span[text()='Geo Zones']");
    public static final By LOGOTYPE = By.xpath("//span[text()='Logotype']");
    public static final By CATALOG = By.xpath("//span[text()='Catalog']");
    public static final By PRODUCT_GROUPS = By.xpath("//span[@class='name' and text()='Product Groups']");
    public static final By COUNTRIES = By.xpath("//span[contains(text(), 'Countries')]");

    public static final By HEADER = By.xpath("//h1[starts-with(@style,'margin-top')]");

    public static final By STICKER = By.xpath("//img[contains(@class,'sticker')]");
}
