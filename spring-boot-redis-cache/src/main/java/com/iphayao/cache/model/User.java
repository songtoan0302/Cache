package com.iphayao.cache.model;

import lombok.Data;
import lombok.ToString;


import javax.persistence.*;

import java.io.Serializable;
@Data
@ToString
@Entity
public class User implements Serializable {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
        private Long id;
    @Column(name = "firstName")
        private String firstName;
    @Column(name = "lastName")
        private String lastName;


}
