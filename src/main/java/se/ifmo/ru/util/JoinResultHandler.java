//package se.ifmo.ru.util;
//TODO: add generic handler
//import javax.management.Query;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//public class JoinResultHandler {
//    public static List<?> handle(Query query) {
//        List< > ducks = new ArrayList<>();
//        List<Object[]> objects = ((org.hibernate.query.Query) query).list();
//        Iterator iterator = objects.iterator();
//        while (iterator.hasNext()) {
//            Object[] obj = (Object[]) iterator.next();
//            <T> duck = (Duck) obj[0];
//            ducks.add(duck);
//        }
//        session.close();
//        return ducks;
//    }
//}
