package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withName("Name"));
        }
        if (app.db().groups().size() == 0){
            app.group().create(new GroupData().withName("Name of group"));
        }
        app.contact().homePage();
    }

    @Test
    public void testAddContactToGroup(){
        Contacts before = app.db().contacts();
        ContactData selectedContact = before.iterator().next();
        Groups groupList = app.db().groups();
        GroupData selectedGroup = groupList.iterator().next();

        app.contact().addContactToGroup(selectedContact, selectedGroup);

        Contacts after = app.db().contacts();
        assertThat(after,
                equalTo(before.without(selectedContact).withAdded(selectedContact.addContactToGroup(selectedGroup))));
    }
}
