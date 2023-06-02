package pages;

import aquality.selenium.forms.Form;

import static pages.GamePage.uniqueLocatorPage;

public class PersonalDetailsPage extends Form {
    public PersonalDetailsPage() {
        super(uniqueLocatorPage(3), "PersonalDetailsPage");
    }
}