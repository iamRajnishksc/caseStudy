package com.example.CaseStudyBackend.controller;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import com.example.CaseStudyBackend.model.SigninRequest;
import com.example.CaseStudyBackend.model.SigninResponse;
import com.example.CaseStudyBackend.model.User;
import org.apache.tomcat.jni.Buffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.CaseStudyBackend.repository.Userrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    Userrepository userrepo;


    @CrossOrigin(origins ="http://localhost:4200")
    @RequestMapping(value = "/signup",method=RequestMethod.POST, headers = "Accept=application/json"  )

    public  ResponseEntity<Object> reactuserapi2(@RequestBody User user) throws AddressException, MessagingException, ClassNotFoundException, IOException {

        User usrsaved = userrepo.save(user);
        // make sure your entity class properties of user are in lower case and match the json,to avoid errors
        System.out.println(user +"check this " +usrsaved.getUsername());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usrsaved.getId())
                .toUri();
        sendemail(usrsaved.getId()) ;

        return ResponseEntity.created(location).build();
    }





    public void sendemail(Long userid) throws AddressException, MessagingException {

        final String username = "rajnishksc1234@gmail.com";
        final String password = "bx4UhC9zJ1#";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new javax.mail.PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("rajnishksc1234@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("rajnishksc123@gmail.com")
            );
            message.setSubject("USer confirmation email");
            //     message.setText("Dear Mail Crawler,"
            //           + "\n\n Please do not spam my email!");
            message.setContent(
                    "<h1><a href =\"http://172.18.6.80:8081/confirmuser/"+userid+"/\"> Click to confirm </a></h1>",
                    "text/html");
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping(value="/confirmuser/{userid}", method=RequestMethod.GET)
    public String welcomepage(@PathVariable Long userid) {
        Optional<User> userlist =   userrepo.findById(userid);
        //do a null check for home work
        User usr = new User();
        usr = userrepo.getById(userid);
        usr.setConfirmed(true);
        userrepo.save(usr);
        return "User confirmed" +usr.getUsername();
    }


    @CrossOrigin(origins ="http://localhost:4200")
    @PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> login(@RequestBody SigninRequest signinRequest)
    {
        final User userDetails = userrepo.findByNameAndPassword(signinRequest.getUsername(),signinRequest.getPassword());
////        if(userDetails == null || !passwordEncoder.matches(signinRequest.getPassword(), userDetails.getPassword())) {
////            throw new UsernameNotFoundException("User not found... Invalid Credentials...!!");
////        }
//         final boolean isAdmin = userDetails.getAdmin();
        return ResponseEntity.ok(new SigninResponse(userDetails.getUsername(),userDetails.getAdmin(),userDetails.getConfirmed()));
    }



    @PostMapping(path = "/adminaccess/{id}")
    public  String admin(@PathVariable Long id)   {
     User user=   userrepo.findById(id).get();
     user.setAdmin(true);
        userrepo.save(user);
        return "Admin Access Granted to" + user.getUsername();
    }





}
