package jpa;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public  abstract  class CatalogElement {

    @Column(name="name", columnDefinition="text")
    protected String name;

    CatalogElement() {

    }

    CatalogElement(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
