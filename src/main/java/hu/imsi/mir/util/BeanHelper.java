package hu.imsi.mir.util;

import hu.imsi.mir.spring.hibernate.dao.MirDao;
import hu.imsi.mir.spring.hibernate.service.MirService;

public class BeanHelper {

    public static DBHelper getDbHelper() {
        return MirBeanId.DB_HELPER.get();
    }

    public static MirService getMirService() {
        return MirBeanId.MIR_SERVICE.get();
    }

    public static MirDao getMirDao() {
        return MirBeanId.MIR_DAO.get();
    }

    public static DBUtils getDBUtils() { return MirBeanId.DB_UTILS.get(); }
}
