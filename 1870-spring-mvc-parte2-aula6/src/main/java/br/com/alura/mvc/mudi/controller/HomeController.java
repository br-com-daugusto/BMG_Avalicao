package br.com.alura.mvc.mudi.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import br.com.alura.mvc.mudi.model.Segredo;
import br.com.alura.mvc.mudi.model.User;
import br.com.alura.mvc.mudi.repository.DBFileRepository;
import br.com.alura.mvc.mudi.repository.SegredoRepository;
import br.com.alura.mvc.mudi.repository.UserRepository;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private SegredoRepository segredoRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DBFileRepository dBFileRepository;

	@GetMapping
	public String home(Model model, Principal principal) {
			
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(username);
		long segredo = segredoRepository.count(username);
		long file = dBFileRepository.countM(username);
		
						
	
		model.addAttribute("segredo", segredo);
		model.addAttribute("file", file);
		return "/pages/dashboard.html";
	}
	
	@GetMapping("lista")
	public String listaSegredo(Model model, Principal principal) {
			

		return "/pages/table-elements.html";
	}

}
