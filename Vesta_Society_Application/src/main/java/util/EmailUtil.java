package util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import model.Vehicle;

public class EmailUtil {

    public static void sendCredentials(String toEmail, String username, String password) {
        final String fromEmail = "md.rocks284@gmail.com"; 
        final String emailPassword = "azfolqwmadutwwrn"; // app password 

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
//        azfo lqwm adut wwrn -- app password 

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, emailPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail, "Vesta Admin"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Your Vesta Login Credentials");
            message.setText(
            	    "Dear " + username + ",\n\n" +
            	    "Welcome to Vesta — your trusted companion for seamless society management.\n\n" +
            	    "We're thrilled to have you onboard! Your account has been successfully created, and you're now part of a platform designed to simplify communication, payments, and everyday operations within your society.\n\n" +
            	    "Here are your login credentials:\n" +
            	    "Username: " + username + "\n" +
            	    "Password: " + password + "\n\n" +
            	    "For your security, we recommend logging in and changing your password as per your preference.\n" +
            	    "You can update it anytime from your profile settings.\n\n" +
            	    "If you have any questions or need assistance, our support team is just a message away.\n\n" +
            	    "Warm regards,\n" +
            	    "Vesta Admin Team"
            	);


            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void sendVehicleConfirmation(String toEmail, String memberName, Vehicle v, String flatNo,String buildingName) {
        final String fromEmail = "md.rocks284@gmail.com";
        final String emailPassword = "azfolqwmadutwwrn";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, emailPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail, "Vesta Admin"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Vehicle Registration Confirmation");

            message.setText(
            	    "Dear " + memberName + ",\n\n" +
            	    "We're happy to inform you that your vehicle has been successfully registered in the Vesta system.\n\n" +
            	    "📌 Vehicle Registration Details:\n" +
            	    "• Registration Number: " + v.getRegistrationNo() + "\n" +
            	    "• Vehicle Type: " + v.getType() + "\n" +
            	    "• Linked Unit: Flat " + flatNo + ", " + buildingName + "\n\n" +
            	    "🚗 Parking Slot Booking:\n" +
            	    "To reserve a parking slot, please reach out directly to your Society Manager. They will assist you with slot availability and assignment.\n\n" +
            	    "If you have any questions or need support, feel free to contact the Vesta Admin Team.\n\n" +
            	    "Thank you for being a part of Vesta — your trusted society management platform.\n\n" +
            	    "Warm regards,\n" +
            	    "Vesta Admin Team"
            	);


            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void sendOtpToEmail(String toEmail, int otp) {
    	final String fromEmail = "md.rocks284@gmail.com".trim();
        final String emailPassword = "azfolqwmadutwwrn";
        
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, emailPassword);
            }
        });
        
        try {
        	
        		Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail, "Vesta Admin"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("6 Digit For Chaange Password");
            
            String body = "Dear Resident,\n\n"
                    + "We received a request to verify your identity for accessing your Vesta Society account.\n"
                    + "Please use the following One-Time Password (OTP) to proceed:\n\n"
                    + "🔐 Your OTP: " + otp + "\n\n"
                    + "This OTP is valid for the next 5 minutes. Do not share it with anyone.\n\n"
                    + "If you did not request this, please ignore this email or contact your society administrator.\n\n"
                    + "Warm regards,\n"
                    + "Vesta Admin\n"
                    + "🏢 Vesta Society Management";

        message.setText(body);

        	
        Transport.send(message);
        	
        	
        }catch(Exception e) {
        	System.out.println(e.getMessage());
        }
        
    }

    
}
