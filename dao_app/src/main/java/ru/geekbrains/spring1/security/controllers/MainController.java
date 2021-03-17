package ru.geekbrains.spring1.security.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.spring1.security.models.entities.User;
import ru.geekbrains.spring1.security.models.dtos.UserDto;
import ru.geekbrains.spring1.security.services.UserService;

import java.security.Principal;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MainController {
    private final UserService userService;

//GET .../app/score/inc - увеличивает балл текущего пользователя
//GET .../app/score/dec - уменьшает балл текущего пользователя
//GET .../app/score/get/current - показывает балл вошедшего пользователя
//GET .../app/score/get/{id} - показывает балл пользователя с указанным id
// (доступнотолько для залогиненных пользователей)

    @GetMapping("/score/get/current")
    public UserDto getScoreCurrent(Principal principal) {
        return userService.findDtoByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("unable to find user by username: " + principal.getName()));
    }

    @GetMapping("/score/get/{id}")
    public UserDto getScoreById(@PathVariable String id) {
        return userService.findDtoById(Long.parseLong(id))
                .orElseThrow(() -> new RuntimeException("unable to find user by id: " + id));
    }

    @GetMapping("/score/inc")
    public UserDto incScoreCurrent(Principal principal) {
        return userService.addScoreByUsername(principal.getName(), 1);
    }

    @GetMapping("/score/dec")
    public UserDto decScoreCurrent(Principal principal) {
        return userService.addScoreByUsername(principal.getName(), -1);
    }
}
