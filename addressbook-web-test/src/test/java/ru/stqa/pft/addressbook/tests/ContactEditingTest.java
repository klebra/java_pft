package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEditingTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        app.contact().homePage();
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withName("Name"));
        }
    }

    @Test
    public void testContactEditing(){
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId())
                .withName("emaN")
                .withLastName("emaNtsaL")
                .withAddress("sserddA")
                .withMobile("123456789")
                .withEmail("addr@mail.some")
                .withEmail2("some@string.here")
                .withEmail3("111@222.333");
        app.contact().modify(contact);
        Contacts after = app.db().contacts();
        assertThat(after.size(), equalTo(before.size()));
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
