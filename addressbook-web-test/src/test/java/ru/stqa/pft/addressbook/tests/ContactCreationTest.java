package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase{

    @Test
    public void testContactCreation() {
        app.getContactsHelper().clickOnAddContact();
        app.getContactsHelper().fillContactForm(new ContactData("Name", "LastName", "Adress", "987654321", "some@mail.addr"));
        app.getContactsHelper().submitContactCreation();
        app.getContactsHelper().clickOnHomePage();
    }

}