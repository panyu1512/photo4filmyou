package domain;

import java.io.Serializable;

public class Email implements Serializable {
  
	private static final long serialVersionUID = -709852238165275685L;
	private String value;

    public Email(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return this.value;
    }
}
