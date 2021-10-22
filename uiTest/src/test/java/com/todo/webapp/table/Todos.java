package com.todo.webapp.table;

import com.todo.AbstractComponent;
import com.todo.AbstractUITest;
import com.todo.util.OsValidator;
import com.todo.webapp.form.DeleteTodo;
import com.todo.webapp.form.EditTodo;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class Todos extends AbstractComponent {

    public Todos(AbstractUITest test, WebElement element) {
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

    public void setUserUsername(String value, Integer order) {
        final WebElement webElement = getElement().findElements(By.xpath(".//*[@data-qa='userUsername']")).get(order);
        if (OsValidator.isMac()) {
            webElement.sendKeys(Keys.COMMAND + "a");
        } else {
            webElement.sendKeys(Keys.CONTROL + "a");
        }
        webElement.sendKeys(Keys.DELETE);
        webElement.sendKeys(value);
        AbstractUITest.sleep(100);
    }

    public void scrollToUserUsername(Integer order) {
        final WebElement webElement = getElement().findElements(By.xpath(".//*[@data-qa='userUsername']")).get(order);
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

    public EditTodo editTodoSubmit(Integer order) {
        final WebElement webElement = getElement().findElements(By.xpath(".//*[@data-qa='editTodo']")).get(order);
        waitToBeClickable(webElement).click();
        AbstractUITest.sleep(200);
        return new EditTodo(getTest(), waitToBeVisible(By.xpath("//*[@data-qa='editTodo']")));
    }

    public void scrollToEditTodo(Integer order) {
        final WebElement webElement = getElement().findElements(By.xpath(".//*[@data-qa='editTodo']")).get(order);
        getTest().scrollToElement(webElement, false);
        AbstractUITest.sleep(100);
    }

    public DeleteTodo deleteTodoSubmit(Integer order) {
        final WebElement webElement = getElement().findElements(By.xpath(".//*[@data-qa='deleteTodo']")).get(order);
        waitToBeClickable(webElement).click();
        AbstractUITest.sleep(200);
        return new DeleteTodo(getTest(), waitToBeVisible(By.xpath("//*[@data-qa='deleteTodo']")));
    }

    public void scrollToDeleteTodo(Integer order) {
        final WebElement webElement = getElement().findElements(By.xpath(".//*[@data-qa='deleteTodo']")).get(order);
        getTest().scrollToElement(webElement, false);
        AbstractUITest.sleep(100);
    }

    public EditTodo getEditTodo() {
        return new EditTodo(getTest(), waitToBeVisible(By.xpath("//*[@data-qa='editTodo']")));
    }

    public DeleteTodo getDeleteTodo() {
        return new DeleteTodo(getTest(), waitToBeVisible(By.xpath("//*[@data-qa='deleteTodo']")));
    }
}
