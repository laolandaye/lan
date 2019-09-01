/**
 * 
 */
package mybatis.dao;

import mybatis.po.User;

/**
 * 用户dao
 *
 */
public interface UserDao {

	//根据ID查询用户信息
	public User findUserById(int id) throws Exception;
	
}