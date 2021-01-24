package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;

@Repository
public class PhoneDao {
	
	@Autowired
	private  SqlSession sqlSession;
	
	
	//전체리스트 가져오기
	public List<PersonVo> getPersonList(){
		System.out.println("dao:PersonList()");
		List<PersonVo> personList = sqlSession.selectList("phonebook.selectList2");
		
		System.out.println(personList.toString());
		return personList;
		
	}
	
	//전화번호 저장
	
	public void personInsert(PersonVo personVo) {
		System.out.println(personVo.toString());
		int count = sqlSession.insert("phonebook.insert",personVo);
	}
	
	
	//전화번호 삭제
	
	public void personDelete(int personId) {
		System.out.println("DAO: personDelete()  :"+personId);
		int count = sqlSession.delete("phonebook.delete",personId);
		System.out.println(count);
	}
	

	//1명 데이터 가져오기
	public PersonVo getPerson(int personId) {
		System.out.println("dao: getPerson () : " + personId);
		
		PersonVo personVo = sqlSession.selectOne("phonebook.selectone",personId);
		//select 자체가 이미 vo 값을 리턴해준다 (따로지정안함)
		System.out.println(personVo.toString());
		
		return personVo;
	}
	
	//전화번호 수정 
	public void personUpdate(PersonVo personVo) {
		System.out.println("Dao: personUpdate() ");
		
		int count = sqlSession.update("phonebook.update",personVo);
		
		
	}
}
