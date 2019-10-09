package com.jdbcmysql.test;

import com.jdbcmysql.dao.UserDao;
import com.jdbcmysql.po.User;
import org.junit.Test;

import java.sql.SQLException;

/**
 * 1.解决java.lang.NoClassDefFoundError: org/hamcrest/SelfDescribing错误
 *   https://blog.csdn.net/castle07/article/details/8553704
 */

public class UserDaoTest {

    /**
     *
     * @throws SQLException
     */
    @Test
    public void findUserTest () throws SQLException {
        User u = new User();
        u.setUsername("111");
        u.setPassword("12345678");
        UserDao userDao = new UserDao();
        User user = userDao.findUser(u);
        System.out.println(user);

    }
}
