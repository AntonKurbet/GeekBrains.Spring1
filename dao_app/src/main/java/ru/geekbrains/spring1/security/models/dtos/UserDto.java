package ru.geekbrains.spring1.security.models.dtos;

import lombok.Data;
import ru.geekbrains.spring1.security.models.entities.*;

@Data
public class UserDto {
    private String name;
    private long score;

    public UserDto(User user) {
        name = user.getUsername();
        score = user.getScore();
    }
}
