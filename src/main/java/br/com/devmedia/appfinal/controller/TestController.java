package br.com.devmedia.appfinal.controller;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    
    @Autowired
    private JdbcTemplate template;

    @RequestMapping("/hello")
    public String hello() {
        String value = "";
        try {
            DatabaseMetaData data = this.template.getDataSource().getConnection().getMetaData();
            value = "URL: " + data.getURL();
        } catch (SQLException e) {
            value = e.getMessage();
        }
        return value;
    }
}