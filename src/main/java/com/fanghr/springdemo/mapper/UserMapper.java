package com.fanghr.springdemo.mapper;

import com.fanghr.springdemo.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User : fanghr
 * Date : 2019/7/20
 * Time : 8:42 PM
 */
@Mapper
public interface UserMapper {

    @Select("select * from user where id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "age", property = "age")
    })
    public User getUserById(long id);

    @Select("select * from user where name = #{name}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "age", property = "age")
    })
    public List<User> getUsersByName(String name);


    @Insert("insert into user(name, age) values(#{name}, #{age})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int addUser(User user);
}
