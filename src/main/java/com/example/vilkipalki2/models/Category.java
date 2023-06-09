package com.example.vilkipalki2.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@Table(name="categories")
public class Category {

    @SequenceGenerator(name="id_generator", initialValue = 0, allocationSize = 1)
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    private long id;

    private String name;

    private String iconFileName;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.EAGER, mappedBy="category")
    @JsonManagedReference(value = "category_reference")
    private List<MenuItem> itemList;

    @PreRemove
    public void clearItemList() {this.itemList.clear();}

}
