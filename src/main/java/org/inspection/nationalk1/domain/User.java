package org.inspection.nationalk1.domain;

import javax.persistence.*;

/**
 * Created by Park Kwang Yong(pky1030@gmail.com) on 16. 3. 19..
 */
@Entity
@Table(name = "user")
public class User {
    @Id @GeneratedValue
    private Long id;
    @Column(name = "name" ,nullable = false, length =  30, unique = true)
    private String name;

    public User(){ }

    public User(String name){
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
