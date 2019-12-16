package hu.imsi.mir.util;

import hu.imsi.mir.services.model.inner.CreateMuseumRequest;
import hu.imsi.mir.services.model.inner.Message;
import hu.imsi.mir.services.model.inner.MessageList;
import hu.imsi.mir.services.model.inner.Museum;
import hu.imsi.mir.services.model.ws.WSMessageList;
import hu.imsi.mir.services.model.ws.WSMuseum;

import javax.ws.rs.core.MultivaluedMap;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class MapperHelper {

    public static <S,T> void map(S source, T target){
        Class<?> objClass = target.getClass();
        for (Field field : objClass.getDeclaredFields()) {

            Class c = field.getType();
            Object objField = null;
            try {
            // Ha a típus benne van a dispatcher-ben akkor rekurzívan meghívjuk
            if(MapPairs.MAPPER_DISPATCHER.containsKey(c)){

            } else if(List.class.equals(c)){



            } else {


                // Invoke the getter method on the Institution1 object.
                objField = new PropertyDescriptor(field.getName(),
                        source.getClass()).getReadMethod().invoke(source);

                // Invoke the setter method on the Institution2 object.
                new PropertyDescriptor(field.getName(), target.getClass())
                        .getWriteMethod().invoke(target, objField);
            }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IntrospectionException e) {
                e.printStackTrace();
            }
        }
    }

    public static <T> void map(MultivaluedMap<String, String> source, T target){
        Class<?> objClass = target.getClass();
        for (Field field : objClass.getDeclaredFields()) {

            Class c = field.getType();
            Object objField = null;
            try {

                objField = source.getFirst(field.getName());

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

    /*public static <S, T> Collection<T> mapCollection(final Collection<S> sourceCollection, Collection<T> targetCollection, final MapContext mapContext) {
        targetCollection = targetCollection != null ? targetCollection : new LinkedList<T>();

        if (sourceCollection.isEmpty()) {
            targetCollection.clear();
            return targetCollection;
        }

        for (S newItem : sourceCollection) {
            targetCollection.add(newItem);
        }

        return targetCollection;
    }*/

    public static void main(String[] args){
        MessageList ml = new MessageList();
        Message m = new Message();
        ml.getMessage().add(m);

        WSMessageList wml = new WSMessageList();


        Museum mu = new Museum();
        mu.setName("name");
        mu.setDesc("desc");

        WSMuseum wmu = new WSMuseum();
        map(mu, wmu);
    }
}
