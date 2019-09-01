/**
 * 
 */
package mybatis.dao;

import mybatis.po.User;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {
	
	@Override
	public User findUserById(int id) throws Exception {
		//创建工厂
		//创建sqlSession
		SqlSession sqlSession = this.getSqlSession();
		
		//根据ID 查询
		User user = sqlSession.selectOne("test.findUserById" , id);

		return user;
	}
}
