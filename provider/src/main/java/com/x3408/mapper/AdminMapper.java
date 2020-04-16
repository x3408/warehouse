package com.x3408.mapper;

import com.x3408.entity.Admin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper {

    @Select("select * from admin where id=#{id}")
    Admin getAdminById(Integer id);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into admin(name,username,password,age,email,level,status)" +
            " values(#{name},#{username},#{password},#{age},#{email},#{level},#{status})")
    int insertAdmin(Admin admin);

    @Select("select count(*) from admin")
    Integer getAdminCount();
}
