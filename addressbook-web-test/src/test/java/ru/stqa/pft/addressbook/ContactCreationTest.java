package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTest extends TestBase{

    @Test
    public void testContactCreation() {
        clickOnAddContact();
        fillContactForm(new ContactData("Name", "LastName", "Adress", "987654321", "some@mail.addr"));
        submitContactCreation();
        clickOnHomePage();
    }

}
