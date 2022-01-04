package com.example.FB_Task;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class MainPageTest{

MainPage mainPage = new MainPage();

//Variables

    String validLoginEmail = "open_pgfcqyu_user@tfbnw.net";
    String validLoginPassword = "Test1234";
    String validLoginName = "Open";
    String validRegEmail = "tsfm36@gmail.com";


//Login to Facebook Successfully
    @Test
    public void loginSuccessfully() {
        mainPage.openFB();
        mainPage.login(validLoginEmail,validLoginPassword);
        Assert.assertTrue(mainPage.loggedIn(validLoginName));
    }

//Login using invalid Email
    @Test
    public void loginUnsuccessfullyInvalidEmail(){
        RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
        String invalidLoginEmail = randomDataGenerator.generateRandomEmailAddress();
        String invalidLoginPassword = randomDataGenerator.generateRandomPassword();
        mainPage.openFB();
        mainPage.login(invalidLoginEmail,invalidLoginPassword);
        Assert.assertTrue(mainPage.notLoggedIn(0));
        mainPage.closeFB();

    }

//Login using invalid password
    @Test
    public void loginUnsuccessfullyInvalidPassword(){
        RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
        String invalidLoginPassword = randomDataGenerator.generateRandomPassword();
        mainPage.openFB();
        mainPage.login(validLoginEmail,invalidLoginPassword);
        Assert.assertTrue(mainPage.notLoggedIn(1));
        mainPage.closeFB();

    }

//Register to Facebook
    @Test
    public void registerSuccessfully(){
        mainPage.openFB();
        RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
        String regFName = randomDataGenerator.generateRandomFirstName();
        String regLName = randomDataGenerator.generateRandomFirstName();
        String regEmail = randomDataGenerator.generateRandomEmailAddress();
        String regPassword = randomDataGenerator.generateRandomPassword();
        mainPage.register(regEmail,regPassword,regFName,regLName);
        Assert.assertTrue(mainPage.registered(regFName));
        mainPage.logout();
        mainPage.openFB();
        mainPage.login(regEmail,regPassword);
        Assert.assertTrue(mainPage.registered(regFName));
        mainPage.closeFB();
    }


//Register to Facebook with an email already registered
    @Test
    public void registerUnsuccessfullyInvalidEMail(){
        mainPage.openFB();
        RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
        String regFName = randomDataGenerator.generateRandomFirstName();
        String regLName = randomDataGenerator.generateRandomFirstName();
        String regPassword = randomDataGenerator.generateRandomPassword();
        mainPage.register(validRegEmail,regPassword,regFName,regLName);
        Assert.assertTrue(mainPage.checkInvalidMsg(validRegEmail));
    }


}