package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

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

    public void editContact() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
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
}
