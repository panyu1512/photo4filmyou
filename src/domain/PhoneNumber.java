package domain;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumber implements Serializable {
	private static final long serialVersionUID = 1L;

    private String value;

    public PhoneNumber(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }

    public boolean isValid()
    {
    	Pattern pattern = Pattern.compile("^(\\d{3}[- .]?){2}\\d{4}$");
        Matcher matcher = pattern.matcher(value);

        if (!matcher.matches()) {
            return false;
        }
        return true;
    }


}
