package cn.itcast.service.mapper;

import cn.itcast.service.pojo.User;
import tk.mybatis.mapper.common.Mapper;

//@Mapper 省略全局配置了 @MapperScan(basePackages = { "cn.itcast.service.mapper" })
//@Repository
public interface UserMapper extends Mapper<User> {
}
