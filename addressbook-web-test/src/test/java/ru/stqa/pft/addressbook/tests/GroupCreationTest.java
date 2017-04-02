package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTest extends TestBase{

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().goToGroupPage();
        int before = app.getGroupHelper().getGroupSize();
        app.getGroupHelper().createGroup(new GroupData("Name of group", null , null));
        int after = app.getGroupHelper().getGroupSize();
        Assert.assertEquals(after, before + 1);
    }

}
