package hu.imsi.mir.utils;

import hu.imsi.mir.dao.entities.*;
import liquibase.util.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

public class InitDBDataHelper {

    private static final String MUSEUM = "museum";
    private static final String ROOM = "room";
    private static final String WALL = "wall";
    private static final String DOOR = "door";
    private static final String EXHIBITION = "exhibition";
    private static final String POI = "poi";
    private static final String BEACON = "beacon";
    private static final String LAYOUT = "layout";
    private static final String EXHIBITION_TOUR = "exhibition_tour";
    private static final String EXHIBITION_TOUR_LAYOUT = "exhibition_tour_layout";
    private static final String CONTENT = "content";
    private static final String CONTENT_OBJECT = "content_object";

    private static final List<String> objectDefMap = new ArrayList<>();
    static {
        objectDefMap.add(MUSEUM);
        objectDefMap.add(ROOM);
        objectDefMap.add(WALL);
        objectDefMap.add(DOOR);
        objectDefMap.add(EXHIBITION);
        objectDefMap.add(POI);
        objectDefMap.add(BEACON);
        objectDefMap.add(LAYOUT);
        objectDefMap.add(EXHIBITION_TOUR);
        objectDefMap.add(EXHIBITION_TOUR_LAYOUT);
        objectDefMap.add(CONTENT);
        objectDefMap.add(CONTENT_OBJECT);
    }

    public static void readProperties(String property) {
        File f = new File(property);
        readProperties(f);
    }

    public static Date toDate(String strDate){
        if(StringUtils.isEmpty(strDate)) return null;

        Date date;

        try {
            date = new SimpleDateFormat("yyyy").parse(strDate);
        } catch (Exception _ex1){
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
            } catch (Exception _ex2){
                try {
                    date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strDate);
                } catch (Exception ex){
                    throw new RuntimeException(ex);
                }
            }
        }

        return date;
    }

    public static void readProperties(File property) {
        Properties props = new Properties();
        InputStream input = null;

        Map objectHash = new HashMap<String, Object>();

        DBHelper dbHelper = BeanHelper.getDBHelper();

        try {

            input = new FileInputStream(property);
            final InputStreamReader reader = new InputStreamReader(input, StandardCharsets.UTF_8);

            // load a properties file
            props.load(reader);

            //HMuseum creation
            for (String k : objectDefMap) {
                for (int i = 1; i < 1000; i++) {
                    String key = "o_"+ k + "_" + i;
                    String s = (String) props.get(key);
                    Object obj = null;
                    if (s != null) {
                        String[] args = s.split(",");
                        switch(k) {
                            case MUSEUM : obj = dbHelper.createMuseum(args[0], args[1], args[2], Integer.valueOf(args[3]), args[4], args[5], args[6], args[7], args[8]); break;
                            case ROOM : obj = dbHelper.createRoom(args[0], args[1], (HMuseum) objectHash.get(args[2]), Integer.valueOf(args[3]), Integer.valueOf(args[4]), args[5]); break;
                            case WALL : obj = dbHelper.createWall((HRoom) objectHash.get(args[0]), Integer.valueOf(args[1]), Integer.valueOf(args[2]), Integer.valueOf(args[3]), Integer.valueOf(args[4]), args[5], args[6], Integer.valueOf(args[7])); break;
                            case DOOR : obj = dbHelper.createDoor((HRoom) objectHash.get(args[0]), (HRoom) objectHash.get(args[1]), Integer.valueOf(args[2]), Integer.valueOf(args[3]), Integer.valueOf(args[4]), args[5]); break;
                            case EXHIBITION : obj = dbHelper.createExhibition(args[0], args[1], args[2], args[3], (HMuseum) objectHash.get(args[4])); break;
                            case POI : obj = dbHelper.createPoi(args[0], args[1], args[2], toDate(args[3]), args[4], args[5], args[6], args[7], args[8], args[9], args[10]); break;
                            case BEACON : obj = dbHelper.createBeacon(args[0], args[1], args[2]); break;
                            case LAYOUT : obj = dbHelper.createLayout((HRoom) objectHash.get(args[0]), (HBeacon) objectHash.get(args[1]), (HExhibition) objectHash.get(args[2]), (HPoi) objectHash.get(args[3]), Integer.valueOf(args[4]), Integer.valueOf(args[5])); break;
                            case EXHIBITION_TOUR : obj = dbHelper.createExhibitionTour(args[0], args[1], (HExhibition) objectHash.get(args[2]), args[3]); break;
                            case EXHIBITION_TOUR_LAYOUT : obj = dbHelper.createExhibitionTourLayout((HExhibitionTour) objectHash.get(args[0]),(HLayout) objectHash.get(args[1]), Integer.valueOf(args[2])); break;
                            case CONTENT : obj = dbHelper.createContent(args[0], args[1], args[2], args[3], args[4], args[5], args[6]); break;
                            case CONTENT_OBJECT : obj = dbHelper.createContentObject((HContent) objectHash.get(args[0]), (HMuseum) objectHash.get(args[1]),(HRoom) objectHash.get(args[2]), (HPoi) objectHash.get(args[3]), (HExhibition) objectHash.get(args[4]), (HExhibitionTour) objectHash.get(args[5]), args[6]); break;
                            default : break;
                        }

                        objectHash.put(key, obj);
                    } else {
                        break;
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
