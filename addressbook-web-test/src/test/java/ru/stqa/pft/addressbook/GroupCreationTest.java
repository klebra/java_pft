package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTest extends TestBase{

    @Test
    public void testGroupCreation() {
        goToGroupPage();
        clickOnNewGroup();
        fillGroupForm(new GroupData("Name of group", "Header", "Comment"));
        submitGroupCreation();
        returnToGroupPage();
    }

}
