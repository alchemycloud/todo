package com.todo.webapp.table;

import com.todo.AbstractComponent;
import com.todo.AbstractUITest;
import com.todo.util.OsValidator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class UserTodos extends AbstractComponent {

    public UserTodos(AbstractUITest test, WebElement element) {
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

    public void setUserId(Integer value, Integer order) {
        final WebElement webElement = getElement().findElements(By.xpath(".//*[@data-qa='userId']")).get(order);
        if (OsValidator.isMac()) {
            webElement.sendKeys(Keys.COMMAND + "a");
        } else {
            webElement.sendKeys(Keys.CONTROL + "a");
        }
        webElement.sendKeys(Keys.DELETE);
        webElement.sendKeys(value.toString());
        AbstractUITest.sleep(100);
    }

    public void scrollToUserId(Integer order) {
        final WebElement webElement = getElement().findElements(By.xpath(".//*[@data-qa='userId']")).get(order);
        getTest().scrollToElement(webElement, false);
        AbstractUITest.sleep(100);
    }

    public void setTask(String value, Integer order) {
        final WebElement webElement = getElement().findElements(By.xpath(".//*[@data-qa='task']")).get(order);
        if (OsValidator.isMac()) {
            webElement.sendKeys(Keys.COMMAND + "a");
        } else {
            webElement.sendKeys(Keys.CONTROL + "a");
        }
        webElement.sendKeys(Keys.DELETE);
        webElement.sendKeys(value);
        AbstractUITest.sleep(100);
    }

    public void scrollToTask(Integer order) {
        final WebElement webElement = getElement().findElements(By.xpath(".//*[@data-qa='task']")).get(order);
        getTest().scrollToElement(webElement, false);
        AbstractUITest.sleep(100);
    }

    public void setDate(String value, Integer order) {
        final WebElement webElement = getElement().findElements(By.xpath(".//*[@data-qa='date']")).get(order);
        if (OsValidator.isMac()) {
            webElement.sendKeys(Keys.COMMAND + "a");
        } else {
            webElement.sendKeys(Keys.CONTROL + "a");
        }
        webElement.sendKeys(Keys.DELETE);
        webElement.sendKeys(value);
        AbstractUITest.sleep(50);
    }

    public void scrollToDate(Integer order) {
        final WebElement webElement = getElement().findElements(By.xpath(".//*[@data-qa='date']")).get(order);
        getTest().scrollToElement(webElement, false);
        AbstractUITest.sleep(100);
    }

    public void sortByTask() {
        final WebElement webElement = waitToBeVisible(By.xpath(".//*[@data-qa='sortByTask']"));
        waitToBeClickable(webElement).click();
    }
}
