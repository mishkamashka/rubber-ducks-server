package se.ifmo.ru.service;

import se.ifmo.ru.dao.MeetingDao;
import se.ifmo.ru.model.Meeting;

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

    //TODO: test
    public List<Meeting> getByName(String name) {
        return meetingDao.getByName(name);
    }

    //TODO: test
    public List<Meeting> getByPlaceId(long placeId) {
        return meetingDao.getByPlaceId(placeId);
    }

    //TODO: test
    public List<Meeting> getByDate(Date date) {
        return meetingDao.getByDate(date);
    }

    //TODO: test
    public Meeting getByPlaceIdAndDate(long placeId, Date date) {
        return meetingDao.getByPlaceIdAndDate(placeId, date);
    }
}
