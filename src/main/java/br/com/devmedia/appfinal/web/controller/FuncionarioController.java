package br.com.devmedia.appfinal.web.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
import br.com.devmedia.appfinal.service.FuncionarioService;
import br.com.devmedia.appfinal.web.editor.CargoEditorSupport;

@Controller
@RequestMapping(value = "funcionario")
public class FuncionarioController implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private FuncionarioService funcionarioService;
    
    @Autowired
    private CargoService cargoService;
    
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Cargo.class, new CargoEditorSupport(this.cargoService));
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView form(@ModelAttribute("funcionario") Funcionario funcionario) {
        ModelAndView view = new ModelAndView("formFuncionario");
        view.addObject("funcionarios", this.funcionarioService.findAll());
        view.addObject("cargos", this.cargoService.findAll());
        
        return view;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String save(@ModelAttribute("funcionario") Funcionario funcionario, BindingResult result) {
        this.funcionarioService.saveOrUpdate(funcionario);
        
        return "redirect:/funcionario";
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView preUpdate(@PathVariable("id") Integer id, ModelMap model) {
        return new ModelAndView();
    }
    
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") Integer id) {
        this.funcionarioService.remove(id);
        return "redirect:/funcionario";
    }
}