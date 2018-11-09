package se.ifmo.ru.ServiceTest;

import org.junit.Test;
import se.ifmo.ru.model.Meeting;
import se.ifmo.ru.model.Place;
import se.ifmo.ru.service.MeetingService;
import se.ifmo.ru.service.PlaceService;
import se.ifmo.ru.util.DateFormatter;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class MeetingServiceTest {

    @Test
    public void meetingServiceSaveTest() {
        MeetingService meetingService = new MeetingService();
        Meeting meeting = new Meeting("Duck Race");
        meeting.setDate("12-11-2020-15-00");
        PlaceService placeService = new PlaceService();
        Place place = new Place("Awesome restaurant");
        placeService.save(place);
        meeting.setPlace(place);
        meetingService.save(meeting);
        assertEquals(meetingService.getById(meeting.getId()), meeting);
        meetingService.delete(meeting);
        placeService.delete(place);
    }

    @Test
    public void meetingServiceSaveAndGetByIdTest() {
        MeetingService meetingService = new MeetingService();
        Meeting meeting = new Meeting("Another Meeting");
        meeting.setDate("12-09-2020-13-00");
        PlaceService placeService = new PlaceService();
        Place place = new Place("Place to Be");
        placeService.save(place);
        meeting.setPlace(place);
        meetingService.save(meeting);
        assertEquals(meetingService.getById(meeting.getId()), meeting);
        meetingService.delete(meeting);
    }

    @Test
    public void meetingGetByNameTest() {
        MeetingService meetingService = new MeetingService();
        Meeting meeting = new Meeting("My Meeting");
        meeting.setDate("12-09-2020-13-00");
        PlaceService placeService = new PlaceService();
        Place place = new Place("New place");
        placeService.save(place);
        meeting.setPlace(place);
        meetingService.save(meeting);
        Meeting meeting2 = new Meeting("Mysterious Meeting");
        meeting2.setDate("12-09-2020-14-00");
        Place place2 = new Place("One more");
        placeService.save(place2);
        meeting2.setPlace(place2);
        meetingService.save(meeting2);
        List<Meeting> meetings = meetingService.getByName("My");
        for (Meeting meeting1 : meetings) {
            System.out.println(meeting1.getId() + " " + meeting1.getName());
        }
        meetingService.delete(meeting);
        meetingService.delete(meeting2);
        placeService.delete(place);
        placeService.delete(place2);
    }

    @Test
    public void meetingGetByDate() {
        MeetingService meetingService = new MeetingService();
        Meeting meeting = new Meeting("My Meeting");
        meeting.setDate("12-09-2020-10-00");
        PlaceService placeService = new PlaceService();
        Place place = new Place("Super Cafe");
        placeService.save(place);
        meeting.setPlace(place);
        meetingService.save(meeting);
        List<Meeting> meetings = meetingService.getByDate(DateFormatter.stringToDate("12-09-2020-10-00"));
        for (Meeting meeting1 : meetings) {
            System.out.println(meeting1.getId() + " " + meeting1.getName() + " " + meeting1.getDate() + " " + meeting1.getPlace().getName());
        }
        meetingService.delete(meeting);
        placeService.delete(place);
    }

    @Test
    public void meetingGetAll() {
        MeetingService meetingService = new MeetingService();
        Meeting meeting = new Meeting("My Meeting");
        meeting.setDate("12-09-2020-10-00");
        PlaceService placeService = new PlaceService();
        Place place = new Place("Super cafe");
        placeService.save(place);
        meeting.setPlace(place);
        meetingService.save(meeting);
        List<Meeting> meetings = meetingService.getAll();
        for (Meeting meeting1 : meetings) {
            Place place1 = meeting1.getPlace();
            System.out.println(meeting1.getId() + " " + meeting1.getName() + " " + place1.getId());
        }
        meetingService.delete(meeting);
        placeService.delete(place);
    }

    @Test
    public void meetingGetByPlaceId() {
        MeetingService meetingService = new MeetingService();
        Meeting meeting = new Meeting("My Meeting");
        meeting.setDate("12-09-2020-10-00");
        PlaceService placeService = new PlaceService();
        Place place = new Place("Super cafe");
        placeService.save(place);
        meeting.setPlace(place);
        meetingService.save(meeting);
        List<Meeting> meetings = meetingService.getByPlaceId(place.getId());
        for (Meeting meeting1 : meetings) {
            Place place1 = meeting1.getPlace();
            System.out.println(meeting1.getId() + " " + meeting1.getName() + " " + place1.getId());
        }
        meetingService.delete(meeting);
        placeService.delete(place);
    }

    @Test
    public void meetingGetByPlaceIdAndDate() {
        MeetingService meetingService = new MeetingService();
        Meeting meeting = new Meeting("My Meeting");
        meeting.setDate("12-09-2020-10-00");
        PlaceService placeService = new PlaceService();
        Place place = new Place("Super cafe");
        placeService.save(place);
        meeting.setPlace(place);
        meetingService.save(meeting);
        Meeting meeting1 = meetingService.getByPlaceIdAndDate(place.getId(), DateFormatter.stringToDate("12-09-2020-10-00"));
        System.out.println(meeting1.getId() + " " + meeting1.getName() + " " + meeting1.getPlace().getName() + " " + meeting1.getDate() + " " + meeting1.getPlace().getName());
        meetingService.delete(meeting);
    }
}
