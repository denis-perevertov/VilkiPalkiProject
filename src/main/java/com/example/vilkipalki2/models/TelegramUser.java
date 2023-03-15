package com.example.vilkipalki2.models;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="telegram_users")
public class TelegramUser {

    @Id
    @SequenceGenerator(name="telegram_id_generator", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "telegram_id_generator")
    private long id;

    private long userId;

    private long chatId;

    private String phone_number;

    private String full_name;

    public TelegramUser() {
    }

    public TelegramUser(long userId, long chatId, String phone_number) {
        this.userId = userId;
        this.chatId = chatId;
        this.phone_number = phone_number;
    }

    public TelegramUser(long userId, long chatId, String phone_number, String first_name, String last_name) {
        this.userId = userId;
        this.chatId = chatId;
        this.phone_number = phone_number;
        this.full_name = first_name + " " + last_name;
    }
}
