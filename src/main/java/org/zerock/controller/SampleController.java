package org.zerock.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
	
	// http://localhost:8080/sample/ex01?name=Peter&age=39
 
	@GetMapping("/ex01")
    public String ex01(SampleDTO dto) {
        System.out.print(dto);
		//log.info(" "+dto);
        return "ex01";
    }
	
	// http://localhost:8080/sample/ex02?name=AAA&age=10 
	// @RequestParam으로 객체를 형성하지 않고, 개별적으로 값 전달 가능
	
	@GetMapping("/ex02")
    public String ex02(@RequestParam("name") String name,@RequestParam("age") int age) {
        System.out.println("name: "+ name);
        System.out.println("age: "+ age);
 
        return "ex02";
    }
	
	// http://localhost:8080/sample/ex02List?ids=111&ids=222&ids=333
	// 리스트도 동일하게 전달 가능 
	
	@GetMapping("/ex02List")
    public String ex02List(@RequestParam("ids") ArrayList<String> ids) {
        System.out.println("ids: "+ ids);
    
        return "ex02List";
    }
	
	// http://localhost:8080/sample/ex02List?ids=111&ids=222&ids=333
	// 배열도 동일하게 전달 가능 
	
	@GetMapping("/ex02Array")    
	public String ex02Array(@RequestParam("ids") String[]ids) {
		System.out.println("array ids: "+ Arrays.toString(ids));
		
        return "ex02Array";    
    }
	
	// http://localhost:8080/sample/ex02Baen?list%5B0%5D.name=aaa&list%5B1%5D.name=bbb&list%5B2%5D.name=ccc
	// 객체 리스트도 전송 가능.
	// Tomcat이[]를허용하지 않음. [ 은 %5B, ] 은 %5D
	
	@GetMapping("/ex02Baen")
    public String ex02Baen(SampleDTOList list) {
        System.out.println("list dtos: "+ list);
        
        return "ex02Baen";    
    }
	
	// 파라미터의 수집을 다른 용어로는 'binding(바인딩)'이 라고 함
	// 변환이 가능한 데이터는 자동으로 변환되지만 경우에 따라서는 파라미터를 변환해서 처리해야 하는 경우도 존재함
	// 예를 들어, 화면에서 '2018-01-01'과 같이 문자열로 전달된 데이터를 java.util.Date타입으로 변환하는 작업이 그러함
	// 스프링 Controller에서는 파라미터를 바인딩할 때 자동으로 호출되는 @InitBinder를 이용해서 이러한 변환을 처리할 수 있음
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-mm-dd");
        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
    } 
 
	// http://localhost:8080/sample/ex03?title=test&dueDate=2018-01-01
	
    @GetMapping("/ex03")
    public String ex03(TodoDTO todo) {
        System.out.println("todo: "+todo);
        
        return "ex03";
    }
    
    // http://localhost:8080/sample/ex04?name=aaa&age=11&page=9
    // @ModelAttribute를 이용해, 모델에 포함 않된, 추가 데이터를 전달 가능  
    
    @GetMapping("/ex04")
    public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
        System.out.println("dto: "+dto);
        System.out.println("page: "+page);
        
        return "/sample/ex04";
    }


	
}
