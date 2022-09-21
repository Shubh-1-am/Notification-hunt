import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class EMail {

    private static final String SUBJECT = "New updates on the website:";
    private static  String TO ;
    private static  String FROM ;
    private static  String PASSWORD ;

    public EMail(String FROM, String PASSWORD,String TO){

        EMail.TO = TO;
        EMail.FROM = FROM;
        EMail.PASSWORD = PASSWORD;
    }

    public void getEmail (Records record) {
        File file = new File(Records.NEW_UPDATES_FILEPATH);
        if (file.length() != 0) {
            sendEmail(file);
            System.out.println("Mail sent to "+ EMail.TO+" .");
            record.updatePrevRecordFile(file);
        } else {
            System.out.println("No new updates!");
        }
    }

    private void sendEmail(File newUpdates) {
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();
        System.out.println("Properties"+ properties);
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth","true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM,PASSWORD);
            }
        });
        session.setDebug(true);

        MimeMessage m = new MimeMessage(session);
        try {
            m.setFrom(FROM);
            m.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(TO)));
            m.setSubject(SUBJECT);
            m.setText(Files.readString(Path.of(newUpdates.getAbsolutePath())));
            Transport.send(m);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
