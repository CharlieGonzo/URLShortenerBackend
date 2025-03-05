package com.example.urlshort;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Random;

@Repository
public class URLRepository {

    @Autowired
    JdbcTemplate template;


    public String getURL(int id){
        System.out.println("here");
        final String[] response = new String[1];
        template.query("SELECT fullURL FROM shortURLs WHERE id = ? ",(rs,row) -> {
            response[0] = rs.getString("fullURL");
            return null;
        },id);

        return response[0];
    }


    private int changID(int id){
        return id * 10;
    }

    private int createRandomID(){
        Random random = new Random();
        int number = 0;
        for (int i = 0; i < 5; i++) {
            number = 10000 + random.nextInt(90000); // Generates a number between 10000 and 99999

        }

        return number;
    }

    public int createURL(String URL){
        int newID = createRandomID();
        String checkIfExist = getURL(newID);
        while(checkIfExist != null){
            newID = changID(newID);
            checkIfExist = getURL(newID);
        }
        try {
            template.update("INSERT INTO shortURLs VALUES (?,?)", newID, URL);
        }catch (Exception e){
            return -1;
        }
        return newID;

    }



}
