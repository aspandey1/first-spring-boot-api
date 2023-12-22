package com.ashish.contentcalander.repository;

import com.ashish.contentcalander.model.Content;
import com.ashish.contentcalander.model.Status;
import com.ashish.contentcalander.model.Type;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentCollectionRepository {

    private final List<Content> contentArrayList = new ArrayList<>();

    public ContentCollectionRepository(){

    }

    public List<Content> findAll() {
        return contentArrayList;
    }

    public Optional<Content> findByID(Integer id){
        return contentArrayList.stream().filter(c -> c.id().equals(id)).findFirst();
    }

    public boolean existsById(Integer id){
        return contentArrayList.stream().filter(c -> c.id().equals(id)).count() == 1;
    }

    public void save(Content content) {
        contentArrayList.removeIf(c -> c.id().equals(content.id()));
        contentArrayList.add(content);
    }

    public void delete(Integer id){
        contentArrayList.removeIf(c -> c.id().equals(id));
    }

    @PostConstruct
    private void init(){
        Content content1 = new Content(
                1,
                "My First Blog Post",
                "First Content",
                Status.IDEA,
                Type.ARTICLE,
                LocalDateTime.now(),
                null,
                "");
        Content content2 = new Content(
                2,
                "My Second Blog Post",
                "Second Content",
                Status.IDEA,
                Type.ARTICLE,
                LocalDateTime.now(),
                null,
                "");
        contentArrayList.add(content1);
        contentArrayList.add(content2);
    }





}
