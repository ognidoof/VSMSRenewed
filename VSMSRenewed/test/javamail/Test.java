/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javamail;

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
public class Test {

    public static void main(String[] args) {

        String host = "smtp.gmail.com";
        final String user = "ognidoof";
        final String password = "AbC12321CbA";
        String subject = "Receiving order from <supplier>; Order ID : ...";

        if (!user.equals("") && !password.equals("")) {
          EmailController emailController = new EmailController(host,user,password);
          emailController.sendMessage("vincentt.2013@sis.smu.edu.sg","Receiving order from <supplier>; Order ID : ...", "Your order is tested here \n Hello I am your <ul><li>d</li></ul>");
        } else {
            System.out.println("User email and password are empty. Please correct the problem");
        }
    }
}
