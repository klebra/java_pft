package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupEditingTest extends TestBase{

    @Test
    public void testGroupEditing(){
        app.getNavigationHelper().goToGroupPage();
        app.getNavigationHelper().selectCheckbox();
        app.getGroupHelper().editGroup();
        app.getGroupHelper().fillGroupForm(new GroupData("Group of name", "Footer", "Header"));
        app.getNavigationHelper().clickOnUpdate();
        app.getGroupHelper().returnToGroupPage();
    }
}
