package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public List<GroupData> list() {
         List<GroupData> groups = new ArrayList<GroupData>();
         List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
         for (WebElement element : elements) {
             String name = element.getText();
             int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
             groups.add(new GroupData().withId(id).withName(name));
         }
         return groups;
    }

    public Set<GroupData> all() {
        Set<GroupData> groups = new HashSet<>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groups.add(new GroupData().withId(id).withName(name));
        }
        return groups;
    }

    public void selectCheckbox(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void clickOnUpdate() {
        click(By.name("update"));
    }

    public void modify(int index, GroupData group) {
        selectCheckbox(index);
        editGroup();
        fillGroupForm(group);
        clickOnUpdate();
        returnToGroupPage();
    }

    public void delete(int index) {
        selectCheckbox(index);
        deleteSelectedGroups();
        returnToGroupPage();
    }
}