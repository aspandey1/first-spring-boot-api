package com.ashish.contentcalander.controller;

import com.ashish.contentcalander.model.Content;
import com.ashish.contentcalander.repository.ContentCollectionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/content")
@CrossOrigin()
public class ContentController {

    private final ContentCollectionRepository repository;

    @Autowired
    public ContentController(ContentCollectionRepository repository) {
        this.repository = repository;
    }

    // GET REQUEST
    // Make a request and find all pieces of content
    @GetMapping("")
    public List<Content> findAll(){
        return repository.findAll();
    }

    // GET REQUEST
    // Make a request and find content by ID
    @GetMapping("/{id}")
    public Content findById(@PathVariable Integer id){
        return repository.findByID(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found"));
    }

    // POST REQUEST
    // Make a request to create new content
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@Valid @RequestBody Content content){
        repository.save(content);
    }

    // PUT REQUEST
    // Make a update to a content by ID
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Content content, @PathVariable Integer id){
        if(!repository.existsById(id)){
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found");
        }
        repository.save(content);
    }

    // DELETE REQUEST
    // Make a delete request by content ID
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        repository.delete(id);
    }
}
