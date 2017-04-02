package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupDeletionTest extends TestBase{

    @Test
    public void testGroupDeletion(){
        app.getNavigationHelper().goToGroupPage();
        int before = app.getGroupHelper().getGroupSize();
        if (!app.getGroupHelper().isGroupPresent()){
            app.getGroupHelper().createGroup(new GroupData("Name of group", null , null));
        }
        app.getNavigationHelper().selectCheckbox();
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
        int after = app.getGroupHelper().getGroupSize();
        Assert.assertEquals(after, before - 1);
    }

}
