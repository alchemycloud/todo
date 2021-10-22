package com.todo.webapp.table;

import com.todo.AbstractComponent;
import com.todo.AbstractUITest;
import com.todo.util.OsValidator;
import com.todo.webapp.form.DeleteUser;
import com.todo.webapp.form.EditUser;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class Users extends AbstractComponent {

    public Users(AbstractUITest test, WebElement element) {
        super(test, element);
    }

    public void setId(Integer value, Integer order) {
        final WebElement webElement = getElement().findElements(By.xpath(".//*[@data-qa='id']")).get(order);
        if (OsValidator.isMac()) {
            webElement.sendKeys(Keys.COMMAND + "a");
        } else {
            webElement.sendKeys(Keys.CONTROL + "a");
        }
        webElement.sendKeys(Keys.DELETE);
        webElement.sendKeys(value.toString());
        AbstractUITest.sleep(100);
    }

    public void scrollToId(Integer order) {
        final WebElement webElement = getElement().findElements(By.xpath(".//*[@data-qa='id']")).get(order);
        getTest().scrollToElement(webElement, false);
        AbstractUITest.sleep(100);
    }

    public void setFirstName(String value, Integer order) {
        final WebElement webElement = getElement().findElements(By.xpath(".//*[@data-qa='firstName']")).get(order);
        if (OsValidator.isMac()) {
            webElement.sendKeys(Keys.COMMAND + "a");
        } else {
            webElement.sendKeys(Keys.CONTROL + "a");
        }
        webElement.sendKeys(Keys.DELETE);
        webElement.sendKeys(value);
        AbstractUITest.sleep(100);
    }

    public void scrollToFirstName(Integer order) {
        final WebElement webElement = getElement().findElements(By.xpath(".//*[@data-qa='firstName']")).get(order);
        getTest().scrollToElement(webElement, false);
        AbstractUITest.sleep(100);
    }

    public void setLastName(String value, Integer order) {
        final WebElement webElement = getElement().findElements(By.xpath(".//*[@data-qa='lastName']")).get(order);
        if (OsValidator.isMac()) {
            webElement.sendKeys(Keys.COMMAND + "a");
        } else {
            webElement.sendKeys(Keys.CONTROL + "a");
        }
        webElement.sendKeys(Keys.DELETE);
        webElement.sendKeys(value);
        AbstractUITest.sleep(100);
    }

    public void scrollToLastName(Integer order) {
        final WebElement webElement = getElement().findElements(By.xpath(".//*[@data-qa='lastName']")).get(order);
        getTest().scrollToElement(webElement, false);
        AbstractUITest.sleep(100);
    }

    public void setUsername(String value, Integer order) {
        final WebElement webElement = getElement().findElements(By.xpath(".//*[@data-qa='username']")).get(order);
        if (OsValidator.isMac()) {
            webElement.sendKeys(Keys.COMMAND + "a");
        } else {
            webElement.sendKeys(Keys.CONTROL + "a");
        }
        webElement.sendKeys(Keys.DELETE);
        webElement.sendKeys(value);
        AbstractUITest.sleep(100);
    }

    public void scrollToUsername(Integer order) {
        final WebElement webElement = getElement().findElements(By.xpath(".//*[@data-qa='username']")).get(order);
        getTest().scrollToElement(webElement, false);
        AbstractUITest.sleep(100);
    }

    public void setPasswordHash(String value, Integer order) {
        final WebElement webElement = getElement().findElements(By.xpath(".//*[@data-qa='passwordHash']")).get(order);
        if (OsValidator.isMac()) {
            webElement.sendKeys(Keys.COMMAND + "a");
        } else {
            webElement.sendKeys(Keys.CONTROL + "a");
        }
        webElement.sendKeys(Keys.DELETE);
        webElement.sendKeys(value);
        AbstractUITest.sleep(100);
    }

    public void scrollToPasswordHash(Integer order) {
        final WebElement webElement = getElement().findElements(By.xpath(".//*[@data-qa='passwordHash']")).get(order);
        getTest().scrollToElement(webElement, false);
        AbstractUITest.sleep(100);
    }

    public void viewUserTodosSubmit(Integer order) {
        final WebElement webElement = getElement().findElements(By.xpath(".//*[@data-qa='viewUserTodos']")).get(order);
        waitToBeClickable(webElement).click();
        AbstractUITest.sleep(200);
    }

    public void scrollToViewUserTodos(Integer order) {
        final WebElement webElement = getElement().findElements(By.xpath(".//*[@data-qa='viewUserTodos']")).get(order);
        getTest().scrollToElement(webElement, false);
        AbstractUITest.sleep(100);
    }

    public EditUser editUserSubmit(Integer order) {
        final WebElement webElement = getElement().findElements(By.xpath(".//*[@data-qa='editUser']")).get(order);
        waitToBeClickable(webElement).click();
        AbstractUITest.sleep(200);
        return new EditUser(getTest(), waitToBeVisible(By.xpath("//*[@data-qa='editUser']")));
    }

    public void scrollToEditUser(Integer order) {
        final WebElement webElement = getElement().findElements(By.xpath(".//*[@data-qa='editUser']")).get(order);
        getTest().scrollToElement(webElement, false);
        AbstractUITest.sleep(100);
    }

    public DeleteUser deleteUserSubmit(Integer order) {
        final WebElement webElement = getElement().findElements(By.xpath(".//*[@data-qa='deleteUser']")).get(order);
        waitToBeClickable(webElement).click();
        AbstractUITest.sleep(200);
        return new DeleteUser(getTest(), waitToBeVisible(By.xpath("//*[@data-qa='deleteUser']")));
    }

    public void scrollToDeleteUser(Integer order) {
        final WebElement webElement = getElement().findElements(By.xpath(".//*[@data-qa='deleteUser']")).get(order);
        getTest().scrollToElement(webElement, false);
        AbstractUITest.sleep(100);
    }

    public EditUser getEditUser() {
        return new EditUser(getTest(), waitToBeVisible(By.xpath("//*[@data-qa='editUser']")));
    }

    public DeleteUser getDeleteUser() {
        return new DeleteUser(getTest(), waitToBeVisible(By.xpath("//*[@data-qa='deleteUser']")));
    }
}
