package se.ifmo.ru.ServiceTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import se.ifmo.ru.model.*;
import se.ifmo.ru.service.*;

import java.util.List;

public class UserServiceTest {

    @Test
    public void userServiceSaveAndGetByIdTest() {
        UserService userService = new UserService();
        User user = new User("user", "email@mail.em");
        userService.save(user);

        Duck duck = new Duck();
        duck.setName("ducky");
        FeatureSet featureSet = new FeatureSet();
        duck.setFeatureSet(featureSet);
        user.addDuck(duck);

        user = userService.getById(user.getId());
        System.out.println(user.getId() + " " + user.getNickname());
        userService.delete(user);
    }

    @Test
    public void userServiceSaveAndGetByIdWithDucksTest() {
        UserService userService = new UserService();
        User user = new User("user", "email@mail.em");
        userService.save(user);

        Duck duck = new Duck();
        duck.setName("ducky");
        FeatureSet featureSet = new FeatureSet();
        duck.setFeatureSet(featureSet);
        user.addDuck(duck);

        duck = new Duck();
        duck.setName("another_duck");
        featureSet = new FeatureSet();
        duck.setFeatureSet(featureSet);
        user.addDuck(duck);

        user = userService.getByIdWithDucks(user.getId());
        for (int i = 0; i < user.getDucks().size(); i++) {
            System.out.println(user.getId() + " " + user.getNickname() + " " + user.getDucks().get(i).getName());
        }
        userService.delete(user);
    }

    @Test
    public void userServiceGetDucksTest() {
        UserService userService = new UserService();
        User user = new User("user", "email@mail.em");
        userService.save(user);

        Duck duck = new Duck();
        duck.setName("ducky");
        FeatureSet featureSet = new FeatureSet();
        duck.setFeatureSet(featureSet);
        user.addDuck(duck);

        Duck duck1 = new Duck();
        duck1.setName("another_duck");
        featureSet = new FeatureSet();
        duck1.setFeatureSet(featureSet);
        user.addDuck(duck1);

        List<Duck> ducks = userService.getDucks(user);
        for (Duck duck2 : ducks) {
            System.out.println(duck2.getId() + " " + duck2.getName());
        }
        userService.delete(userService.getById(user.getId()));
    }

    @Test
    public void userServiceSaveAndGetByIdWithAttendingEventsTest() {
        UserService userService = new UserService();
        User user = new User("user", "email@mail.em");
        userService.save(user);

        EventService eventService = new EventService();
        Event event = new Event("Another Event");
        event.setDate("12-09-2020-13-00");
        PlaceService placeService = new PlaceService();
        Place place = new Place("Place to Be");
        placeService.save(place);
        event.setPlace(place);
        eventService.save(event);

        user.getAttendingEvents().add(event);
        event.getParticipants().add(user);
        userService.update(user);

        user = userService.getByIdWithAttendingEvents(user.getId());
        for (int i = 0; i < user.getAttendingEvents().size(); i++) {
            System.out.println(user.getId() + " " + user.getNickname() + " " + user.getAttendingEvents().get(0).getName());
        }
        userService.delete(user);
        eventService.delete(event);
        placeService.delete(place);
    }

    @Test
    public void userServiceSaveAndGetByIdWithOrganizedEventsTest() {
        UserService userService = new UserService();
        User user = new User("user", "email@mail.em");
        userService.save(user);

        EventService eventService = new EventService();
        Event event = new Event("Another Event");
        event.setDate("12-09-2020-13-00");
        PlaceService placeService = new PlaceService();
        Place place = new Place("Place to Be");
        placeService.save(place);
        event.setPlace(place);
        eventService.save(event);

        user.getOrganizedEvents().add(event);
        event.getOrganizers().add(user);

        userService.update(user);

        user = userService.getByIdWithOrganizedEvents(user.getId());
        for (int i = 0; i < user.getOrganizedEvents().size(); i++) {
            System.out.println(user.getId() + " " + user.getNickname() + " " + user.getOrganizedEvents().get(0).getName());
        }
        userService.delete(user);
        eventService.delete(event);
        placeService.delete(place);
    }

