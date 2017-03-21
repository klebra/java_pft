package ru.stqa.pft.addressbook;

public class ContactData {
    private final String name;
    private final String lastName;
    private final String adress;
    private final String mobile;
    private final String email;

    public ContactData(String name, String lastName, String adress, String mobile, String email) {
        this.name = name;
        this.lastName = lastName;
        this.adress = adress;
        this.mobile = mobile;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAdress() {
        return adress;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }
}
