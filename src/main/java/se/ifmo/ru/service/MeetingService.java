package se.ifmo.ru.service;

import org.joda.time.DateTime;
import se.ifmo.ru.dao.MeetingDao;
import se.ifmo.ru.model.Meeting;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MeetingService {
    
    private MeetingDao meetingDao = new MeetingDao();

    public Meeting getById(long id) {
        return meetingDao.getById(id);
    }

    public void save(Meeting meeting) {
        meetingDao.save(meeting);
    }

    public void update(Meeting meeting) {
        meetingDao.update(meeting);
    }

    public void delete(Meeting meeting) {
        meetingDao.delete(meeting);
    }

    public List<Meeting> getAll() {
        return meetingDao.getAll();
    }
    
    public List<Meeting> getByName(String name) {
        return meetingDao.getByName(name);
    }

    public List<Meeting> getByPlaceId(long placeId) {
        return meetingDao.getByPlaceId(placeId);
    }

    public List<Meeting> getByDate(Date date) {
        MeetingService meetingService = new MeetingService();
        List<Meeting> meetings = meetingService.getAll();
        List<Meeting> result = new ArrayList<>();
        DateTime reqDate = new DateTime(date);
        DateTime meetingDate;
        for (Meeting meeting1: meetings) {
            meetingDate = new DateTime(meeting1.getDate());
            if (reqDate.equals(meetingDate)) {
                result.add(meeting1);
            }
        }
        return result;
    }

    public Meeting getByPlaceIdAndDate(long placeId, Date date) {
        MeetingService meetingService = new MeetingService();
        List<Meeting> meetings = meetingService.getAll();
        List<Meeting> result = new ArrayList<>();
        DateTime reqDate = new DateTime(date);
        DateTime meetingDate;
        for (Meeting meeting1: meetings) {
            meetingDate = new DateTime(meeting1.getDate());
            if (reqDate.equals(meetingDate) && meeting1.getPlace().getId() == placeId) {
                result.add(meeting1);
            }
        }
        return result.size() > 0 ? result.get(0) : null;
    }
}
