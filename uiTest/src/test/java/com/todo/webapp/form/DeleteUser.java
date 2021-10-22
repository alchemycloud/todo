package com.todo.webapp.form;

import com.todo.AbstractComponent;
import com.todo.AbstractUITest;
import com.todo.util.OsValidator;
import com.todo.webapp.dropdown.UserRoleDropDown;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class DeleteUser extends AbstractComponent {

    public DeleteUser(AbstractUITest test, WebElement element) {
        super(test, element);
    }

    public void setFirstName(String value) {
        final WebElement webElement = waitToBeVisible(By.xpath(".//*[@data-qa='firstName']"));
        if (OsValidator.isMac()) {
            webElement.sendKeys(Keys.COMMAND + "a");
        } else {
            webElement.sendKeys(Keys.CONTROL + "a");
        }
        webElement.sendKeys(Keys.DELETE);
        webElement.sendKeys(value);
        AbstractUITest.sleep(100);
    }

    public void scrollToFirstName() {
        final WebElement webElement = waitToBeVisible(By.xpath(".//*[@data-qa='firstName']"));
        getTest().scrollToElement(webElement, false);
        AbstractUITest.sleep(100);
    }

    public void setLastName(String value) {
        final WebElement webElement = waitToBeVisible(By.xpath(".//*[@data-qa='lastName']"));
        if (OsValidator.isMac()) {
            webElement.sendKeys(Keys.COMMAND + "a");
        } else {
            webElement.sendKeys(Keys.CONTROL + "a");
        }
        webElement.sendKeys(Keys.DELETE);
        webElement.sendKeys(value);
        AbstractUITest.sleep(100);
    }

    public void scrollToLastName() {
        final WebElement webElement = waitToBeVisible(By.xpath(".//*[@data-qa='lastName']"));
        getTest().scrollToElement(webElement, false);
        AbstractUITest.sleep(100);
    }

    public UserRoleDropDown getRole() {
        return new UserRoleDropDown(getTest(), waitToBeVisible(By.xpath(".//*[@data-qa='role']")));
    }

    public void scrollToRole() {
        final WebElement webElement = waitToBeVisible(By.xpath(".//*[@data-qa='role']"));
        getTest().scrollToElement(webElement, false);
        AbstractUITest.sleep(100);
    }

    public void setUsername(String value) {
        final WebElement webElement = waitToBeVisible(By.xpath(".//*[@data-qa='username']"));
        if (OsValidator.isMac()) {
            webElement.sendKeys(Keys.COMMAND + "a");
        } else {
            webElement.sendKeys(Keys.CONTROL + "a");
        }
        webElement.sendKeys(Keys.DELETE);
        webElement.sendKeys(value);
        AbstractUITest.sleep(100);
    }

    public void scrollToUsername() {
        final WebElement webElement = waitToBeVisible(By.xpath(".//*[@data-qa='username']"));
        getTest().scrollToElement(webElement, false);
        AbstractUITest.sleep(100);
    }

    public void setPasswordHash(String value) {
        final WebElement webElement = waitToBeVisible(By.xpath(".//*[@data-qa='passwordHash']"));
        if (OsValidator.isMac()) {
            webElement.sendKeys(Keys.COMMAND + "a");
        } else {
            webElement.sendKeys(Keys.CONTROL + "a");
        }
        webElement.sendKeys(Keys.DELETE);
        webElement.sendKeys(value);
        AbstractUITest.sleep(100);
    }

    public void scrollToPasswordHash() {
        final WebElement webElement = waitToBeVisible(By.xpath(".//*[@data-qa='passwordHash']"));
        getTest().scrollToElement(webElement, false);
        AbstractUITest.sleep(100);
    }

    public void submitSubmit() {
        final WebElement webElement = waitToBeVisible(By.xpath(".//*[@data-qa='submit']"));
        waitToBeClickable(webElement).click();
        waitToBeInvisible(By.xpath("//*[@class='cdk-overlay-backdrop']"));
    }

    public void scrollToSubmit() {
        final WebElement webElement = waitToBeVisible(By.xpath(".//*[@data-qa='submit']"));
        getTest().scrollToElement(webElement, false);
        AbstractUITest.sleep(100);
    }

    public void cancel() {
        final WebElement webElement = waitToBeVisible(By.xpath(".//*[@data-qa='cancel']"));
        waitToBeClickable(webElement).click();
        waitToBeInvisible(By.xpath("//*[@class='cdk-overlay-backdrop']"));
    }

    public void close() {
        final WebElement webElement = waitToBeVisible(By.xpath(".//*[@data-qa='close']"));
        waitToBeClickable(webElement).click();
        waitToBeInvisible(By.xpath("//*[@class='cdk-overlay-backdrop']"));
    }
}
