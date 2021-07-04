package com.codecool.videoservice.model;



import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "videos")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String name;


    private String url;

    public Video(Long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public Video() {
        this.name = "";
        this.url = "";
    }



    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
