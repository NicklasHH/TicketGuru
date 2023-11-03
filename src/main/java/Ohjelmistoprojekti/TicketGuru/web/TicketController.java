package Ohjelmistoprojekti.TicketGuru.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TicketController {

	@RequestMapping("/index") // http://localhost:8080/index
	public String showMainPage() {

		return "index.html";
	}
}

//Käyty läpi 01-11-2023
