package hu.imsi.mir.utils;

public interface BeanId<T extends Enum<T>> {

    String getBeanId();

    default <B> B get() {
        return BeanUtils.getBean(this);
    }
}
