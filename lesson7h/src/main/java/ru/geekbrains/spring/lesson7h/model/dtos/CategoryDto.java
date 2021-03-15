package ru.geekbrains.spring.lesson7h.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.spring.lesson7h.model.entities.Category;

@Data
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private String title;

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.title = category.getTitle();
    }
}