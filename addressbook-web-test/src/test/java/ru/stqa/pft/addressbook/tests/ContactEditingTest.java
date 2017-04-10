package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactEditingTest extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        app.getContactsHelper().goToHomePage();
        if (!app.getContactsHelper().isContactPresent()){
            app.getContactsHelper().createContact(new ContactData("Name", null, null, null, null));
        }
    }

    @Test
    public void testContactEditing(){
        List<ContactData> before = app.getContactsHelper().getContactList();
        ContactData contact = new ContactData("emaN", "emaNtsaL", "sserddA", "123456789", "addr@mail.some");
        int index = before.size() - 1;
        app.getContactsHelper().modifyContact(contact, index);
        List<ContactData> after = app.getContactsHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
