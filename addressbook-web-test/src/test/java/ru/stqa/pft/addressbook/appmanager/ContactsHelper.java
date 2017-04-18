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
        type(By.name("address"), contactData.getAddress());
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
            List<WebElement> cells = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            String name = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            String allphones = cells.get(5).getText();
            String address = cells.get(3).getText();
            String allEmail = cells.get(4).getText();

            contacts.add(new ContactData()
                    .withId(id)
                    .withName(name)
                    .withLastName(lastname)
                    .withAllPhones(allphones)
                    .withAddress(address)
                    .withAllEmail(allEmail)
            );
        }
        return contacts;
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href*='edit.php?id=%s']", id))).click();
    }

    public void selectCheckboxById(int id) {
        wd.findElement(By.cssSelector(String.format("input[id='%s']", id))).click();
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

    public ContactData infoFromEditForm(ContactData contact) {
        selectContactById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData()
                .withId(contact.getId())
                .withName(firstname)
                .withLastName(lastname)
                .withHome(home)
                .withMobile(mobile)
                .withWork(work)
                .withAddress(address)
                .withEmail(email)
                .withEmail2(email2)
                .withEmail3(email3);
    }

    public ContactData infoFromDetailsForm(ContactData contact) {
        clickOnDetailsOfContact(contact.getId());
        String allinfo = wd.findElement(By.id("content")).getText();
        return new ContactData()
                .withAllInfo(allinfo);
    }

    private void clickOnDetailsOfContact(int id) {
        wd.findElement(By.cssSelector(String.format("a[href*='view.php?id=%s']", id))).click();
    }
}
