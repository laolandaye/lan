/**
 * 
 */
package mybatis.mapper;

import mybatis.mapper.UserMapper;
import mybatis.po.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Administrator
 *
 */
public class UserMapperTest {

	private ApplicationContext applicationContext;
	@Before
	public void setUp() throws Exception {
		//创建spring容器	
		applicationContext = new
                ClassPathXmlApplicationContext("spring/applicationContext.xml");
	}
	
	@Test
	public void testFindUserById() throws Exception {
		UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
		
		User user = userMapper.findUserById(1);
		System.out.println(user);
		
	}

}
