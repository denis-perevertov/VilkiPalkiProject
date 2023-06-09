package com.example.vilkipalki2.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name="ingredients")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Ingredient implements Cloneable {

    @Id
    @SequenceGenerator(name="ingr_generator", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingr_generator")
    private long id;

    @NotBlank
    private String name;

    private String icon;

    @ManyToMany(mappedBy = "ingredients", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<MenuItem> itemList;

    @Override
    public Ingredient clone() {
        try {
            Ingredient clone = (Ingredient) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
