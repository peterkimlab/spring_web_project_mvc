package org.zerock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j;

@ControllerAdvice
@Log4j
public class CommonExceptionAdvice {
	
	// http://localhost:8080/sample/ex04?name=aaa&age=bbb&page=9
	// age에 숫자가 아닌 문자가 들어가면, 예외처리 발생 함 

	@ExceptionHandler(Exception.class)
	public String except(Exception ex, Model model) {

		//log.error("Exception ......." + ex.getMessage());
		System.out.println("Exception ......." + ex.getMessage());
		
		model.addAttribute("exception", ex);
		//log.error(model);
		System.out.println("model : " + model);
		
		
		return "error_page";
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handle404(NoHandlerFoundException ex) {

		return "custom404";
	}

}
