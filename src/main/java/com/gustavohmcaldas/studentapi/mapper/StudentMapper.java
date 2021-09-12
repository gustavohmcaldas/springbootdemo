package com.gustavohmcaldas.studentapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.gustavohmcaldas.studentapi.domain.Student;
import com.gustavohmcaldas.studentapi.requests.StudentPostRequestBody;
import com.gustavohmcaldas.studentapi.requests.StudentPutRequestBody;

@Mapper(componentModel = "spring")
public abstract class StudentMapper {
	public static final StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);
	
	public abstract Student toStudent(StudentPostRequestBody studentPostRequestBody);
	
	public abstract Student toStudent(StudentPutRequestBody studentPutRequestBody);
}
