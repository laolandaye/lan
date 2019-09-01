/**
 * 
 */
package mybatis.mapper;

import mybatis.po.Orders;
import mybatis.po.User;

import java.util.List;

/**
 * @author Administrator
 *
 */
public interface OrdersMapper {

	//一對一查詢,查询订单关联用户
	public List<Orders> findOrderUserListResultMap() throws Exception;
	//一對多查詢
	public List<Orders> findOrderAndOrderdetail() throws Exception;
	//一對多复杂
	public List<User> findUserOrderDetail() throws Exception;
	//一對多复杂
	public List<User> findUserOrderItems() throws Exception;
	
	//一對多延迟
	public List<Orders> findOrderUserListLazyLoading() throws Exception;
	
	
}
