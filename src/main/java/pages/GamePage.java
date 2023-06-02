package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class GamePage extends Form {
    public static LoginPage loginPage = new LoginPage();
    public static MainPage mainPage = new MainPage();
    public static AvatarInterestsPage avatarInterestsPage = new AvatarInterestsPage();
    public static PersonalDetailsPage personalDetailsPage = new PersonalDetailsPage();
    public static HelpForm helpForm = new HelpForm();
    public static CookiesForm cookiesForm = new CookiesForm();
    private final IButton nextButton = getElementFactory().getButton(By.xpath("//*[contains(text(), 'Next')]"), "NextButton");
    private final ILabel timerLabel = getElementFactory().getLabel(By.xpath("//div[contains(@class, 'timer--white')]"), "TimerLabel");

    public GamePage() {
        super(By.xpath("//div[contains(@class, 'timer--white')]"), "GamePage");
    }

    public void clickNextButton() {
        nextButton.clickAndWait();
    }

    public String getTextFromTimerLabel() {
        return timerLabel.getText();
    }

    public static By uniqueLocatorPage(Integer i) {
        StringBuilder xpathLocator = new StringBuilder("//div[contains(text(), ' / 4')]");
        return By.xpath(String.valueOf(xpathLocator.insert(24, i.toString())));
    }
}
