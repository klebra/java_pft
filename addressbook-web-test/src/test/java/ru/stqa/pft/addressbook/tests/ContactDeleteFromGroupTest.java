package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteFromGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withName("Name"));
        }
        if (app.db().groups().size() == 0){
            app.group().create(new GroupData().withName("Name of group"));
        }
        if (app.db().contacts().findAllWithGroups().isEmpty()){
            app.contact()
                    .addContactToGroup(
                            app.db().contacts().iterator().next(),
                            app.db().groups().iterator().next()
                    );
        }
        app.contact().homePage();
    }

    @Test
    public void testDeleteContactFromGroup(){
        Contacts before = app.db().contacts();
        ContactData contactWithGroup = before.findAllWithGroups().iterator().next();
        GroupData deletedGroup = contactWithGroup.getGroups().iterator().next();

        app.contact().deleteContactFromGroup(contactWithGroup, deletedGroup);

        Contacts after = app.db().contacts();
        assertThat(after,
                equalTo(before
                        .without(contactWithGroup)
                        .withAdded(contactWithGroup.removeGroup(deletedGroup))));
    }
}
