package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactsHelper extends HelperBase{

    public ContactsHelper(WebDriver wd) {
        super(wd);
    }

    public void clickOnHomePage() {
        click(By.linkText("home page"));
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("address"), contactData.getAdress());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("email"), contactData.getEmail());
    }

    public void clickOnAddContact() {
        click(By.linkText("add new"));
    }

    public void deleteCurrent(){
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void create(ContactData data) {
        clickOnAddContact();
        fillContactForm(data);
        submitContactCreation();
        clickOnHomePage();
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String lastname = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
            String name = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            contacts.add(new ContactData().withId(id).withName(name).withLastName(lastname));
        }
        return contacts;
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("a[href*='edit.php?id=" + id + "']")).click();
    }

    public void selectCheckboxById(int id) {
        wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
    }

    public void clickOnUpdate() {
        click(By.name("update"));
    }

    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        fillContactForm(contact);
        clickOnUpdate();
        clickOnHomePage();
    }

    public void homePage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home"));
    }

    public void acceptAlert(){
        wd.switchTo().alert().accept();
    }


    public void delete(ContactData contact) {
        selectCheckboxById(contact.getId());
        deleteCurrent();
        acceptAlert();
        homePage();
    }
}
