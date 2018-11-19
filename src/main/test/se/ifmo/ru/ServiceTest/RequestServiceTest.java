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

public class RequestServiceTest {

    @Test
    public void requestServiceSaveAndGetByIdTest() {
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
        RequestService requestService = new RequestService();
        Request request = new Request(user, duck);
        user.getRequests().add(request);
        duck.getRequests().add(request);
        requestService.save(request);

        Request request1 = requestService.getById(request.getId());
        System.out.println(request1.getId() + " " + request1.getUser().getNickname() + " " + request1.getDuck().getName());
        assertEquals(request, request1);
        userService.delete(user);
    }

    @Test
    public void requestServiceGetByUserIdTest() {
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
        RequestService requestService = new RequestService();
        Request request = new Request(user, duck);
        user.getRequests().add(request);
        duck.getRequests().add(request);
        requestService.save(request);

        List<Request> requests = requestService.getByUserId(user.getId());
        for (Request request1 : requests) {
            System.out.println(request1.getId());
        }
        userService.delete(user);
    }

    @Test
    public void requestServiceGetByDuckIdTest() {
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
        RequestService requestService = new RequestService();
        Request request = new Request(user, duck);
        user.getRequests().add(request);
        duck.getRequests().add(request);
        requestService.save(request);

        List<Request> requests = requestService.getByDuckId(user.getId());
        for (Request request1 : requests) {
            System.out.println(request1.getId());
        }
        userService.delete(user);
    }

    @Test
    public void requestServiceGetByIsApprovedTest() {
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
        RequestService requestService = new RequestService();
        Request request = new Request(user, duck);
        request.setApproved(true);
        user.getRequests().add(request);
        duck.getRequests().add(request);
        requestService.save(request);

        List<Request> requests = requestService.getByIsApproved(true);
        for (Request request1 : requests) {
            System.out.println(request1.getId());
        }
        userService.delete(user);
    }
}
