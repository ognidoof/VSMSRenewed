/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javamail;

import DAO.UserDAO;
import Model.Order;
import Model.Orderline;
import Model.Supplier;
import Model.Vendor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author vincentt.2013
 */
public class EmailController {

    private static String host;
    private static String user;
    private static String password;

    public EmailController(String host, String user, String password){
        this.host= host;
        this.user = user;
        this.password =password;
    }
    
    public static void sendMessage(String toEmail,String subject, String messageString) {


        //Sending to Google SMTP Port
        String SMTP_PORT = "465";
        String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        //Get the session object  
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.socketFactory.port", SMTP_PORT);
        props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.put("mail.smtp.socketFactory.fallback", "false");

        Session session = Session.getInstance(props, new GMailAuthenticator(user, password));

        //Compose the message  
        try {
            MimeMessage message = new MimeMessage(session);

            // creates message part
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(message, "text/html");

            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

            message.setSubject(subject);
            message.setText(messageString);
            //send the message  
            Transport.send(message);

            System.out.println("Message is delivered");
        } catch (MessagingException e) {
            System.out.println("Message is not sent");
            e.printStackTrace();
        }
    }
    
    //TODO: Convert the orders into message string for each supplier
    public static HashMap<Supplier,String> supplierMessageList (Order order){
        HashMap<Supplier,String> suppOrderMap = new HashMap<Supplier, String>();
       
        for(Orderline orderline : order.getOrderlines()){
            int supplier_id = orderline.getSupplier_id();
            Supplier supplier = UserDAO.getSupplierById(supplier_id);
            if (suppOrderMap.containsKey(supplier)){
                String orderlineText = suppOrderMap.get(supplier);
                orderlineText += orderline.toString();
            } else{
                suppOrderMap.put(supplier, orderline.toString());
            }
        }
        return suppOrderMap;
    }
    
    //TODO: convert the orders into message string for vendor
    public static void sendMessageToVendor(Vendor vendor,HashMap<Supplier,String> suppOrderMap){
        Iterator iter = suppOrderMap.keySet().iterator();
        String messageText = "";
        while(iter.hasNext()){
            Supplier supplier = (Supplier)iter.next();
            messageText += supplier.getSupplier_name()+"\n";
            messageText += supplier.getEmail()+"\n";
            messageText += supplier.getTelephone_number()+"\n";
            messageText += suppOrderMap.get(supplier);
            messageText += "\n\n";
        }
        
        sendMessage(vendor.getEmail(),"Your orders to suppliers", messageText);
    }
    
    //TODO: send orderlist to multiple suppliers --> Make sure you have the domain ready
    public static void sendMessageToSuppliers (HashMap<Supplier,String> suppOrderMap){
        Iterator iter = suppOrderMap.keySet().iterator();
        while(iter.hasNext()){
            Supplier supplier = (Supplier)iter.next();
            String messageText = suppOrderMap.get(supplier);
            sendMessage(supplier.getEmail(),"Order from Vendor", messageText);
        }
    }
}
