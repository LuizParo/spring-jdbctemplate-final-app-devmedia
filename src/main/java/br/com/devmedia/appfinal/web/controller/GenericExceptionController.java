package br.com.devmedia.appfinal.web.controller;

import java.io.Serializable;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GenericExceptionController implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(GenericExceptionController.class);
    private final static String DEFAULT_PAGE_ERROR = "error";
    
    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public ModelAndView integrityKeyException(HttpServletRequest request, Exception e) {
        LOGGER.error("Request '" + request.getRequestURI() + "' lançou a exceção: " + e.getMessage(), e);
        
        ModelAndView view = new ModelAndView(DEFAULT_PAGE_ERROR);
        view.addObject("mensagem", "Registro já existe no banco de dados!");
        view.addObject("exception", e);
        view.addObject("url", request.getRequestURL() + " (" + request.getMethod() + ")");
        
        return view;
    }
    
    @ExceptionHandler(value = {SQLException.class, DataAccessException.class})
    public ModelAndView integrityException(HttpServletRequest request, Exception e) {
        LOGGER.error("Request '" + request.getRequestURI() + "' lançou a exceção: " + e.getMessage(), e);
        
        ModelAndView view = new ModelAndView(DEFAULT_PAGE_ERROR);
        view.addObject("mensagem", "Ocorreu um problema ao acessar o banco de dados: " + e.getMessage());
        view.addObject("exception", e);
        view.addObject("url", request.getRequestURL() + " (" + request.getMethod() + ")");
        
        return view;
    }
    
    @ExceptionHandler(value = {Exception.class})
    public ModelAndView defaultException(HttpServletRequest request, Exception e) {
        LOGGER.error("Request '" + request.getRequestURI() + "' lançou a exceção: " + e.getMessage(), e);
        
        ModelAndView view = new ModelAndView(DEFAULT_PAGE_ERROR);
        view.addObject("mensagem", "Ocorreu um problema na aplicação: " + e.getMessage());
        view.addObject("exception", e);
        view.addObject("url", request.getRequestURL() + " (" + request.getMethod() + ")");
        
        return view;
    }
    
    @ExceptionHandler(value = {NoHandlerFoundException.class})
    public ModelAndView handlerNotFoundException(HttpServletRequest request, Exception e) {
        LOGGER.error("Request '" + request.getRequestURI() + "' lançou a exceção: " + e.getMessage(), e);
        
        ModelAndView view = new ModelAndView(DEFAULT_PAGE_ERROR);
        view.addObject("mensagem", "URL '" + request.getRequestURL() + "' inválida!");
        view.addObject("exception", e);
        view.addObject("url", request.getRequestURL() + " (" + request.getMethod() + ")");
        
        return view;
    }
}