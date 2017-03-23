package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase{

    @Test
    public void testContactDeletion(){
        app.getNavigationHelper().goToHomePage();
        app.getNavigationHelper().selectCheckbox();
        app.getContactsHelper().deleteContact();
        app.getNavigationHelper().acceptAlert();
    }
}
