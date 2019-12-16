package hu.imsi.mir.util;

import hu.imsi.mir.services.model.inner.CreateMuseumRequest;
import hu.imsi.mir.services.model.inner.Museum;
import hu.imsi.mir.services.model.ws.WSMuseum;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class MapperHelper {

    public static <S,T> void map(S source, T target){
        Class<?> objClass = target.getClass();
        for (Field field : objClass.getDeclaredFields()) {

            Class c = field.getType();

            // Ha a típus benne van a dispatcher-ben akkor rekurzívan meghívjuk
            if(MapPairs.MAPPER_DISPATCHER.containsKey(c)){

            } else {

                Object objField = null;
                try {
                    // Invoke the getter method on the Institution1 object.
                    objField = new PropertyDescriptor(field.getName(),
                            source.getClass()).getReadMethod().invoke(source);

                    // Invoke the setter method on the Institution2 object.
                    new PropertyDescriptor(field.getName(), target.getClass())
                            .getWriteMethod().invoke(target, objField);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IntrospectionException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args){
        Museum m = new Museum();
        m.setAddress("address");
        m.setCuriosity("curiosity");
        m.setName("mittom");

        WSMuseum wm = new WSMuseum();

        map(m, wm);
    }
}
