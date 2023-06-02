package pages;

import utilits.RandomGenerateUtil;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.core.elements.ElementsCount;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Random;

import static pages.GamePage.uniqueLocatorPage;

public class LoginPage extends Form {
    public LoginPage() {
        super(uniqueLocatorPage(1), "LoginForm");
    }

    private final ITextBox passwordBox = getElementFactory().getTextBox(By.xpath("//div[@class='login-form__field-row']/input"), "PasswordField");
    private final ITextBox loginBox = getElementFactory().getTextBox(By.xpath("//input[contains(@placeholder,'email')]"), "LoginField");
    private final ITextBox domainBox = getElementFactory().getTextBox(By.xpath("//input[contains(@placeholder,'Domain')]"), "DomainField");
    private final IComboBox dropDownList = getElementFactory().getComboBox(By.xpath("//div[@class='dropdown dropdown--gray']"), "DropDownHeader");
    private final ICheckBox acceptCheckBox = getElementFactory().getCheckBox(By.xpath("//span[@class='checkbox__box']"), "AcceptTermsCheckBox");
    private static String loginText;

    public void setLoginField() {
        loginText = RandomGenerateUtil.generateEmail();
        loginBox.clearAndType(loginText);
    }

    public void setPasswordField() {
        passwordBox.clearAndType(RandomGenerateUtil.generatePassword(loginText));
    }

    public void setDomainField() {
        domainBox.clearAndType(RandomGenerateUtil.generateEmail());
    }

    public void clickComboBox() {
        Random rand = new Random();
        dropDownList.click();
        List<IButton> dropDownElements = getElementFactory().findElements(By.xpath("//div[@class='dropdown__list-item']"), ElementType.BUTTON, ElementsCount.MORE_THEN_ZERO, ElementState.DISPLAYED);
        dropDownElements.get(rand.nextInt(dropDownElements.size() - 1)).click();
    }

    public void selectAcceptCheckBox() {
        acceptCheckBox.check();
    }
}
