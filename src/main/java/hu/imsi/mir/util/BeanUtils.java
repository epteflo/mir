package hu.imsi.mir.util;

import org.springframework.beans.BeansException;

import java.util.Map;

public enum BeanUtils {
    ;

    /**
     * Ellenőrzi, hogy a Spring kontextusban van-e a paraméterként kapott névvel bekonfigurált bean.
     */
    public static boolean containsBean(final BeanId beanId) {
        return ApplicationContextProvider.getApplicationContext().containsBean(beanId.getBeanId());
    }

    /**
     * Ellenőrzi, hogy a Spring kontextusban van-e a paraméterként kapott névvel bekonfigurált bean.
     */
    public static boolean containsBean(final String beanName) {
        return ApplicationContextProvider.getApplicationContext().containsBean(beanName);
    }

    /**
     * Azt a Spring kontextusban bekonfigurált bean-t adja vissza, amely id-je megegyezik a paraméterként kapott bean id-jével.
     */
    public static <T> T getBean(final BeanId beanId) throws BeansException {
        return  (T) ApplicationContextProvider.getApplicationContext().getBean(beanId.getBeanId());
    }

    /**
     * Azt a Spring kontextusban bekonfigurált bean-t adja vissza, amely id-je megegyezik a paraméterként kapott bean id-jével.
     */
    public static <T> T getBean(final String beanName) throws BeansException {
        return  (T) ApplicationContextProvider.getApplicationContext().getBean(beanName);
    }

    /**
     * Azt a Spring kontextusban bekonfigurált bean-t adja vissza, amely osztálya/interfésze megegyezik a paraméterként kapott osztállyal.
     * Ha több ilyen is van, akkor BeansException dobódik.
     *
     */
    public static <T> T getBean(final Class<T> beanClass) throws BeansException {
        return ApplicationContextProvider.getApplicationContext().getBean(beanClass);
    }

    /**
     * Azokat a Spring kontextusban bekonfigurált bean-eket adja vissza, amelyek osztálya/interfésze megegyezik a paraméterként kapott osztállyal.
     */
    public static  <T> Map<String, T> getBeansOfType(final Class<T> requiredClass) throws BeansException {
        return ApplicationContextProvider.getApplicationContext().getBeansOfType(requiredClass);
    }

    /**
     * Azokat a Spring kontextusban bekonfigurált bean-eket adja vissza, amelyek osztálya/interfésze megegyezik a paraméterként kapott osztállyal, és nem lazy inicializáltak
     */
    public static  <T> Map<String, T> getEagerBeansOfType(final Class<T> requiredClass) throws BeansException {
        return ApplicationContextProvider.getApplicationContext().getBeansOfType(requiredClass, true, false);
    }
}
