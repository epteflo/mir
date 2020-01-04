package hu.imsi.mir.utils;

import hu.imsi.mir.service.ServiceRegistry;

public class BeanHelper {

    public static ServiceRegistry getServiceRegistry() {
        return MirBeanId.SERVICE_REGISTRY.get();
    }
    public static DBHelper getDBHelper() { return MirBeanId.DB_HELPER.get(); }
}
