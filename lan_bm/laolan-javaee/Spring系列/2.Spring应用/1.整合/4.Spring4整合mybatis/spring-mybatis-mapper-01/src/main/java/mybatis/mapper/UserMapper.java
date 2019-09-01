/**
 * 
 */
package mybatis.mapper;

import mybatis.po.User;

import java.util.List;

/**
 * 用户dao
 *
 */
public interface UserMapper {

	//根据ID查询用户信息
	public User findUserById(int id) throws Exception;
	
	//根据用户名称模糊查询用户列表
	public List<User> findUserByName(String username) throws Exception;
	
	//插入用户
	public void insertUser(User user) throws Exception ;
	
	//删除用户
	public void deleteUser(int id) throws Exception;
	
	//修改用户
	public void updateUser(User user) throws Exception;
}
