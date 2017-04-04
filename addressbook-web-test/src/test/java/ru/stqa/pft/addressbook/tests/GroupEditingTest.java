package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupEditingTest extends TestBase{

    @Test
    public void testGroupEditing(){
        app.getNavigationHelper().goToGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        if (!app.getGroupHelper().isGroupPresent()){
            app.getGroupHelper().createGroup(new GroupData("Name of group", null , null));
        }
        app.getNavigationHelper().selectCheckbox(before.size() - 1);
        app.getGroupHelper().editGroup();
        GroupData group = new GroupData(before.get(before.size() - 1).getId(), "Group of name", "Footer", "Header");
        app.getGroupHelper().fillGroupForm(group);
        app.getNavigationHelper().clickOnUpdate();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(group);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
