package br.com.devmedia.appfinal.web.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.devmedia.appfinal.entity.Departamento;
import br.com.devmedia.appfinal.service.DepartamentoService;

@Controller
@RequestMapping("departamento")
public class DepartamentoController implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private DepartamentoService service;
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView findAll(@ModelAttribute("departamento") Departamento departamento, ModelMap model) {
        model.addAttribute("departamentos", this.service.findAll());
        return new ModelAndView("formDepartamento", model);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String save(@ModelAttribute("departamento") Departamento departamento) {
        this.service.saveOrUpdate(departamento);
        return "redirect:/departamento";
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView preUpdate(@PathVariable("id") Integer id, ModelMap model) {
        Departamento departamento = this.service.findById(id);
        model.addAttribute("id", id);
        model.addAttribute("departamento", departamento.getDepartamento());
        
        return new ModelAndView("redirect:/departamento", model);
    }
    
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") Integer id) {
        this.service.remove(id);
        return "redirect:/departamento";
    }
}