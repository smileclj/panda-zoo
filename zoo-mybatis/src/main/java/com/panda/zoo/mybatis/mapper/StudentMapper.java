package com.panda.zoo.mybatis.mapper;

import com.panda.zoo.mybatis.model.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMapper {
    int deleteByPrimaryKey(String id);

    int insertSelective(Student record);

    Student selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(@Param("student") Student student, @Param("valid") boolean valid);
}