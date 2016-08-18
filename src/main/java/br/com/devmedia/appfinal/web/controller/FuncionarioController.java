package br.com.devmedia.appfinal.web.controller;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.devmedia.appfinal.entity.Cargo;
import br.com.devmedia.appfinal.entity.Funcionario;
import br.com.devmedia.appfinal.service.CargoService;
import br.com.devmedia.appfinal.service.EnderecoService;
import br.com.devmedia.appfinal.service.FuncionarioService;
import br.com.devmedia.appfinal.validator.EnderecoValidator;
import br.com.devmedia.appfinal.validator.FuncionarioValidator;
import br.com.devmedia.appfinal.web.editor.CargoEditorSupport;
import br.com.devmedia.appfinal.web.editor.StringToBigDecimalEditorSupport;
import br.com.devmedia.appfinal.web.editor.StringToIntegerEditorSupport;

@Controller
@RequestMapping(value = "funcionario")
public class FuncionarioController implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(FuncionarioController.class);

    @Autowired
    private FuncionarioService funcionarioService;
    
    @Autowired
    private CargoService cargoService;

    @Autowired
    private EnderecoService enderecoService;
    
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Cargo.class, new CargoEditorSupport(this.cargoService));
        binder.registerCustomEditor(Integer.class, "endereco.numero", new StringToIntegerEditorSupport());
        binder.registerCustomEditor(BigDecimal.class, "salario", new StringToBigDecimalEditorSupport());
        binder.addValidators(new FuncionarioValidator(new EnderecoValidator()));
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView form(@ModelAttribute("funcionario") Funcionario funcionario) {
        ModelAndView view = new ModelAndView("formFuncionario");
        view.addObject("funcionarios", this.funcionarioService.findAll());
        view.addObject("cargos", this.cargoService.findAll());
        
        return view;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("funcionario") @Validated Funcionario funcionario, BindingResult result) {
        ModelAndView view = new ModelAndView("redirect:/funcionario");
        
        if(result.hasErrors()) {
            LOGGER.warn("Foram encontrados campos inválidos!");
            view.addObject("funcionarios", this.funcionarioService.findAll());
            view.addObject("cargos", this.cargoService.findAll());
            view.setViewName("formFuncionario");
            return view;
        }
        
        try {
            LOGGER.info("Executando saveOrUpdate para funcionário!");
            this.funcionarioService.saveOrUpdate(funcionario);
            LOGGER.info("Funcionário " + funcionario.getId() + " persistido com sucesso!");
        } catch (Exception e) {
            LOGGER.error("Um erro ao inserir/alterar um funcionário!", e);
        }
        return view;
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView preUpdate(@PathVariable("id") Integer id, ModelMap model) {
        model.addAttribute("funcionario", this.funcionarioService.findById(id));
        model.addAttribute("funcionarios", this.funcionarioService.findAll());
        model.addAttribute("cargos", this.cargoService.findAll());
        return new ModelAndView("formFuncionario");
    }
    
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") Integer id) {
        Funcionario funcionario = this.funcionarioService.findById(id);
        this.enderecoService.remove(funcionario.getEndereco().getId());
        
        this.funcionarioService.remove(id);
        return "redirect:/funcionario";
    }
    
    @RequestMapping(value = "/find/cargo/{idCargo}", method = RequestMethod.GET)
    public ModelAndView findByCargo(@PathVariable("idCargo") Integer idCargo, @ModelAttribute("funcionario") Funcionario funcionario) {
        ModelAndView view = new ModelAndView("formFuncionario");
        view.addObject("funcionarios", this.funcionarioService.findByCargo(idCargo));
        view.addObject("cargos", this.cargoService.findAll());
        
        return view;
    }
    
    @RequestMapping(value = "/find/nome/{nome}", method = RequestMethod.GET)
    public ModelAndView findByNome(@PathVariable("nome") String nome, @ModelAttribute("funcionario") Funcionario funcionario) {
        ModelAndView view = new ModelAndView("formFuncionario");
        view.addObject("funcionarios", this.funcionarioService.findByNome(nome));
        view.addObject("cargos", this.cargoService.findAll());
        
        return view;
    }
}