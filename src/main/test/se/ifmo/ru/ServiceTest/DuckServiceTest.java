package se.ifmo.ru.ServiceTest;

import org.junit.Test;
import se.ifmo.ru.model.Duck;
import se.ifmo.ru.model.FeatureSet;
import se.ifmo.ru.service.DuckService;
import se.ifmo.ru.service.FeatureSetService;

import java.util.List;

public class DuckServiceTest {
    
    @Test
    public void duckServiceSaveTest() {
        DuckService duckService = new DuckService();
        FeatureSetService featureSetService = new FeatureSetService();
        FeatureSet featureSet = new FeatureSet();
        featureSetService.save(featureSet);
        Duck duck = new Duck();
        duck.setName("duck_name");
        duck.setFeatureSet(featureSet);
        duckService.save(duck);
    }

    @Test
    public void duckServiceGetTest() {
        DuckService duckService = new DuckService();
        Duck duck = duckService.getById(1);
        System.out.println(duck.toString());
    }

    @Test
    public void duckServiceGetByName() {
        DuckService duckService = new DuckService();
        FeatureSetService featureSetService = new FeatureSetService();
        FeatureSet featureSet = new FeatureSet();
        Duck duck = new Duck();
        duck.setName("duck1");
        duck.setFeatureSet(featureSet);
        featureSetService.save(featureSet);
        duckService.save(duck);
        List <Duck> ducks = duckService.getByName("duck1");
        for (Duck duck1: ducks) {
            System.out.println(duck1.getId() + " " + duck1.getName());
        }
        duckService.delete(duck);
    }
}
