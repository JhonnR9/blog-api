package me.jhonnr9.blog.blogtutorialapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
    private long id;
    private String title;
    private String description;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;

}
