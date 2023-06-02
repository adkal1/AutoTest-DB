package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class CookiesForm extends Form {
    private final IButton acceptCookiesButton = getElementFactory().getButton(By.xpath("//button[contains(@class, 'transparent')]"), "AcceptCookiesButton");

    public CookiesForm() {
        super(By.xpath("//div[@class='cookies']"), "CookiesForm");
    }

    public void clickAcceptCookiesButton() {
        acceptCookiesButton.clickAndWait();
    }
}