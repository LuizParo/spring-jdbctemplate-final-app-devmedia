package br.com.devmedia.appfinal.web.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.devmedia.appfinal.entity.Cargo;
import br.com.devmedia.appfinal.entity.Departamento;
import br.com.devmedia.appfinal.service.CargoService;
import br.com.devmedia.appfinal.service.DepartamentoService;
import br.com.devmedia.appfinal.web.editor.DepartamentoEditorSupport;

@Controller
@RequestMapping(value = "cargo")
public class CargoController implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private CargoService cargoService;
    
    @Autowired
    private DepartamentoService departamentoService;
    
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Departamento.class, new DepartamentoEditorSupport(this.departamentoService));
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView form(@ModelAttribute("cargo") Cargo cargo, ModelMap model) {
        model.addAttribute("cargos", this.cargoService.findAll());
        model.addAttribute("departamentos", this.departamentoService.findAll());
        return new ModelAndView("formCargo", model);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String save(@ModelAttribute("cargo") Cargo cargo) {
        this.cargoService.saveOrUpdate(cargo);
        return "redirect:/cargo";
    }
    
    @RequestMapping(value = "/update/{id}")
    public ModelAndView preUpdate(@PathVariable("id") Integer id) {
        ModelAndView view = new ModelAndView("formCargo");
        view.addObject("cargo", this.cargoService.findById(id));
        view.addObject("cargos", this.cargoService.findAll());
        view.addObject("departamentos", this.departamentoService.findAll());
        
        return view;
    }
    
    @RequestMapping(value = "/remove/{id}")
    public String remove(@PathVariable("id") Integer id) {
        this.cargoService.remove(id);
        return "redirect:/cargo";
    }
}