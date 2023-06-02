package pages;

import utilits.RandomGenerateUtil;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.core.elements.ElementsCount;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.util.List;

import static pages.GamePage.uniqueLocatorPage;

public class AvatarInterestsPage extends Form {
    public AvatarInterestsPage() {
        super(uniqueLocatorPage(2), "AvatarAndInterestsPage");
    }

    private final IButton downloadImageButton = getElementFactory().getButton(By.xpath("//button[contains(@class, 'upload-button')]"), "DownloadImageButton");
    private final ILink uploadImageLink = getElementFactory().getLink(By.xpath("//a[contains(@class, 'upload-button')]"), "UploadImageLink");
    private final ICheckBox unselectAllCheckBox = getElementFactory().getCheckBox(By.xpath("//label[@for='interest_unselectall']"), "UnselectAllCheckBox");
    private final ILink imageLink = getElementFactory().getLink(By.xpath("//div[contains(@class, 'avatar-image')]"),"ImageLink");

    public void clickUploadImage() {
        uploadImageLink.clickAndWait();
    }

    public void checkRandomCheckBoxes(String max_num) {
        List<ICheckBox> checkBoxesList = getElementFactory().findElements(By.xpath("//span[@class='checkbox small']"), ElementType.CHECKBOX, ElementsCount.MORE_THEN_ZERO, ElementState.DISPLAYED);
        List<ICheckBox> checkBoxesLabelList = getElementFactory().findElements(By.xpath("//div[contains(@class, 'interests-list')]"), ElementType.CHECKBOX, ElementsCount.MORE_THEN_ZERO, ElementState.DISPLAYED);

        int[] listOfNumbers = RandomGenerateUtil.randomListOfNumbersInterest(checkBoxesLabelList, Integer.parseInt(max_num));

        for (int num : listOfNumbers) {
            checkBoxesList.get(num).check();
        }
    }

    public void uncheckAllCheckBoxes() {
        unselectAllCheckBox.check();
    }

    public void clickDownloadImage() {
        downloadImageButton.clickAndWait();
    }
    public void waitUploadImage() {
        imageLink.state().waitForDisplayed();
    }
}
