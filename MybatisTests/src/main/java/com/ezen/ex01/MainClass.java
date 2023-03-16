package com.ezen.ex01;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MainClass {

	public static void main(String[] args) {
		
		String resource = "com/ezen/ex01/mybatis-config.xml";
		InputStream inputStream = null; //예외처리를 위해서 null값을 넣어줌
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			//System.out.println(sqlSessionFactory);
			SqlSession session = sqlSessionFactory.openSession();
			//System.out.println(session);
			//BlogMapper은 인터페이스로 인터페이스 파일을 추가해줌
			BlogMapper mapper = session.getMapper(BlogMapper.class);
			//System.out.println(mapper);
			
			BoardVO vo = mapper.selectOne(2222);
			
			System.out.println(vo.getName());
			
			vo.setId(4444);
			vo.setName("이예찬");
			vo.setPhone("010-0000-0000");
			vo.setAddress("거주불명");
			
			int result = mapper.insert(vo);
			
			System.out.println("result : " + result);
			
			System.out.println(vo.getName());
			
		} catch (IOException e) {
		
			e.printStackTrace();
		}

	}

}
