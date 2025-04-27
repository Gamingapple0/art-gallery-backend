package com.sit331.artgallery.auth;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import jakarta.annotation.PostConstruct;


@Configuration
public class FirebaseConfig {

    @Bean
    FirebaseApp firebaseApp() throws IOException {
        if (FirebaseApp.getApps().isEmpty()) {

        ClassPathResource resource = new ClassPathResource("./art-gallery-firebase-service-account.json"); 
        InputStream serviceAccount = resource.getInputStream();
        
	    FirebaseOptions options = FirebaseOptions.builder()
	      .setCredentials(GoogleCredentials.fromStream(serviceAccount))
	      .build();
	
        return FirebaseApp.initializeApp(options);
        }
        return FirebaseApp.getInstance(); // Return existing instance
    }
}
