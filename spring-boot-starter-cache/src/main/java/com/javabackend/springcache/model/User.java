package com.javabackend.springcache.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;

    @Override
    public boolean equals(Object o){
    if(this==o) return  true;
    if (!(o instanceof User)) return false;
    User user= (User) o;
    return getId()==user.getId();
    }

    @Override
    public  int hashCode(){
        return 31;
    }



}
