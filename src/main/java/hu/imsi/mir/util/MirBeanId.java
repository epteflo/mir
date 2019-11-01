package hu.imsi.mir.util;

public enum MirBeanId implements BeanId<MirBeanId> {
    DB_HELPER("dbHelper"),
    MIR_SERVICE("mirService"),
    MIR_DAO("mirDao"),
    DB_UTILS("DBUtils")
    ;

    private final String beanId;

    MirBeanId(String beanId) { this.beanId = beanId; }

    @Override
    public String getBeanId() { return beanId; }

}
