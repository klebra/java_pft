package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTest extends TestBase{

    @Test
    public void testGroupCreation() {
        app.goToGroupPage();
        app.clickOnNewGroup();
        app.fillGroupForm(new GroupData("Name of group", "Header", "Comment"));
        app.submitGroupCreation();
        app.returnToGroupPage();
    }

}
