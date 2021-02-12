package domain;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NIF implements Serializable {
	private static final long serialVersionUID = 1L;

    private String value;

    public NIF(String value){
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
        Pattern pattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");
        Matcher matcher = pattern.matcher(value);

        if (!matcher.matches()) {
            return false;
        }

        String letter = matcher.group(2);
        String letterGroup = "TRWAGMYFPDXBNJZSQVHLCKE";
        int index = Integer.parseInt(matcher.group(1));
        index = index % 23;
        String reference = letterGroup.substring(index, index + 1);
        return reference.equalsIgnoreCase(letter);

    }

    @Override
    public String toString()
    {
        return this.value;
    }
}
