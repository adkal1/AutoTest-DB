package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class HelpForm extends Form {
    private final IButton sendButton = getElementFactory().getButton(By.xpath("//button[contains(@class, 'help-form__send')]"), "HelpFormSendButton");

    public HelpForm() {
        super(By.xpath("//div[@class='help-form']"), "HelpForm");
    }

    public void clickSendButton() {
        sendButton.clickAndWait();
    }
}