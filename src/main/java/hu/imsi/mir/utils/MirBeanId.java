package hu.imsi.mir.utils;

public enum MirBeanId implements BeanId<MirBeanId> {
    SERVICE_REGISTRY("serviceRegistry"),
    DB_HELPER("dBHelper")
    ;

    private final String beanId;

    MirBeanId(String beanId) { this.beanId = beanId; }

    @Override
    public String getBeanId() { return beanId; }

}
