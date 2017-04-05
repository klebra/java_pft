package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactEditingTest extends TestBase{

    @Test
    public void testContactEditing(){
        app.getNavigationHelper().goToHomePage();
        List<ContactData> before = app.getContactsHelper().getContactList();
        if (!app.getContactsHelper().isContactPresent()){
            app.getContactsHelper().createContact(new ContactData("Name", null, null, null, null));
        }
        app.getContactsHelper().selectContact(before.size() - 1);
        ContactData contact = new ContactData("emaN", "emaNtsaL", "sserddA", "123456789", "addr@mail.some");
        app.getContactsHelper().fillContactForm(contact);
        app.getNavigationHelper().clickOnUpdate();
        app.getContactsHelper().clickOnHomePage();
        List<ContactData> after = app.getContactsHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
