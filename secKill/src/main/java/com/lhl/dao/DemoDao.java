package com.lhl.dao;


import com.lhl.domain.Demo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


public interface DemoDao {
    @Select("select * from demo where name = #{name}")
    public Demo getByName(@Param("name")String name	);

    @Insert("insert into demo(name, content)values(#{name}, #{content})")
    public int insert(Demo user);
}
