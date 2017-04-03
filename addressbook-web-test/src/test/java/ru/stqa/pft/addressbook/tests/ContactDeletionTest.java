package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTest extends TestBase{

    @Test
    public void testContactDeletion(){
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactsHelper().isContactPresent()){
            app.getContactsHelper().createContact(new ContactData("Name", null, null, null, null));
        }
        app.getNavigationHelper().selectCheckbox(0);
        app.getContactsHelper().deleteContact();
        app.getNavigationHelper().acceptAlert();
    }
}
