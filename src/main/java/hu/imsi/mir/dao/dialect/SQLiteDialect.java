package hu.imsi.mir.dao.dialect;

import org.hibernate.MappingException;
import org.hibernate.dialect.identity.IdentityColumnSupport;
import org.hibernate.dialect.identity.IdentityColumnSupportImpl;

public class SQLiteDialect extends org.hibernate.dialect.SQLiteDialect {
    @Override
    public IdentityColumnSupport getIdentityColumnSupport() {
        return new SQLiteIdentityColumnSupport();
    }

    public static class SQLiteIdentityColumnSupport extends IdentityColumnSupportImpl {

        @Override
        public boolean supportsIdentityColumns() {
            return true;
        }

        @Override
        public String getIdentitySelectString(String table, String column, int type)
                throws MappingException {
            return "select last_insert_rowid()";
        }

        @Override
        public String getIdentityColumnString(int type) throws MappingException {
            return "integer";
        }
    }
}
