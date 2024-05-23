package pages;



import common.EmailPropertiesLoader;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class SendEmail extends BaseClass{
    public static void sendEmailWay(String filePath) {
        // Recipient's email ID
        //String to = "Sasikumar.R@synergech.com";
        String to = "sasikumar.re97@gmail.com";

        // Sender's email ID
        String from = "kannansasi1997@gmail.com";

        // SMTP server information
        String host = "smtp.gmail.com";  //smtp.office365.com
        final String username = "kannansasi1997@gmail.com"; // Your email address
        final String password = "qtea txwe diln imfm"; // Your email password

        EmailPropertiesLoader emailPropertiesLoader = new EmailPropertiesLoader("email.properties");
        Properties emailProperties = emailPropertiesLoader.getProperties();
        String recipients = emailProperties.getProperty("recipients");
        System.out.println("Recipients : "+recipients);

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", "465"); // Port for TLS/STARTTLS
        //properties.setProperty("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");
        //properties.put("mail.smtp.starttls.enable", "true");



        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Read the HTML file content
            //String filename = "D:\\Demo\\target\\cucumber-reports\\cucumber.html";
            String filename=filePath;
            File file = new File(filename);

                if (file.exists()) {
                    // Get the file size in bytes
                    long fileSizeBytes = file.length();

                    // Convert bytes to kilobytes
                    double fileSizeKB = fileSizeBytes / 1024.0;
                    // Print the file size
                    System.out.println("******File size: " + fileSizeKB + " KB*****");
                } else {
                    System.out.println("File not found at the specified location.");
                }
            // Check if the file exists


            //String htmlContent = new String(Files.readAllBytes(Paths.get(filename)));

            // Create a default MimeMessage object.
            System.out.println("Mimemessage section");
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
           // message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            for (String recipient : recipients.split(",")) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient.trim()));
            }

            // Set Subject: header field
            message.setSubject("Cucumber Report");
            System.out.println("Body part  section");
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Please find the attached cucumber report.");

            // Create a multipart message for attachment
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is the attachment
            messageBodyPart = new MimeBodyPart();
            //String filenames = "D:\\Demo\\target\\cucumber-reports\\cucumber.html";
            String filenames=filePath;

            // Read the file as bytes
            Path path = Paths.get(filenames);
            byte[] fileContent = Files.readAllBytes(path);

            // Create a DataSource from the byte array
            DataSource dataSource = new ByteArrayDataSource(fileContent, "text/html");
            messageBodyPart.setDataHandler(new DataHandler(dataSource));
            messageBodyPart.setFileName("extent.html");

            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);
            System.out.println("Transport sent");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
