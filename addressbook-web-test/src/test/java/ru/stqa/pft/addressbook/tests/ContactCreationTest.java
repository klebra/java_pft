package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase{

    @Test
    public void testContactCreation() {
        app.getContactsHelper().clickOnAddContact();
        app.getContactsHelper().fillContactForm(new ContactData("Name", null, null, null, null));
        app.getContactsHelper().submitContactCreation();
        app.getContactsHelper().clickOnHomePage();
    }

}
