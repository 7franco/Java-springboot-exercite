package com.jfranco.springboot.jpa.springboot_jpa.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Embeddable
public class Audit {

    @Column(name="create_at")
    private LocalDateTime creatAt;

    @Column(name="updated_at")
    private LocalDateTime updateAt;

     @PrePersist
    public void PrePersist(){
        System.out.println("Evento del ciclo de vieda de Entity pre-presist");
        this.creatAt = LocalDateTime.now();
    }

    @PreUpdate
    public void PreUpdate(){
        System.out.println("Evento del ciclo de vieda de Entity pre-update");
        this.updateAt= LocalDateTime.now();
    }

    public LocalDateTime getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(LocalDateTime creatAt) {
        this.creatAt = creatAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

     
}
