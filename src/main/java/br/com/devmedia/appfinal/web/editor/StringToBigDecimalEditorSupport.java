package br.com.devmedia.appfinal.web.editor;

import java.beans.PropertyEditorSupport;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.apache.log4j.Logger;

public class StringToBigDecimalEditorSupport extends PropertyEditorSupport implements Serializable {
    private static final long serialVersionUID = 1;
    private static final Logger LOGGER = Logger.getLogger(StringToBigDecimalEditorSupport.class);
    
    @Override
    public String getAsText() {
        BigDecimal value = (BigDecimal) this.getValue();
        DecimalFormat format = new DecimalFormat("#,##0.00");
        
        if(value == null) {
            value = BigDecimal.ZERO;
        }
        
        return format.format(value);
    }
    
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            text = text.replace(".", "").replace(",", ".");
            super.setValue(new BigDecimal(text));
        } catch (NumberFormatException e) {
            LOGGER.fatal(text + " não é um BigDecimal válido!", e);
        }
    }
}