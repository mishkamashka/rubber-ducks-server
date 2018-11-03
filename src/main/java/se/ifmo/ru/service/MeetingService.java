package se.ifmo.ru.service;

import se.ifmo.ru.dao.MeetingDao;
import se.ifmo.ru.model.Meeting;

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

}
