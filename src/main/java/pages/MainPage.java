package pages;

import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MainPage extends Form {
    public MainPage() {
        super(By.xpath("//div[@class='logo']"), "HomePage");
    }

    private final ILink linkStart = getElementFactory().getLink(By.xpath("//a[@class='start__link']"), "StartLink");

    public void clickStartLink() {
        linkStart.clickAndWait();
    }
}