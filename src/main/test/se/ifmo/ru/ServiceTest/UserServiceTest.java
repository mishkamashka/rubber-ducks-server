package se.ifmo.ru.ServiceTest;

import org.junit.Test;
import se.ifmo.ru.model.User;
import se.ifmo.ru.service.UserService;

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
        userService.delete(userService.getByNicknameAndEmail("test_name_email", "test_name_email"));
    }

    @Test
    public void userServiceGetByNicknameTest() {
        UserService userService = new UserService();
        User user = new User("test1", "whatever1@mail.com");
        userService.save(user);
        user = new User("test2", "whatever2@mail.com");
        userService.save(user);
        user = new User("test", "whatever@mail.com");
        userService.save(user);
        List<User> users = userService.getByNickname("test");
        for (User user1 : users) {
            System.out.println(user1.getId() + " " + user1.getNickname());
        }
        userService.delete(userService.getByNicknameAndEmail("test", "whatever@mail.com"));
        userService.delete(userService.getByNicknameAndEmail("test2", "whatever2@mail.com"));
        userService.delete(userService.getByNicknameAndEmail("test1", "whatever1@mail.com"));
    }

    @Test
    public void userServiceGetByFirstNameTest() {
        UserService userService = new UserService();
        User user = new User("test", "whatever@mail.com");
        user.setFirstName("John");
        user.setLastName("Black");
        userService.save(user);
        user = new User("test1", "whatever1@mail.com");
        user.setFirstName("Johny");
        user.setLastName("White");
        userService.save(user);
        List<User> users = userService.getByFirstName("John");
        for (User user1 : users) {
            System.out.println(user1.getId() + " " + user1.getNickname() + " " + user1.getFirstName() + " " + user1.getLastName());
        }
        userService.delete(userService.getByNicknameAndEmail("test", "whatever@mail.com"));
        userService.delete(userService.getByNicknameAndEmail("test1", "whatever1@mail.com"));
    }

    @Test
    public void userServiceGetByLastNameTest() {
        UserService userService = new UserService();
        User user = new User("test", "whatever@mail.com");
        user.setFirstName("John");
        user.setLastName("Black");
        userService.save(user);
        user = new User("test1", "whatever1@mail.com");
        user.setFirstName("John");
        user.setLastName("Blackberry");
        userService.save(user);
        List<User> users = userService.getByLastName("Black");
        for (User user1 : users) {
            System.out.println(user1.getId() + " " + user1.getNickname() + " " + user1.getFirstName() + " " + user1.getLastName());
        }
        userService.delete(userService.getByNicknameAndEmail("test", "whatever@mail.com"));
        userService.delete(userService.getByNicknameAndEmail("test1", "whatever1@mail.com"));
    }

    @Test
    public void userServiceDeleteTest() {
        UserService userService = new UserService();
        User user = userService.getById(1);
        if (user != null)
            userService.delete(user);
    }
}
