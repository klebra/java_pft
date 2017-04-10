package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTest extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        app.getContactsHelper().goToHomePage();
        if (!app.getContactsHelper().isContactPresent()){
            app.getContactsHelper().createContact(new ContactData("Name", null, null, null, null));
        }
    }

    @Test
    public void testContactDeletion(){
        List<ContactData> before = app.getContactsHelper().getContactList();
        int index = before.size() - 1;
        app.getContactsHelper().deleteContact(index);
        List<ContactData> after = app.getContactsHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }
}
