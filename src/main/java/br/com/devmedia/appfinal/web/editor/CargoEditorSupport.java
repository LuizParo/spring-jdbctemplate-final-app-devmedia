package br.com.devmedia.appfinal.web.editor;

import java.beans.PropertyEditorSupport;
import java.io.Serializable;

import br.com.devmedia.appfinal.entity.Cargo;
import br.com.devmedia.appfinal.service.CargoService;

public class CargoEditorSupport extends PropertyEditorSupport implements Serializable {
    private static final long serialVersionUID = 1L;
    private CargoService cargoService;

    public CargoEditorSupport(CargoService cargoService) {
        this.cargoService = cargoService;
    }
    
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if(text.isEmpty()) {
            int id = Integer.parseInt(text);
            Cargo cargo = this.cargoService.findById(id);
            super.setValue(cargo);
        }
    }
}