package se.ifmo.ru.ServiceTest;

import org.junit.Test;
import se.ifmo.ru.model.Duck;
import se.ifmo.ru.model.FeatureSet;
import se.ifmo.ru.model.User;
import se.ifmo.ru.service.DuckService;
import se.ifmo.ru.service.FeatureSetService;
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
        FeatureSetService featureSetService = new FeatureSetService();
        FeatureSet featureSet = new FeatureSet();
        featureSetService.save(featureSet);
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
    public void duckServiceGetByName() {
        UserService userService = new UserService();
        User user = new User("test user", "test email");
        userService.save(user);
        DuckService duckService = new DuckService();
        FeatureSetService featureSetService = new FeatureSetService();
        FeatureSet featureSet = new FeatureSet();
        featureSetService.save(featureSet);
        Duck duck = new Duck();
        duck.setName("duck_name");
        duck.setFeatureSet(featureSet);
        duck.setOwner(user);
        user.getDucks().add(duck);
        duckService.save(duck);
        List <Duck> ducks = duckService.getByName("duck");
        for (Duck duck1: ducks) {
            System.out.println(duck1.getId() + " " + duck1.getName() + " " + duck1.getOwner().getNickname() +
                    " " + duck1.getFeatureSet().getId());
        }
        duckService.delete(duck);
    }

    @Test
    public void eventGetAll() {
        UserService userService = new UserService();
        User user = new User("test user", "test email");
        userService.save(user);
        DuckService duckService = new DuckService();
        FeatureSetService featureSetService = new FeatureSetService();
        FeatureSet featureSet = new FeatureSet();
        featureSetService.save(featureSet);
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
}
