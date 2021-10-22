package com.todo.webapp.page;

import com.todo.AbstractPage;
import com.todo.AbstractUITest;
import com.todo.webapp.form.CreateTodo;
import com.todo.webapp.table.Todos;
import java.util.Optional;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class TodosPage extends AbstractPage {

    public TodosPage(AbstractUITest test) {
        super(test);
    }

    @Override
    public String getPath() {
        return "/todos";
    }

    @Override
    public Optional<ExpectedCondition<WebElement>> getExpectation() {
        return Optional.empty();
    }

    public CreateTodo addTodoSubmit() {
        final WebElement webElement = waitToBeVisible(By.xpath(".//*[@data-qa='addTodo']"));
        waitToBeClickable(webElement).click();
        AbstractUITest.sleep(200);
        return new CreateTodo(getTest(), waitToBeVisible(By.xpath("//*[@data-qa='createTodo']")));
    }

    public void scrollToAddTodo() {
        final WebElement webElement = waitToBeVisible(By.xpath(".//*[@data-qa='addTodo']"));
        getTest().scrollToElement(webElement, false);
        AbstractUITest.sleep(100);
    }

    public Todos getTodos() {
        return new Todos(getTest(), waitToBeVisible(By.xpath(".//*[@data-qa='todos']")));
    }

    public void scrollToTodos() {
        final WebElement webElement = waitToBeVisible(By.xpath(".//*[@data-qa='todos']"));
        getTest().scrollToElement(webElement, false);
        AbstractUITest.sleep(100);
    }

    public CreateTodo getCreateTodo() {
        return new CreateTodo(getTest(), waitToBeVisible(By.xpath("//*[@data-qa='createTodo']")));
    }
}
