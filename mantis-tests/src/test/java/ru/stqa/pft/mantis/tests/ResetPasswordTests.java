package ru.stqa.pft.mantis.tests;


import java.util.List;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() throws IOException, MessagingException {
        app.mail().start();
        if (app.db().users().size() <= 1) {
            long now = System.currentTimeMillis();
            String user = String.format("user%s", now);
            String password = user;
            String email = String.format("%s@localhost.localdomain", user);
            app.registration().start(user, email);
            List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
            String confirmationLink = findConfirmationLink(mailMessages, email);
            app.registration().finish(confirmationLink, password);
            assertTrue(app.newSession().login(user, password));
        }
    }

    @Test
    public void testResetPassword() throws IOException, MessagingException {
        String user = app.db().users().iterator().next().getUsername();
        String password = user;
        String email = String.format("%s@localhost.localdomain", user);

        app.login().as(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        app.admin().resetPasswordFor(user);
        app.login().logout();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 20000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);

        assertTrue(app.newSession().login(user, password));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}
