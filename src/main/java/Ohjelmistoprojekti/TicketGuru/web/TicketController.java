package Ohjelmistoprojekti.TicketGuru.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import Ohjelmistoprojekti.TicketGuru.domain.Ticket;
import Ohjelmistoprojekti.TicketGuru.domain.TicketRepository;


@Controller
public class TicketController {
	
	private static final Logger log = LoggerFactory.getLogger(TicketController.class);

	//@Autowired
	//TicketRepository trepository;
	
	@RequestMapping("/index") //http://localhost:8080/index
	public String showMainPage() {
		//log.info("RETURN INDEX.HTML");
		return "index.html";  //index.html
	}
}
