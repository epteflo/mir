package hu.imsi.mir.spring.hibernate.dao;

import hu.imsi.mir.spring.hibernate.model.HBeacon;
import hu.imsi.mir.spring.hibernate.model.HMuseum;
import hu.imsi.mir.spring.hibernate.query.MuseumQueryParams;
import org.hibernate.Criteria;
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

    public List<HMuseum> getAllMuseum() {
        return (List<HMuseum>)this.getHibernateTemplate().execute(new HibernateCallback(){
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                return session.createCriteria(HMuseum.class)
                        .list();
            }
        });
    }

    public List<HMuseum> findMuseums(final MuseumQueryParams museumQueryParams){
        return (List<HMuseum>)this.getHibernateTemplate().execute(new HibernateCallback(){
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Criteria c = session.createCriteria(HMuseum.class);

                if(museumQueryParams.getName()!=null){
                    c.add(Restrictions.like("name", museumQueryParams.getName()).ignoreCase());
                }

                if(museumQueryParams.getAddress()!=null){
                    c.add(Restrictions.like("address", "%" + museumQueryParams.getAddress() + "%").ignoreCase());
                }

                if(museumQueryParams.getName()!=null){
                    c.add(Restrictions.like("desc", "%" + museumQueryParams.getDesc() + "%").ignoreCase());
                }

                if(museumQueryParams.getNumOfRooms()!=null){
                    c.add(Restrictions.eq("numOfRooms", museumQueryParams.getNumOfRooms()));
                }

                if(museumQueryParams.getHistory()!=null){
                    c.add(Restrictions.like("history", "%" + museumQueryParams.getHistory() + "%").ignoreCase());
                }

                if(museumQueryParams.getCuriosity()!=null){
                    c.add(Restrictions.like("curiosity", "%" + museumQueryParams.getCuriosity() + "%").ignoreCase());
                }

                if(museumQueryParams.getOpenHours()!=null){
                    c.add(Restrictions.like("openHours", "%" + museumQueryParams.getOpenHours() + "%").ignoreCase());
                }

                if(museumQueryParams.getOtherServices()!=null){
                    c.add(Restrictions.like("otherSerices", "%" + museumQueryParams.getOtherServices() + "%").ignoreCase());
                }

                if(museumQueryParams.getPrices()!=null){
                    c.add(Restrictions.like("prices", "%" + museumQueryParams.getPrices() + "%").ignoreCase());
                }

                return c.list();

            }
        });
    }


    public Integer saveBeacon(final HBeacon hBeacon) {
        getHibernateTemplate().execute(new HibernateCallback(){
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                session.saveOrUpdate(hBeacon);

                return null;
            }
        });
        return hBeacon.getId();
    }

    public HBeacon getBeacon(final int id){
        return (HBeacon) getHibernateTemplate().execute(new HibernateCallback(){
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                return session.get(HBeacon.class, id);
            }
        });
    }

    public List<HBeacon> getAllBeacon() {
        return (List<HBeacon>)this.getHibernateTemplate().execute(new HibernateCallback(){
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                return session.createCriteria(HBeacon.class)
                        .list();
            }
        });
    }


}
