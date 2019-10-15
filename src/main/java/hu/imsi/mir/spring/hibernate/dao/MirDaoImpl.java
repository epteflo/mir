package hu.imsi.mir.spring.hibernate.dao;

import hu.imsi.mir.spring.hibernate.model.HMuseum;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.sql.SQLException;
import java.util.List;

public class MirDaoImpl extends HibernateDaoSupport implements MirDao{

    public Integer saveMuseum(final HMuseum hMuseum) {
        getHibernateTemplate().execute(new HibernateCallback(){
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                session.saveOrUpdate(hMuseum);

                return null;
            }
        });
        return hMuseum.getId();
    }

    public HMuseum getMuseum(final int id){
        return (HMuseum) getHibernateTemplate().execute(new HibernateCallback(){
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                return session.get(HMuseum.class, id);
            }
        });
    }


}
