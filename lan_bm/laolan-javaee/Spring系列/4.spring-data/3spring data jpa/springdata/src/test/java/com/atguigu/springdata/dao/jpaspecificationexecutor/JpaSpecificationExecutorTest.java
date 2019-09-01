package com.atguigu.springdata.dao.jpaspecificationexecutor;

import com.atguigu.springdata.pojo.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class JpaSpecificationExecutorTest {

	private ApplicationContext ctx = null;
	private PersonJpaSpecificationExecutorRepsotory pjseRepsotory = null;

	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		pjseRepsotory = ctx.getBean(PersonJpaSpecificationExecutorRepsotory.class);
	}

	//条件查询分页
	/**
	 * 调用 JpaSpecificationExecutor 的 Page<T> findAll(Specification<T> spec, Pageable pageable);
	 * Specification: 封装了jpa Criteria查询的查询条件
	 * Pageable: 封装请求分页信息 pageNo, pageSize, Sort
	 */
	@Test
	public void testJpaSpecificationExecutor(){
		int pageNo = 3 - 1;
		int pageSize = 5;
		PageRequest pageable = new PageRequest(pageNo, pageSize);

		//通常使用 Specification 匿名内部类
		Specification<Person> specification = new Specification<Person>() {
			/**
			 * @param *root: 查询的实体类
			 * @param query: 可以得到Root 对象,
			 *             1.告知 JPA Criteria 查询哪个实体类
			 *             2.添加查询条件
			 *    		   3.结合EntityManager得到最终查询的 TypedQuery 对象.
			 * @param *cb: CriteriaBuilder对象，
			 * 				1.用于创建Criteria相关对象工厂
			 * 				2.从中获取	predicate 对象
			 * @return *Predicate类型:  代表一个查询条件.
			 */
			@Override
			public Predicate toPredicate(Root<Person> root,
										 CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path path = root.get("id");
				Predicate predicate = cb.gt(path, 5);
				return predicate;
			}
		};

		Page<Person> page = pjseRepsotory.findAll(specification, pageable);

		System.out.println("总记录数: " + page.getTotalElements());
		System.out.println("当前页: " + (page.getNumber() + 1));
		System.out.println("总页数: " + page.getTotalPages());
		System.out.println("当前页面的 List: " + page.getContent());
		System.out.println("当前页面记录数: " + page.getNumberOfElements());
	}
}
