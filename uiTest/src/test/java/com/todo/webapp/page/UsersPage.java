package com.todo.webapp.page;

import com.todo.AbstractPage;
import com.todo.AbstractUITest;
import com.todo.webapp.form.CreateUser;
import com.todo.webapp.table.UserTodos;
import com.todo.webapp.table.Users;
import java.util.Optional;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class UsersPage extends AbstractPage {

    public UsersPage(AbstractUITest test) {
        super(test);
    }

    @Override
    public String getPath() {
        return "/users";
    }

    @Override
    public Optional<ExpectedCondition<WebElement>> getExpectation() {
        return Optional.empty();
    }

    public CreateUser addUserSubmit() {
        final WebElement webElement = waitToBeVisible(By.xpath(".//*[@data-qa='addUser']"));
        waitToBeClickable(webElement).click();
        AbstractUITest.sleep(200);
        return new CreateUser(getTest(), waitToBeVisible(By.xpath("//*[@data-qa='createUser']")));
    }

    public void scrollToAddUser() {
        final WebElement webElement = waitToBeVisible(By.xpath(".//*[@data-qa='addUser']"));
        getTest().scrollToElement(webElement, false);
        AbstractUITest.sleep(100);
    }

    public Users getUsers() {
        return new Users(getTest(), waitToBeVisible(By.xpath(".//*[@data-qa='users']")));
    }

    public void scrollToUsers() {
        final WebElement webElement = waitToBeVisible(By.xpath(".//*[@data-qa='users']"));
        getTest().scrollToElement(webElement, false);
        AbstractUITest.sleep(100);
    }

    public UserTodos getUserTodos() {
        return new UserTodos(getTest(), waitToBeVisible(By.xpath(".//*[@data-qa='userTodos']")));
    }

    public void scrollToUserTodos() {
        final WebElement webElement = waitToBeVisible(By.xpath(".//*[@data-qa='userTodos']"));
        getTest().scrollToElement(webElement, false);
        AbstractUITest.sleep(100);
    }

    public CreateUser getCreateUser() {
        return new CreateUser(getTest(), waitToBeVisible(By.xpath("//*[@data-qa='createUser']")));
    }
}
