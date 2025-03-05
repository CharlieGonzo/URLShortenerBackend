package com.example.urlshort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@SpringBootApplication
public class UrlShortApplication implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(UrlShortApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(UrlShortApplication.class, args);
    }

    @Autowired
    JdbcTemplate template;

    public int createRandomID(){
        Random random = new Random();
        int number = 0;
        for (int i = 0; i < 5; i++) {
            number = 10000 + random.nextInt(90000); // Generates a number between 10000 and 99999

        }

        return number;

    }

    public int changID(int id){
        return id * 10;
    }

    @Override
    public void run(String... args) throws Exception {
        template.execute("DROP TABLE IF EXISTS shortURLs");

        template.execute("CREATE TABLE shortURLs (id int primary key ,fullURL VARCHAR(255))");
        log.info("testing random ID {}",createRandomID());

        Object[] list = new Object[3];
        list[0] = createRandomID();
        list[1] = "https://www.google.com/";
        template.update("INSERT INTO shortURLS VALUES (?,?)",list[0],list[1]);
        List<String> lists = new ArrayList<>();
        template.query("SELECT * froM shortURLs",(rs,row) -> {
            System.out.println(rs.getString("fullURL"));
            System.out.println(rs.getInt("id"));
            return null;
        });

        log.info(lists.toString());
//
//        template.execute("INSERT INTO short ");
    }
}
