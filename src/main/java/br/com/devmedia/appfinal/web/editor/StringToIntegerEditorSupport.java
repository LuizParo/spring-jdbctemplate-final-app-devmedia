package br.com.devmedia.appfinal.web.editor;

import java.beans.PropertyEditorSupport;
import java.io.Serializable;

import org.apache.log4j.Logger;

public class StringToIntegerEditorSupport extends PropertyEditorSupport implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(StringToIntegerEditorSupport.class);

    @Override
    public void setAsText(String text) {
        try {
            super.setValue(Integer.valueOf(text));
        } catch (NumberFormatException e) {
            LOGGER.fatal(text + " não é um Integer válido!", e);
        }
    }
}