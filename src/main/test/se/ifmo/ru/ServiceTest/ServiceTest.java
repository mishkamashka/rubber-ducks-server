package se.ifmo.ru.ServiceTest;

import org.junit.Test;
import se.ifmo.ru.model.User;
import se.ifmo.ru.service.UserService;

public class ServiceTest {

    @Test
    public void userServiceTest() {
        UserService userService = new UserService();
        User user = new User("user","email@mail.em");
        userService.save(user);
    }
}
