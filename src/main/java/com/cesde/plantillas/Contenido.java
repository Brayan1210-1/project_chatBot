package com.cesde.plantillas;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@MappedSuperclass
public class Contenido {

 @Id
 private Long id;
 
 @Column (name = "enunciado", nullable = false, length = 400 )
 private String enunciado;
 
 @Column (name = "createdAt", nullable = false, updatable = false)
 private LocalDateTime createdAt;
 
 @Column (name = "createdAt", nullable = false )
 private LocalDateTime updatedAt;
 


 public Contenido() {}

 public Contenido(Long id, String enunciado ) {
	 this.id = id;
	 this.enunciado = enunciado;
	 this.createdAt = LocalDateTime.now();
	 this.updatedAt = LocalDateTime.now();
 }
 
 @PrePersist
 protected void onCreate() {
   this.createdAt = LocalDateTime.now();
   this.updatedAt = LocalDateTime.now();
 }
 
 @PreUpdate
 protected void onUpdate() {
   this.updatedAt = LocalDateTime.now();
 }
 
 public Long getId() {
	return id;
 }

 public void setId(Long id) {
	this.id = id;
 }

 public String getEnunciado() {
	return enunciado;
 }

 public void setEnunciado(String enunciado) {
	this.enunciado = enunciado;
 }

 public LocalDateTime getCreatedAt() {
	return createdAt;
 }

 public void setCreatedAt(LocalDateTime createdAt) {
	this.createdAt = createdAt;
 }

 public LocalDateTime getUpdatedAt() {
	return updatedAt;
 }

 
}
