package com.example.demo.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	@Autowired
	JdbcTemplate jt;
	
	@Autowired
	NamedParameterJdbcTemplate nt;
	
public int dataInsert(Student s) {
	int id=s.getId();
	String name=s.getName();
	String branch=s.getBranch();
	String sql="insert into Student values(?,?,?)";
	return jt.update(sql,id,name,branch);	
	}

public int datainsert(Student s) {
	MapSqlParameterSource sp=new MapSqlParameterSource().addValue("id", s.getId()).addValue("name", s.getName())
			.addValue("branch", s.getBranch() );
	String sq="insert into Student values(:id,:name,:branch)";
	return nt.update(sq,sp);
	}

public int dataUpdate(Student s) {
	MapSqlParameterSource sp=new MapSqlParameterSource().addValue("id", s.getId()).addValue("name", s.getName());
	String sq="update Student set name=(:name) where id=(:id)";
	return nt.update(sq,sp);
	}
public int dataDelete(Student s) {
	MapSqlParameterSource sp=new MapSqlParameterSource().addValue("id", s.getId());
	String sq="delete from Student where id=(:id)";
	return nt.update(sq,sp);
	}
}


