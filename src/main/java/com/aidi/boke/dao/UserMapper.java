package com.aidi.boke.dao;


import com.aidi.boke.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


//声明是一个Mapper 映射的注解 可以在启动类中加 @MapperScan注解扫描dao包
@Repository
public interface UserMapper {

    User selectUserByName(String name);

    Integer saveUser(User user);

    User loginUser(User user);
}
