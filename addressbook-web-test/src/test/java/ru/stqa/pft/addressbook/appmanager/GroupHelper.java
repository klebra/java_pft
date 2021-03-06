package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class GroupHelper extends HelperBase{

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void clickOnNewGroup() {
        click(By.name("new"));
    }

    public void deleteSelectedGroups() {
        click(By.name("delete"));
    }

    public void editGroup() {
        click(By.name("edit"));
    }

    public void create(GroupData groupData) {
        clickOnNewGroup();
        fillGroupForm(groupData);
        submitGroupCreation();
        returnToGroupPage();
    }

    public Groups all() {
        Groups groups = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groups.add(new GroupData().withId(id).withName(name));
        }
        return groups;
    }

    public void selectCheckboxById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void clickOnUpdate() {
        click(By.name("update"));
    }

    public void modify(GroupData group) {
        selectCheckboxById(group.getId());
        editGroup();
        fillGroupForm(group);
        clickOnUpdate();
        returnToGroupPage();
    }

    public void delete(GroupData group) {
        selectCheckboxById(group.getId());
        deleteSelectedGroups();
        returnToGroupPage();
    }
}