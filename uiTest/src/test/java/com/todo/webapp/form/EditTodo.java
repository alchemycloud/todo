package com.todo.webapp.form;

import com.todo.AbstractComponent;
import com.todo.AbstractUITest;
import com.todo.util.OsValidator;
import com.todo.webapp.dropdown.StatusDropDown;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class EditTodo extends AbstractComponent {

    public EditTodo(AbstractUITest test, WebElement element) {
        super(test, element);
    }

    public void setUserId(Integer value) {
        final WebElement webElement = waitToBeVisible(By.xpath(".//*[@data-qa='userId']"));
        if (OsValidator.isMac()) {
            webElement.sendKeys(Keys.COMMAND + "a");
        } else {
            webElement.sendKeys(Keys.CONTROL + "a");
        }
        webElement.sendKeys(Keys.DELETE);
        webElement.sendKeys(value.toString());
        AbstractUITest.sleep(100);
    }

    public void scrollToUserId() {
        final WebElement webElement = waitToBeVisible(By.xpath(".//*[@data-qa='userId']"));
        getTest().scrollToElement(webElement, false);
        AbstractUITest.sleep(100);
    }

    public void setTask(String value) {
        final WebElement webElement = waitToBeVisible(By.xpath(".//*[@data-qa='task']"));
        if (OsValidator.isMac()) {
            webElement.sendKeys(Keys.COMMAND + "a");
        } else {
            webElement.sendKeys(Keys.CONTROL + "a");
        }
        webElement.sendKeys(Keys.DELETE);
        webElement.sendKeys(value);
        AbstractUITest.sleep(100);
    }

    public void scrollToTask() {
        final WebElement webElement = waitToBeVisible(By.xpath(".//*[@data-qa='task']"));
        getTest().scrollToElement(webElement, false);
        AbstractUITest.sleep(100);
    }

    public void setDate(String value) {
        final WebElement webElement = waitToBeVisible(By.xpath(".//*[@data-qa='date']"));
        if (OsValidator.isMac()) {
            webElement.sendKeys(Keys.COMMAND + "a");
        } else {
            webElement.sendKeys(Keys.CONTROL + "a");
        }
        webElement.sendKeys(Keys.DELETE);
        webElement.sendKeys(value);
        AbstractUITest.sleep(50);
    }

    public void scrollToDate() {
        final WebElement webElement = waitToBeVisible(By.xpath(".//*[@data-qa='date']"));
        getTest().scrollToElement(webElement, false);
        AbstractUITest.sleep(100);
    }

    public StatusDropDown getStatus() {
        return new StatusDropDown(getTest(), waitToBeVisible(By.xpath(".//*[@data-qa='status']")));
    }

    public void scrollToStatus() {
        final WebElement webElement = waitToBeVisible(By.xpath(".//*[@data-qa='status']"));
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
