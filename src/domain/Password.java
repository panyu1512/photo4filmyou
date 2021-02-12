package domain;

import auth.EncoderInterface;

import java.io.Serializable;

public class Password implements Serializable {
    
	private static final long serialVersionUID = -6784133890430129078L;
	
	private String value;

    public Password(String hash){
        this.value = hash;
    }

    public Password(String value, EncoderInterface encoder){
        this.value = encoder.encode(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString()
    {
        return this.value;
    }

}
