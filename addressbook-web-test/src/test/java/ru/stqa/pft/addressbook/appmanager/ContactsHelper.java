package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
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

    public void deleteContact(){
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void createContact(ContactData data) {
        clickOnAddContact();
        fillContactForm(data);
        submitContactCreation();
        clickOnHomePage();
    }

    public boolean isContactPresent() {
        return isElementPresent(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public List<ContactData> getContactList() {
         List<ContactData> contacts = new ArrayList<ContactData>();
         List<WebElement> elements = wd.findElements(By.name("entry"));
         for (WebElement element : elements) {
             String lastname = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
             String name = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
             int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
             ContactData contact = new ContactData(id, name, lastname,null, null, null);
             contacts.add(contact);
         }
         return contacts;
    }

    public void selectContact(int index) {
        wd.findElements(By.name("entry")).get(index).findElement(By.cssSelector("td:nth-child(8)")).click();
    }
}
