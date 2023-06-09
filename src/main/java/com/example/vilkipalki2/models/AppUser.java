package com.example.vilkipalki2.models;


import com.example.vilkipalki2.dto.UserDTO;
import com.example.vilkipalki2.util.AppUserRole;
import com.example.vilkipalki2.util.Gender;
import com.example.vilkipalki2.util.Language;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class AppUser {

    @Id
    @SequenceGenerator(name="user_sequence_generator", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "user_sequence_generator")
    private long id;

    @Enumerated(EnumType.STRING)
    private AppUserRole role;

    @Enumerated(EnumType.STRING)
    private Language language;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String name;
    private String phone;
    private String avatarFileName;
    private LocalDate birthdate;
    private String email;
    private String password;
    private int bonus;

    private LocalDate creationDate = LocalDate.of(2022, 1, 1);

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_order_list",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    @JsonIgnore
    private List<Order> orderList;

    @ElementCollection
    @JsonIgnore
    private List<Address> addressList;

    @ManyToMany
    @JoinTable(
            name = "user_favorite_items",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    @JsonIgnore
    private List<MenuItem> favoriteItemsList;

    public AppUser(){}

    public AppUser(String name, String email, int bonus) {
        this.name = name;
        this.email = email;
        this.bonus = bonus;
    }

    public AppUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AppUser(UserDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.phone = dto.getPhone();
        this.email = dto.getEmail();
        this.bonus = dto.getBonus();
        this.birthdate = dto.getBirthdate();
        this.password = dto.getPassword();

        this.gender = dto.getGender() != null ? Gender.valueOf(dto.getGender()) : Gender.UNKNOWN;
        this.language = Language.valueOf(dto.getLanguage());

        this.orderList = new ArrayList<>();
        this.addressList = new ArrayList<>();
        this.favoriteItemsList = new ArrayList<>();
    }

    public AppUser(AppUserRole role, Language language, Gender gender, String name, String phone, LocalDate birthdate, String email, LocalDate creationDate) {
        this.role = role;
        this.language = language;
        this.gender = gender;
        this.name = name;
        this.phone = phone;
        this.birthdate = birthdate;
        this.email = email;
        this.creationDate = creationDate;
    }

    public void addBonus(int bonusToAdd) {
        this.bonus += bonusToAdd;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", role=" + role +
                ", language=" + language +
                ", gender=" + gender +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", avatarFileName='" + avatarFileName + '\'' +
                ", birthdate=" + birthdate +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", bonus=" + bonus +
                ", creationDate=" + creationDate +
                ", orderList=" + orderList.size() +
                ", addressList=" + addressList.size() +
                ", favoriteItemsList=" + favoriteItemsList.size() +
                "}\n";
    }
}
