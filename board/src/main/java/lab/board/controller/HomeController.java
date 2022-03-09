package lab.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home() { 
		System.out.println("home1");
		return "index";
	}
	
	@PostMapping("/")
	public String home2() { 
		System.out.println("home2");
		return "index";
	}
}
