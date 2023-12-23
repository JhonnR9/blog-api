package me.jhonnr9.blog.blogtutorialapi.controllers;

import com.fasterxml.jackson.databind.JsonNode;

import me.jhonnr9.blog.blogtutorialapi.models.Blog;
import me.jhonnr9.blog.blogtutorialapi.services.BlogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


@RestController
@RequestMapping("/api-blogs/blogs")
public class BlogController {
    private static final Logger logger = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    private BlogService blogService;

    @GetMapping
    public ResponseEntity<List<Blog>> getAll(){
        List<Blog> blogs = blogService.getAll();

        logger.info("Blog List: " + blogs.toString());
        return ResponseEntity.status(HttpStatus.OK).body(blogs);
    }

    @PostMapping
    public ResponseEntity<Blog> createBlog (@RequestBody JsonNode blogJson) {
        try {
            if(blogService.isJSONValid(blogJson.toString())){
                Blog createdBlog = blogService.create(blogJson);
                return ResponseEntity.status(HttpStatus.CREATED).body(createdBlog);
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        } catch (Exception e){
            logger.error("JSON fields are not parsable. " + e);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }

    @PatchMapping(path= "/{id}", produces = {"application/json"})
    public ResponseEntity<Blog> updateBlog (@PathVariable("id") long id, @RequestBody JsonNode updatedBlogJson ) {
        logger.info("id: " + id);
        try {
            if(blogService.isJSONValid(updatedBlogJson.toString())){

                Blog oldBlog = blogService.retriveBlog(id);

                if(oldBlog == null){
                    logger.error("Blog not found for ID: " + id + "!");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                }else{
                    Blog updatedBlog = blogService.updateBlog(oldBlog, updatedBlogJson);
                    return ResponseEntity.ok(updatedBlog);
                }

            }else {
                logger.error("Bad request");
                return ResponseEntity.badRequest().body(null);
            }
        }catch(Exception e){
            logger.error("JSON fields are not parsable." + e);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }

    @GetMapping(path= "/{id}")
    public ResponseEntity<Blog> retrieveBlog(@PathVariable("id") long id){
        try {
            Blog foundBlog = blogService.retriveBlog(id);
            if (foundBlog != null) {
                return ResponseEntity.ok().body(foundBlog);
            }
            else {
                logger.error("blog with 'ID: " + id + "' not found");
                return ResponseEntity.notFound().build();
            }

        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }

    @DeleteMapping(path= "/{id}")
    public ResponseEntity<Blog> deleteBlog(@PathVariable("id") long id){
        try {
            Blog foundBlog = blogService.retriveBlog(id);
            boolean isDeleted = blogService.deleteBlog(foundBlog);
            if (foundBlog != null && isDeleted) {
                return ResponseEntity.noContent().build();
            }
            else {
                logger.error("blog with 'ID: " + id + "' not found");
                return ResponseEntity.notFound().build();
            }

        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }
}
