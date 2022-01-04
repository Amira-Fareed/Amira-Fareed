package com.example.FB_Task;

import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {


    WebDriver driver = new ChromeDriver();


    // Variables

    String chromeDriverPath= "chromedriver.exe";
    String invalidEmailMsg= "Find your account and log in.";
    String invalidPassMsg = "Forgotten password?";

    // Elements

     By emailInput = By.id("email");
     By passwordInput = By.id("pass");
     By loginButton = By.name("login");
     By invalidEmailMsgBox = By.xpath("//a[contains(text(),'"+invalidEmailMsg+"')]");
     By invalidPassMsgBox = By.xpath("//a[contains(text(),'"+invalidPassMsg+"')]");
     By createAccountBtn = By.cssSelector("a[data-testid='open-registration-form-button']");
     By regFirstNameInput = By.name("firstname");
     By regLastNameInput = By.name("lastname");
     By regEmailInput = By.name("reg_email__");
     By regEmailConfirmInput = By.name("reg_email_confirmation__");
     By regPassInput = By.name("reg_passwd__");
     By selectDay = By.id("day");
     By selectMonth = By.id("month");
     By selectYear = By.id("year");
     By selectGender = By.cssSelector("input[value='2']");
     By signUpBtn = By.name("websubmit");
     By regErrorMsgBox = By.id("reg_error");
     By regErrorMsg = By.id("reg_error_inner");


    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

}

    protected boolean elementVisible(By locator, int timeOut) {
        boolean flag;
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            flag = true;
        } catch (Exception ex) {
            flag = false;
        }
        return flag;
    }


    public void openFB(){
        driver.get("https://www.facebook.com/");
    }

    public void closeFB(){
        driver.close();
    }

    public void logout(){
        driver.manage().deleteAllCookies();
    }
    public void login(String email ,String pass ){
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(pass);
        driver.findElement(loginButton).click();
    }

    public boolean loggedIn(String name){
        By loggedInName = By.xpath("//span[contains(text(),'Welcome to Facebook, "+name+"')]");
        elementVisible(loggedInName,10);
        return driver.findElement(loggedInName).isDisplayed();
    }

    public boolean notLoggedIn(int invalidCase) {

        if (invalidCase == 0){
            elementVisible(invalidEmailMsgBox,10);
            return driver.findElement(invalidEmailMsgBox).isDisplayed();
        }
        else {
            elementVisible(invalidPassMsgBox,10);
            return driver.findElement(invalidPassMsgBox).isDisplayed();
        }

    }

    public boolean registered(String name){
        By loggedInName = By.xpath("//span[contains(text(),'"+name+"')]");
        elementVisible(loggedInName,30);
        return driver.findElement(loggedInName).isDisplayed();
    }
    public void register(String email ,String pass,String fName,String lName) {
        driver.findElement(createAccountBtn).click();
        elementVisible(regEmailInput, 10);
        driver.findElement(regFirstNameInput).sendKeys(fName);
        driver.findElement(regLastNameInput).sendKeys(lName);
        driver.findElement(regEmailInput).sendKeys(email);
        driver.findElement(regEmailConfirmInput).sendKeys(email);
        driver.findElement(regPassInput).sendKeys(pass);
        driver.findElement(selectDay).sendKeys("2");
        driver.findElement(selectMonth).sendKeys("OCT");
        driver.findElement(selectYear).sendKeys("1990");
        driver.findElement(selectGender).click();
        driver.findElement(signUpBtn).click();

        if (elementVisible(regErrorMsg, 10)) {
            for (int i = 0; i < 5; i++) {
                if (elementVisible(regErrorMsg, 10)) {
                    driver.findElement(signUpBtn).click();
                }
            }
        }
    }


    public Boolean checkInvalidMsg(String email){
        String msg = "There is an existing account associated with "+email;
        return driver.findElement(regErrorMsg).getText().equals(msg);

    }


}