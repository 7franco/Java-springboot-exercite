package com.jfranco.springboot.jpa.springboot_jpa.dto;

public class PersonDto {

    private String name;
    private String lastame;
    
    public PersonDto(String name, String lastame) {
        this.name = name;
        this.lastame = lastame;
    }
    public String getName() {
        return name;
    }
    public String getLastame() {
        return lastame;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setLastame(String lastame) {
        this.lastame = lastame;
    }
    @Override
    public String toString() {
        return "PersonDto [name=" + name + ", lastame=" + lastame + "]";
    }

    
}
