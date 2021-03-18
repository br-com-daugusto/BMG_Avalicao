package br.com.alura.mvc.mudi.controller;

import java.io.BufferedOutputStream;

import java.io.FileOutputStream;
import java.util.Arrays;

import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.alura.mvc.mudi.dto.RequisicaoNovoSegredo;
import br.com.alura.mvc.mudi.model.DBFile;
import br.com.alura.mvc.mudi.model.Segredo;
import br.com.alura.mvc.mudi.model.UploadFileResponse;
import br.com.alura.mvc.mudi.model.User;
import br.com.alura.mvc.mudi.repository.SegredoRepository;
import br.com.alura.mvc.mudi.repository.UserRepository;
import br.com.alura.mvc.mudi.servico.DBFileStorageService;


@Controller


@RequestMapping("segredo")
public class SegredoController {

	private static final String CADASTRO_VIEW = "/pages/index.html";
	
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@Autowired
	private SegredoRepository segredoRepository;

	@Autowired
	private UserRepository userRepository;
	
    @Autowired
    private DBFileStorageService dbFileStorageService;
    

	@GetMapping("home")
	public String CadastroSegredo(RequisicaoNovoSegredo requisicao) {
		return "/pages/form-elements.html";
		//return "pages/CadastroDivida.html";
	}
	
	@GetMapping("audioret")
	public String retSegredo(RequisicaoNovoSegredo requisicao) {
		return "/pages/index.html";
	}

	@PostMapping("novoSegredoCadastro")
	public ModelAndView novoSegredo(@Valid RequisicaoNovoSegredo requisicao, BindingResult result) {

		if (result.hasErrors()) {

			ModelAndView mv = new ModelAndView("/pages/form-elements.html");
			mv.addObject("mensagemErro", "Erro ao salvar o registro!");
			return mv;
		} else {

			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = userRepository.findByUsername(username);

			Segredo segredo = requisicao.toSegredo();
			segredo.setUser(user);
			segredoRepository.save(segredo);
			ModelAndView mv = new ModelAndView("/pages/audio.html");	
			mv.addObject("mensagem", "Dados registrados com sucesso!");
			return mv;

		}

	}
	
}
