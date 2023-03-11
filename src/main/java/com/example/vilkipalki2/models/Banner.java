package com.example.vilkipalki2.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="banners")
public class Banner {

    @Id @GeneratedValue
    private long id;

    private String fileName;

}
