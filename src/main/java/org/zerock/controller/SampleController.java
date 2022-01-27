package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zerock.domain.SampleDTO;

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
}
