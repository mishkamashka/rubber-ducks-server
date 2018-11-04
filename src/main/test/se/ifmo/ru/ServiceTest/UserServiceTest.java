package se.ifmo.ru.ServiceTest;

import org.junit.Test;
import org.postgresql.util.PSQLException;
import se.ifmo.ru.model.User;
import se.ifmo.ru.service.UserService;

import javax.validation.ConstraintViolationException;
import java.util.List;

public class UserServiceTest {

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

    @Test
    public void userServiceGetByNicknameAndEmailTest() {
        UserService userService = new UserService();
        User user = userService.getByNicknameAndEmail("test_name_email", "test_name_email");
        System.out.println(user.getId() + " " + user.getNickname());
    }

    @Test
    public void userServiceGetByNicknameTest() {
        UserService userService = new UserService();
        User user = new User("test1", "whatever@mail.com");
        userService.save(user);
        user = new User("test2", "whatever1@mail.com");
        userService.save(user);
        user = new User("test", "whatever2@mail.com");
        userService.save(user);
        List<User> users = userService.getByNickname("test");
        for (User user1 : users) {
            System.out.println(user1.getId() + " " + user1.getNickname());
        }
        userService.delete(userService.getByNicknameAndEmail("test1", "whatever@mail.com"));
        userService.delete(userService.getByNicknameAndEmail("test2", "whatever1@mail.com"));
        userService.delete(userService.getByNicknameAndEmail("test", "whatever2@mail.com"));
    }

    @Test
    public void userServiceDeleteTest() {
        UserService userService = new UserService();
        User user = userService.getById(1);
        if (user != null)
            userService.delete(user);
    }
}
