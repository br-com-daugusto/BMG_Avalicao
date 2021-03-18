package br.com.alura.mvc.mudi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import br.com.alura.mvc.mudi.dto.RequsicaoNovoLogin;
import br.com.alura.mvc.mudi.model.User;
import br.com.alura.mvc.mudi.repository.UserRepository;

@Controller
@RequestMapping("pages")
public class RegistroController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("registro")
	public String retornaPaginaRegistro(RequsicaoNovoLogin requisicao) {
		return "pages/register";
	}

	@PostMapping("novo")
	public ModelAndView novo(@Valid RequsicaoNovoLogin requisicao, BindingResult result) {


		String username = requisicao.getUsername();
		User user = userRepository.findByUsername(username);
	
		
		if (result.hasErrors()) {
			ModelAndView mv = new ModelAndView("pages/register");
			return mv;
		} else if (user != null) {
			ModelAndView mv = new ModelAndView("pages/register");
			mv.addObject("mensagemErro", "Usuario ja existe mo fi!");
			return mv;
		} else {
			user = requisicao.toUsuario();
			userRepository.save(user);
			ModelAndView mv = new ModelAndView("pages/register");
			mv.addObject("mensagem", "Registro salvo com sucesso!");
			return mv;
		}

	}
}
