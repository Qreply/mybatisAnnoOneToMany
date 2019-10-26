package com.wd.mapper;

import com.wd.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;


/**
 * 用户的持久层接口
 * 在mybatis中针对,CRUD一共有四个注解
 *  @Select @Insert @Update @Delete
 */
@CacheNamespace(blocking = true)
public interface UserMapper {

    /**
     * 查询所有操作
     * @return
     */
    @Select("select * from user")
    @Results(id = "userMap", value = {
            @Result(id=true,column = "id",property = "userId"),
            @Result(column = "username",property = "userName"),
            @Result(column = "address",property = "userAddress"),
            @Result(column = "sex",property = "userSex"),
            @Result(column = "birthday",property = "userBirthday"),
            @Result(property = "accounts",column = "id",
                    many = @Many(select = "com.wd.mapper.AccountMapper.findAccountByUid",
                            fetchType = FetchType.LAZY))
    })
    List<User> findAll();

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Select("select * from user where id = #{id}")
    @ResultMap(value = {"userMap"})
    User findById(Integer id);

    /**
     * 根据用户名称模糊查询
     * @param name
     * @return
     */
    @Select("select * from user where username like #{name}")
    @ResultMap(value = {"userMap"} )
    List<User> findUserByName(String name);

}
