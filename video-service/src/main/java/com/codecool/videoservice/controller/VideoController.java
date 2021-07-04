package com.codecool.videoservice.controller;

import com.codecool.videoservice.model.Video;
import com.codecool.videoservice.repositories.VideoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class VideoController {


    private VideoRepo videoRepo;

    @Autowired
    public VideoController(VideoRepo videoRepo) {
        this.videoRepo = videoRepo;
    }

    @GetMapping(value = "/videos", produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<EntityModel<Video>> handleGetAllVideos() {
        List<EntityModel<Video>> videosAsModels = videoRepo.findAll().stream()
                .map(video -> EntityModel.of(
                        video
                        // add links here
                        )
                )
                .collect(Collectors.toList());
        return CollectionModel.of(
                videosAsModels
                // add links here
        );
    }
}
