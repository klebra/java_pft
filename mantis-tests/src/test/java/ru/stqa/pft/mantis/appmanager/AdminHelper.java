package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class AdminHelper extends HelperBase{

    public AdminHelper(ApplicationManager app) {
        super(app);
    }

    public void resetPasswordFor(String username) {
        wd.get(app.getProperty("web.baseUrl") + "manage_overview_page.php");
        click(By.linkText("Manage Users"));
        wd.findElement(By.linkText(username)).click();
        click(By.cssSelector("input[value='Reset Password']"));
    }
}
