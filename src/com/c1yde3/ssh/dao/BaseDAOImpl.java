package com.c1yde3.ssh.dao;

import org.hibernate.FlushMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by c1yde3 on 2017/12/12.
 */

@Repository
@Transactional
@Scope("prototype")
public class BaseDAOImpl implements BaseDAO {

    private HibernateTemplate ht;

    @Autowired
    public void setHt(HibernateTemplate ht) {
        this.ht = ht;
    }

    private HibernateTemplate getHt() {
        ht.setCacheQueries(true);
        ht.getSessionFactory().getCurrentSession().setHibernateFlushMode(FlushMode.AUTO);
        return ht;
    }

    @Override
    public boolean add(Object o) {
        try {
            this.getHt().save(o);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Object o) {
        try {
            this.getHt().delete(o);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Object o) {
        try {
            this.getHt().update(o);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List find(Object o) {
        return this.getHt().findByExample(o);
    }


    /**
     * 根据两个站点查找符合的车次
     * @param station1 站点名1
     * @param station2 站点名2
     * @return 返回包含当前两站名的车此信息
     */
    @Override
    public List findByTwoStation(String station1,String station2){
        return this.getHt().find(" from com.c1yde3.ssh.model.Trans trians where trians.passby like '%"+station1+"%' and  trians.passby like '%"+station2+"%'");

    }

    @Override
    public List getAllTrips() {
        return this.getHt().find("from com.c1yde3.ssh.model.Trans");
    }

    @Override
    public List getTripByOneStation(String station) {
        return this.getHt().find(" from com.c1yde3.ssh.model.Trans trian where trian.passby like '%"+station+"%'");
    }
}