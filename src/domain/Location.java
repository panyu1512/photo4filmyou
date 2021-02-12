package domain;

import java.io.Serializable;

public class Location implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String value;

    public Location(String value){
        this.value = value;
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
