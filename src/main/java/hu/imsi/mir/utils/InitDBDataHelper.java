package hu.imsi.mir.utils;

import hu.imsi.mir.dao.entities.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class InitDBDataHelper {

    private static final String MUSEUM = "museum";
    private static final String ROOM = "room";
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
                            case ROOM : obj = dbHelper.createRoom(args[0], args[1], (HMuseum) objectHash.get(args[2]), Integer.valueOf(args[3]), Integer.valueOf(args[4])); break;
            
                            default : break;
                        }

                        objectHash.put(key, obj);
                    } else {
                        break;
                    }
                }
            }

            //HBeacon creation
            for (int i = 1; i < 1000; i++) {
                String key = "o_beacon_" + i;
                String s = (String) props.get(key);
                if (s != null) {
                    String[] args = s.split(",");
                    HBeacon beacon = dbHelper.createBeacon(args[0], args[1], args[2]);
                    objectHash.put(key, beacon);
                } else {
                    break;
                }
            }

/*            for(int i=1;i<1000;i++){
                String key = "o_blnode_"+i;
                String s = (String)props.get(key);
                if(s!=null){
                    String[] args = s.split(",");
                    HBLNode node = dbHelper.createBlNode(args[0], args[1]);
                    objectHash.put(key, node);
                } else {
                    break;
                }
            }

            for(int i=1;i<1000;i++){
                String key = "o_rfidantenna_"+i;
                String s = (String)props.get(key);
                if(s!=null){
                    String[] args = s.split(",");
                    HRFIDAntenna antenna = dbHelper.createHRFIDAntenna(args[0], args[1]);
                    objectHash.put(key, antenna);
                } else {
                    break;
                }
            }

            for(int i=1;i<1000;i++){
                String key = "o_rfidtag_"+i;
                String s = (String)props.get(key);
                if(s!=null){
                    String[] args = s.split(",");
                    HRFIDTag tag = dbHelper.createHRFIDTag(args[0], args[1], args[2]);
                    objectHash.put(key, tag);
                } else {
                    break;
                }
            }

            for(int i=1;i<1000;i++){
                String key = "o_worker_"+i;
                String s = (String)props.get(key);
                if(s!=null){
                    String[] args = s.split(",");
                    HWorker worker = dbHelper.createWorker(args[0], args[1], args[2], args[3], (HBLNode)objectHash.get(args[4]), (HRFIDTag)objectHash.get(args[5]));
                    objectHash.put(key, worker);
                } else {
                    break;
                }
            }

            for(int i=1;i<1000;i++){
                String key = "o_permission_"+i;
                String s = (String)props.get(key);
                if(s!=null){
                    String[] args = s.split(",");
                    if(args.length==6) {
                        HPermission permission = dbHelper.createPermission(args[0], (HRFIDAntenna) objectHash.get(args[1]), (HRFIDTag) objectHash.get(args[2]), (HWorker) objectHash.get(args[3]), Integer.valueOf(args[4]), args[5]);
                    } else if(args.length==4){
                        HPermission permission = dbHelper.createPermission(args[0], (HRFIDAntenna) objectHash.get(args[1]), (HRFIDTag) objectHash.get(args[2]), (HWorker) objectHash.get(args[3]));
                    } else {
                        HPermission permission = dbHelper.createPermission(args[0], (HRFIDAntenna) objectHash.get(args[1]), (HRFIDTag) objectHash.get(args[2]));
                    }
                } else {
                    break;
                }
            }*/

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
