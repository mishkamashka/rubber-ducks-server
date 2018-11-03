package se.ifmo.ru.ServiceTest;

import org.junit.Test;
import se.ifmo.ru.model.User;
import se.ifmo.ru.service.UserService;

public class ServiceTest {

    @Test
    public void userServiceSaveTest() {
        UserService userService = new UserService();
        User user = new User("user", "email@mail.em");
        userService.save(user);
    }

    @Test
    public void userServiceGetTest() {
        UserService userService = new UserService();
        User user = userService.getById(1);
        System.out.println(user.toString());
    }
}
