package hu.imsi.mir.utils;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class InitDBDataHelper {

    public static void readProperties(File property) {
        Properties props = new Properties();
        InputStream input = null;

        Map objectHash = new HashMap<String, Object>();

    /*    try {

            input = new FileInputStream(property);
            final InputStreamReader reader = new InputStreamReader(input, StandardCharsets.UTF_8);

            // load a properties file
            props.load(reader);

            //HMuseum creation
            for(int i=1;i<1000;i++){
                String key = "o_museum_"+i;
                String s = (String)props.get(key);
                if(s!=null){
                    String[] args = s.split(",");
                    HMuseum museum = dbHelper.createMuseum(args[0], args[1], args[2], Integer.valueOf(args[3]), args[4], args[5], args[6], args[7], args[8]);
                    objectHash.put(key, museum);
                } else {
                    break;
                }
            }

            //HBeacon creation
            for(int i=1;i<1000;i++){
                String key = "o_beacon_"+i;
                String s = (String)props.get(key);
                if(s!=null){
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
        */
    }
}
