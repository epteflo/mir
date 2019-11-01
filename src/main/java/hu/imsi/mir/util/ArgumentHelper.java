package hu.imsi.mir.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ArgumentHelper {

    private static final List<String> ARGUMENTS = new ArrayList<String>();
    static {
        ARGUMENTS.add(Constants.HOST);
        ARGUMENTS.add(Constants.PORT);
        ARGUMENTS.add(Constants.CONFIG);
        ARGUMENTS.add(Constants.DB_INIT_PROPERTIES);
        ARGUMENTS.add(Constants.DB_CLEAR);
    }

    public static HashMap<String,String> createArgsMap(String[] args){
        HashMap<String,String> map = new HashMap<>();
        String key=null;
        String value=null;
        for(int i = 0;i<args.length;i++){
            if( i % 2 == 0){
                key=null;value=null;
                //vezérlő paraméter
                if(!args[i].startsWith("-")) return null;
                key=args[i].substring(1);
            } else {
                // érték
                value=args[i];
                map.put(key,value);
            }
        }
        return map;
    }

    public static void printHelp(){
            System.out.println("Wrong argument(s)!");
            System.out.println("arguments: -port <port> -config <config.properties> [-host <host> -dbInitProperties <dbinit.properties> -dbClear <true|onlyLog|false>]");
    }

    public static boolean checkArgumentKeys(Set<String> keys){
        for(String key : keys){
            if(!ARGUMENTS.contains(key)) return false;
        }
        return true;
    }
}
