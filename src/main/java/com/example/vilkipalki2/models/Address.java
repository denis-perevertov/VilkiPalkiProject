package com.example.vilkipalki2.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Embeddable
public class Address implements Serializable {

    @NotBlank
    private String street, house;

    private String apartment, entrance, code, floor;

    private String comment;

    public Address(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        List<String> values = new ArrayList<>();
        Field[] fields = this.getClass().getDeclaredFields();
        for(Field field : fields) {
            try {
                if(field.get(this) != null) values.add(field.getName() + ": " + field.get(this).toString());
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return String.join(",\n", values);
    }
}
