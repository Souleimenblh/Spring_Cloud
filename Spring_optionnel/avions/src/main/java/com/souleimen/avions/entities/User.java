package com.souleimen.avions.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
@Id
@GeneratedValue (strategy=GenerationType.IDENTITY) 
private Long user_id;
private String username;
private String password;
private Boolean enabled;
@ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
@JoinTable(name="user_role",joinColumns = @JoinColumn(name="user_id") , 
 inverseJoinColumns = @JoinColumn(name="role_id")) 
private List<Role> roles; 
}