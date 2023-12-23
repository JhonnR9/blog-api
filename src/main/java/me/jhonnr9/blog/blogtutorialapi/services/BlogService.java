package me.jhonnr9.blog.blogtutorialapi.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.jhonnr9.blog.blogtutorialapi.factories.BlogFactory;
import me.jhonnr9.blog.blogtutorialapi.models.Blog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BlogService {
    private final List<Blog> blogs;
    private final BlogFactory blogFactory;

    private static final Logger logger = LoggerFactory.getLogger(BlogService.class);

    public BlogService(){
        blogs = new ArrayList<>();
        blogFactory = new BlogFactory();
    }

    public Blog create(JsonNode jsonBlog){
        Blog newBlog = blogFactory.createBlogFromJson(jsonBlog);
        blogs.add(newBlog);

        return newBlog;
    }

    public Blog updateBlog(Blog oldBlog, JsonNode updatedBlog){

        Blog updatedBlogObject = blogFactory.createBlogFromJson(updatedBlog);

        if(updatedBlogObject.getTitle() == null){
            updatedBlogObject.setTitle(oldBlog.getTitle());
        }

        if (updatedBlogObject.getDescription() == null){
            updatedBlogObject.setDescription(oldBlog.getDescription());
        }

        updatedBlogObject.setCreateAt(oldBlog.getCreateAt());
        updatedBlogObject.setUpdatedAt(LocalDateTime.now());
        updatedBlogObject.setId(oldBlog.getId());

        int blogIndex = blogs.indexOf(oldBlog);
        blogs.set(blogIndex, updatedBlogObject);

        return updatedBlogObject;

    }

    public Blog retriveBlog(long id){
        return blogs.stream()
                .filter(blog -> id == blog.getId())
                .findFirst()
                .orElse(null);

    }

    public List<Blog> getAll(){
        return Collections.unmodifiableList(blogs);
    }

    public boolean deleteBlog(Blog blog){
        if (blogs.contains(blog)) {
            blogs.remove(blog);
            logger.info("Blog with ID " + blog.getId() + " deleted.");
            return true;
        } else {
            logger.error("Attempted to delete a non-existing blog with ID: " + blog.getId());
            return false;
        }
    }

    public boolean isJSONValid(String jsonInString){
        logger.info(jsonInString);
        try {
            return new ObjectMapper().readTree(jsonInString) != null;
        } catch (IOException e){
            return false;
        }
    }

}
