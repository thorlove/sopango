package com.sopango;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import com.sopango.dao.IAvaiukDao;
import com.sopango.dao.IShareDataDao;
import com.sopango.dao.IUserInfoDao;
import com.sopango.model.UserInfo;



/**
 * Hello world!
 * 
 */
public class App {
    private static Logger log = Logger.getLogger(App.class);
            
    private static SqlSessionFactory sqlSessionFactory;
    static {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            sqlSessionFactory.getConfiguration().addMapper(IUserInfoDao.class);
            sqlSessionFactory.getConfiguration().addMapper(IShareDataDao.class);
            sqlSessionFactory.getConfiguration().addMapper(IAvaiukDao.class);
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            IUserInfoDao userDAO = session.getMapper(IUserInfoDao.class);
            UserInfo user = userDAO.getByPK(1L);
            System.out.println(user.getAvatar_url());
        } finally {
            session.close();
        }
    }
}