    @Test
    public void userServiceGetByNicknameAndEmailTest() {
        UserService userService = new UserService();
        User user = new User("user", "email@mail.em");
        userService.save(user);
        DuckService duckService = new DuckService();
        Duck duck = new Duck();
        duck.setName("ducky");
        FeatureSetService featureSetService = new FeatureSetService();
        FeatureSet featureSet = new FeatureSet();
        featureSetService.save(featureSet);
        duck.setFeatureSet(featureSet);
        duck.setOwner(user);
        user.getDucks().add(duck);
        duckService.save(duck);
        duck = new Duck();
        duck.setName("another_duck");
        featureSet = new FeatureSet();
        featureSetService.save(featureSet);
        duck.setFeatureSet(featureSet);
        duck.setOwner(user);
        user.getDucks().add(duck);
        duckService.save(duck);
        User user1 = userService.getByNicknameAndEmail("user", "email@mail.em");
        System.out.println(user1.getId() + " " + user1.getNickname());
        userService.delete(user);
    }

    @Test
    public void userServiceGetByNicknameAndEmailWithDucksAndRequestsTest() {
        UserService userService = new UserService();
        User user = new User("user", "email@mail.em");
        userService.save(user);
        DuckService duckService = new DuckService();
        Duck duck = new Duck();
        duck.setName("ducky");
        FeatureSetService featureSetService = new FeatureSetService();
        FeatureSet featureSet = new FeatureSet();
        featureSetService.save(featureSet);
        duck.setFeatureSet(featureSet);
        duck.setOwner(user);
        user.getDucks().add(duck);
        duckService.save(duck);
        duck = new Duck();
        duck.setName("another_duck");
        featureSet = new FeatureSet();
        featureSetService.save(featureSet);
        duck.setFeatureSet(featureSet);
        duck.setOwner(user);
        user.getDucks().add(duck);
        duckService.save(duck);
        User user1 = userService.getByNicknameAndEmailWithDucksAndRequests("user", "email@mail.em");
        System.out.println(user1.getId() + " " + user1.getNickname() + " " + user1.getDucks().get(0).getName());
        userService.delete(user);
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
    public void userServiceGetByFirstNameAndLastNameTest() {
        UserService userService = new UserService();
        User user = new User("test", "whatever@mail.com");
        user.setFirstName("John");
        user.setLastName("Black");
        userService.save(user);
        user = new User("test1", "whatever1@mail.com");
        user.setFirstName("Johny");
        user.setLastName("White");
        user = new User("test2", "whatever2@mail.com");
        user.setFirstName("John");
        user.setLastName("Black");
        userService.save(user);
        List<User> users = userService.getByFirstNameAndLastName("John","Black");
        for (User user1 : users) {
            System.out.println(user1.getId() + " " + user1.getNickname() + " " + user1.getFirstName() + " " + user1.getLastName());
        }
        userService.delete(userService.getByNicknameAndEmail("test", "whatever@mail.com"));
        userService.delete(userService.getByNicknameAndEmail("test1", "whatever1@mail.com"));
        userService.delete(userService.getByNicknameAndEmail("test2", "whatever2@mail.com"));
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

    @Test
    public void userAddDuckTest() {
        UserService userService = new UserService();
        User user = new User("user", "email@mail.em");
        userService.save(user);

        Duck duck = new Duck();
        duck.setName("ducky");
        FeatureSetService featureSetService = new FeatureSetService();
        FeatureSet featureSet = new FeatureSet();
        featureSetService.save(featureSet);
        duck.setFeatureSet(featureSet);

        user.addDuck(duck);

        duck = new Duck();
        duck.setName("another_duck");
        featureSet = new FeatureSet();
        featureSetService.save(featureSet);
        duck.setFeatureSet(featureSet);


        user.addDuck(duck);

        user = userService.getByIdWithDucksAndRequests(user.getId());
        for (int i = 0; i < user.getDucks().size(); i++) {
            System.out.println(user.getId() + " " + user.getNickname() + " " + user.getDucks().get(i).getName());
        }
        userService.delete(user);
    }

    @Test
    public void userServiceGetByGenderTest() {
        UserService userService = new UserService();
        User user = new User("user", "email@mail.em");
        user.setGender('f');
        userService.save(user);

        user = new User("name1", "email1");
        user.setGender('m');
        userService.save(user);

        user = new User("name2", "email2");
        user.setGender('f');
        userService.save(user);

        List<User> users = userService.getByGender('f');
        for(User user1 : users) {
            System.out.println(user1.getId() + " " + user1.getNickname() + " " + user1.getGender());
            userService.delete(user1);
        }
    }
}
