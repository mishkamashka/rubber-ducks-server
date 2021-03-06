package se.ifmo.ru.ServiceTest;

import org.junit.Test;
import se.ifmo.ru.model.Duck;
import se.ifmo.ru.model.FeatureSet;
import se.ifmo.ru.model.Request;
import se.ifmo.ru.model.User;
import se.ifmo.ru.service.DuckService;
import se.ifmo.ru.service.FeatureSetService;
import se.ifmo.ru.service.RequestService;
import se.ifmo.ru.service.UserService;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class DuckServiceTest {

    @Test
    public void duckServiceSaveAndGetByIdTest() {

        UserService userService = new UserService();
        User user = new User("test", "email");
        userService.save(user);
        DuckService duckService = new DuckService();
        Duck duck = new Duck();
        FeatureSet featureSet = new FeatureSet();

        duck.setName("duck_name");

        duck.setFeatureSet(featureSet);

        duck.setOwner(user);

        user.getDucks().add(duck);
        duckService.save(duck);

        Duck duck1 = duckService.getById(duck.getId());
        System.out.println(duck1.getId() + " " + duck1.getName());
        assertEquals(duck1, duck);
        userService.delete(user);
    }

    @Test
    public void duckServiceSaveAndGetByIdWithOwnerAndFeatureSetTest() {
        UserService userService = new UserService();
        User user = new User("test", "email");
        userService.save(user);
        DuckService duckService = new DuckService();
        FeatureSet featureSet = new FeatureSet();
        Duck duck = new Duck();
        duck.setName("duck_name");
        duck.setFeatureSet(featureSet);
        duck.setOwner(user);
        user.getDucks().add(duck);
        duckService.save(duck);
        Duck duck1 = duckService.getById(duck.getId());
        System.out.println(duck1.getId() + " " + duck1.getName() + " " + duck1.getOwner().getNickname());
        assertEquals(duck1, duck);
        userService.delete(user);
    }

    @Test
    public void duckServiceSaveAndGetByIdWithRequestsTest() {
        UserService userService = new UserService();
        User user = new User("test", "email");
        userService.save(user);
        DuckService duckService = new DuckService();
        FeatureSet featureSet = new FeatureSet();
        Duck duck = new Duck();
        duck.setName("duck_name");
        duck.setFeatureSet(featureSet);
        duck.setOwner(user);
        user.getDucks().add(duck);
        duckService.save(duck);
        RequestService requestService = new RequestService();
        Request request = new Request(user, duck);
        user.getRequests().add(request);
        duck.getRequests().add(request);
        requestService.save(request);

        Duck duck1 = duckService.getById(duck.getId());
        System.out.println(duck1.getId() + " " + duck1.getName() + " " + duck1.getRequests().get(0));
        assertEquals(duck1, duck);
        userService.delete(user);
    }

    @Test
    public void duckServiceGetByName() {
        UserService userService = new UserService();
        User user = new User("test user", "test email");
        userService.save(user);
        DuckService duckService = new DuckService();
        FeatureSet featureSet = new FeatureSet();
        Duck duck = new Duck();
        duck.setName("duck_name");
        duck.setFeatureSet(featureSet);
        duck.setOwner(user);
        user.getDucks().add(duck);
        duckService.save(duck);
        List <Duck> ducks = duckService.getByName("duck");
        for (Duck duck1: ducks) {
            System.out.println(duck1.getId() + " " + duck1.getName());
        }
        userService.delete(user);
    }

    @Test
    public void duckServiceGetByNameWithOwnerAndFeatureSet() {
        UserService userService = new UserService();
        User user = new User("test user", "test email");
        userService.save(user);
        DuckService duckService = new DuckService();
        FeatureSet featureSet = new FeatureSet();
        Duck duck = new Duck();
        duck.setName("duck_name");
        duck.setFeatureSet(featureSet);
        duck.setOwner(user);
        user.getDucks().add(duck);
        duckService.save(duck);
        List <Duck> ducks = duckService.getByName("duck");
        for (Duck duck1: ducks) {
            System.out.println(duck1.getId() + " " + duck1.getName()+ " " + duck1.getOwner().getNickname() +
                    " " + duck1.getFeatureSet().getId());
        }
        userService.delete(user);
    }

    @Test
    public void duckGetAll() {
        UserService userService = new UserService();
        User user = new User("test user", "test email");
        userService.save(user);
        DuckService duckService = new DuckService();
        FeatureSet featureSet = new FeatureSet();
        Duck duck = new Duck();
        duck.setName("duck_name");
        duck.setFeatureSet(featureSet);
        duck.setOwner(user);
        user.getDucks().add(duck);
        duckService.save(duck);
        List<Duck> ducks = duckService.getAll();
        for (Duck duck1 : ducks) {
            System.out.println(duck1.getId() + " " + duck1.getName());
        }
        userService.delete(user);
    }

    @Test
    public void duckGetAllWithOwnerAndFeatureSetTest() {
        UserService userService = new UserService();
        User user = new User("test user", "test email");
        userService.save(user);
        DuckService duckService = new DuckService();
        FeatureSet featureSet = new FeatureSet();
        Duck duck = new Duck();
        duck.setName("duck_name");
        duck.setFeatureSet(featureSet);
        duck.setOwner(user);
        user.getDucks().add(duck);
        duckService.save(duck);
        List<Duck> ducks = duckService.getAll();
        for (Duck duck1 : ducks) {
            System.out.println(duck1.getId() + " " + duck1.getName() + " " + duck1.getOwner().getNickname() +
                    " " + duck1.getFeatureSet().getId());
        }
        userService.delete(user);
    }

    @Test
    public void duckGetByOwnerId() {
        UserService userService = new UserService();
        User user = new User("test user", "test email");
        userService.save(user);
        DuckService duckService = new DuckService();
        FeatureSet featureSet = new FeatureSet();
        Duck duck = new Duck();
        duck.setName("duck_name");
        duck.setFeatureSet(featureSet);
        duck.setOwner(user);
        user.getDucks().add(duck);
        duckService.save(duck);
        duck = new Duck();
        duck.setName("duck_name1");
        featureSet = new FeatureSet();
        duck.setFeatureSet(featureSet);
        duck.setOwner(user);
        user.getDucks().add(duck);
        duckService.save(duck);
        List<Duck> ducks = duckService.getByOwnerId(user.getId());
        for (Duck duck1 : ducks) {
            System.out.println(duck1.getId() + " " + duck1.getName());
        }
        userService.delete(user);
    }

}
