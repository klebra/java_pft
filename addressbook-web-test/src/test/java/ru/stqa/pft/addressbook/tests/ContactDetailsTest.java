package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDetailsTest extends TestBase {

    @Test
    public void testContactDetails(){
        app.contact().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        ContactData contactInfoFromDetailsForm  = app.contact().infoFromDetailsForm(contact);
        assertThat(contactInfoFromDetailsForm.getAllInfo(), equalTo(mergeAll(modifyPhones(contactInfoFromEditForm))));
    }

    private String mergeAll(ContactData contact) {
        return Arrays.asList(
                mergedNameBlock(contact),
                mergedPhones(contact),
                mergedEmails(contact)
        )
                .stream().filter((s) -> ! s.equals(""))
                .collect(Collectors.joining("\n\n"));
    }

    private String mergedNameBlock(ContactData contact){
        return Arrays.asList(
                contact.getName(),
                contact.getLastName()
        )
                .stream().filter((s) -> !toString().equals(""))
                .collect(Collectors.joining(" "))
                + "\n" + contact.getAddress();
    }

    private String mergedPhones(ContactData contact) {
        return Arrays.asList(contact.getHome(), contact.getMobile(), contact.getWork())
                .stream().filter((s) -> ! s.equals(""))
                .collect(Collectors.joining("\n"));
    }

    public static ContactData modifyPhones(ContactData contact) {
        String home = contact.getHome();
        String mobile = contact.getMobile();
        String work = contact.getWork();
        if (!home.equals("")){
            contact.withHome("H: " + home);
        }
        if (!mobile.equals("")){
            contact.withMobile("M: " + mobile);
        }
        if (!work.equals("")){
            contact.withWork("W: " + work);
        }
        return contact;
    }

    private String mergedEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> ! s.equals(""))
                .collect(Collectors.joining("\n"));
    }
}
