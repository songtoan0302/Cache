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
    @Column(name = "id")
        private Long id;
    @Column(name = "firstname")
        private String firstName;
    @Column(name = "lastname")
        private String lastName;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId();
    }

    @Override
    public int hashCode() {
        return 31;
    }


}
