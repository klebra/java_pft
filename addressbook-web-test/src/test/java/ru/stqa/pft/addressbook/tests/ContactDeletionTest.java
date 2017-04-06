package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTest extends TestBase{

    @Test
    public void testContactDeletion(){
        app.getNavigationHelper().goToHomePage();
        List<ContactData> before = app.getContactsHelper().getContactList();
        if (!app.getContactsHelper().isContactPresent()){
            app.getContactsHelper().createContact(new ContactData("Name", null, null, null, null));
        }
        app.getNavigationHelper().selectCheckbox(before.size() - 1);
        app.getContactsHelper().deleteContact();
        app.getNavigationHelper().acceptAlert();
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactsHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}
