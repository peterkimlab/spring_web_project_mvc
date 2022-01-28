package org.zerock.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;

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

	
}
