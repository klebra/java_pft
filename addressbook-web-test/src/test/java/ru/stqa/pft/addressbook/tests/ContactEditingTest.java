package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactEditingTest extends TestBase{

    @Test
    public void testContactEditing(){
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactsHelper().isContactPresent()){
            app.getContactsHelper().createContact(new ContactData("Name", null, null, null, null));
        }
        app.getContactsHelper().editContact();
        app.getContactsHelper().fillContactForm(new ContactData("emaN", "emaNtsaL", "sserddA", "123456789", "addr@mail.some"));
        app.getNavigationHelper().clickOnUpdate();
        app.getContactsHelper().clickOnHomePage();
    }
}
