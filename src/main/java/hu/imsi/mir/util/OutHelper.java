package hu.imsi.mir.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.imsi.mir.spring.hibernate.model.HMuseum;

import java.text.SimpleDateFormat;
import java.util.List;

import static java.lang.System.exit;

public class OutHelper {

    public static String printObject(Object object){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String s = objectMapper.writeValueAsString(object);
            //System.out.println(s);
            return s;
        } catch (Exception e){
            System.out.println("Error at print object!");
            e.printStackTrace();
            exit(-1);
        }
        return null;
    }

    public static String printHMuseums(List<HMuseum> hMuseums){
        StringBuffer s = new StringBuffer();
        s.append("{\"Museums\":[\n");
        for(HMuseum hrfidTag : hMuseums) {
            s.append(printHMuseum(hrfidTag));
            s.append(",\n");
        }
        if(hMuseums!=null && hMuseums.size()>0) s.deleteCharAt(s.length()-2);
        s.append("]}");
        return s.toString();
    }

    public static String printHMuseum(HMuseum hMuseum){
        return printObject(hMuseum);
    }



}
