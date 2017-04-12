package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactEditingTest extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        app.contact().homePage();
        if (app.contact().all().size() == 0){
            app.contact().create(new ContactData().withName("Name"));
        }
    }

    @Test
    public void testContactEditing(){
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId())
                .withName("emaN")
                .withLastName("emaNtsaL")
                .withAdress("sserddA")
                .withMobile("123456789")
                .withEmail("addr@mail.some");
        app.contact().modify(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}
