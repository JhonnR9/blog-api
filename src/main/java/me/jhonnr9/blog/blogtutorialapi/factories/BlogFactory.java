package me.jhonnr9.blog.blogtutorialapi.factories;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.jhonnr9.blog.blogtutorialapi.models.Blog;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.concurrent.atomic.AtomicLong;

public class BlogFactory {

    private static final AtomicLong idGenerator = new AtomicLong(0);

    private String parseTitle(JsonNode blog){
        return blog.has("title") ? blog.get("title").asText() : null;
    }

    private String parseDescription(JsonNode blog){
        return blog.has("description") ? blog.get("description").asText() : null;
    }
    public Blog createBlogFromJson(JsonNode jsonBlog){
        long id = idGenerator.getAndIncrement();
        String title = parseTitle(jsonBlog);
        String description = parseDescription(jsonBlog);
        LocalDateTime createAt = LocalDateTime.now();

        return  new Blog(id, title, description, createAt, createAt);
    }

}
