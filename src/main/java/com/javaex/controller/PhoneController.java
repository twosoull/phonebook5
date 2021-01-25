package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
@RequestMapping(value = "/phone")
public class PhoneController {
	// jsp/서블릿떄는 doGet()메소드 1개에 파라미터로 구분
	// 스프링은 메소드 단위 기능을 정의

	// 필드
	// 스프링이 알아서 객체를 만든다.
	@Autowired //자동으로 엮어준다
	private PhoneDao phoneDao ;

	// 생성자

	// 메소드 G/S

	/** 메소드 일반 **/
	// 메소드 일반
	// 메소드 기능 1개씩 -->기능마다 url 부여

	// 리스트
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("list");
		
		List<PersonVo> personList = phoneDao.getPersonList();
		
		model.addAttribute("pList", personList);
		
		
		return "list";
	}
	
	// 등록폼
	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("writeForm");

		return "writeForm";
	}
	
	
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@RequestParam("name") String name, @RequestParam("hp") String hp,
			@RequestParam("company") String company) {
		System.out.println("write");
		System.out.println(name + "," + hp + ", " + company);

		PersonVo personVo = new PersonVo(name, hp, company);
		System.out.println(personVo);

		phoneDao.personInsert(personVo);
		return "redirect:/phone/list";
	}
	/*
	// 딜리트
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam("id") int id) {
		System.out.println("delete");
		System.out.println(id);

		phoneDao.personDelete(id);

		return "redirect:/phone/list";
	}
	*/
	// 딜리트수업
	@RequestMapping(value = "/delete/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@PathVariable("id") int id) {
		System.out.println("delete");
		System.out.println(id);


		phoneDao.personDelete(id);

		return "redirect:/phone/list";
	}
	// 수정폼
	@RequestMapping(value = "/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(@RequestParam("id") int id, Model model) {
		System.out.println("modifyForm");
		System.out.println(id);

		// dao를 통해서 데이터받기
		PersonVo personVo = phoneDao.getPerson(id);

		// 어트리뷰트
		model.addAttribute("personVo", personVo);

		return "modifyForm";
	}
	
	@RequestMapping(value = "/modifyForm2", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm2(@RequestParam("id") int id, Model model) {
		System.out.println("modifyForm2");
		System.out.println(id);
		
		Map<String,Object> personMap = phoneDao.getPerson2(id);
		
		model.addAttribute("personMap",personMap);
		return "modifyForm2";
	}
	
	//수정 --> modify
	@RequestMapping(value="modify", method = {RequestMethod.GET,RequestMethod.POST})
	public String modify(@ModelAttribute PersonVo personVo) {
		
		System.out.println("modify");
		System.out.println(personVo);
		
		int count = phoneDao.personUpdate(personVo);
		
		
		
		return "redirect:/phone/list";
	}

	@RequestMapping(value="modify2", method = {RequestMethod.GET,RequestMethod.POST})
	public String modify2(@RequestParam("personId") int personId,
						  @RequestParam("name") String name,
						  @RequestParam("hp") String hp,
						  @RequestParam("company")String company) {
		int count = phoneDao.personUpdate2(personId, name, hp, company);
		
		return "redirect:/phone/list";
	}
	/*
	// 수정 --> modify -- >@ModelAttribute
	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute PersonVo personVo) {
		System.out.println("modify");
		
		System.out.println(personVo);
		
		phoneDao.personUpdate(personVo);
		
		return "redirect:/phone/list";
	}
	*/
	/*
	// 수정 --> modify2 -- >@requestParam
	@RequestMapping(value = "/modify2", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify2(@RequestParam("name") String name, @RequestParam("hp") String hp,
			@RequestParam("company") String company, @RequestParam("personId") int id) {
		System.out.println("modify");

		phoneDao.personUpdate(new PersonVo(id, name, hp, company));

		return "redirect:/phone/list";
	}
		*/
}
