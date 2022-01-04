package com.example.FB_Task;

import com.github.javafaker.Faker;

public class RandomDataGenerator {


    Faker fakerObject  = new Faker();


    public String generateRandomFullName() {
        return fakerObject.name().replaceAll("[^\\dA-Za-z ]", "");
    }

    public String generateRandomFirstName() {
        return fakerObject.firstName().replaceAll("[^\\dA-Za-z ]", "");
    }

    public String generateRandomMobileNumber() {
        return fakerObject.numerify("012########");
    }

    public String generateRandomEmailAddress() {
        return fakerObject.bothify("????##@gmail.com");
    }

    public String generateRandomComment() {
        return fakerObject.sentence();
    }

    public  String generateRandomPassword(){
        String password = "Test_"+generateRandomFirstName();
        return password;
    }

    public String generateCallCenterRegisterationName(){
        String registerName = "Test_"+generateRandomFirstName()+"_user";
        return registerName;
    }

    public String generateCallCenterRegisterationNumber(){
        String registerNumber = generateRandomMobileNumber();
        return registerNumber;
    }

}
