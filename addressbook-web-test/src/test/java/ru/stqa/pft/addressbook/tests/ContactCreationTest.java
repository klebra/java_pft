package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.ContactData;

public class ContactCreationTest extends TestBase{

    @Test
    public void testContactCreation() {
        app.clickOnAddContact();
        app.fillContactForm(new ContactData("Name", "LastName", "Adress", "987654321", "some@mail.addr"));
        app.submitContactCreation();
        app.clickOnHomePage();
    }

}
