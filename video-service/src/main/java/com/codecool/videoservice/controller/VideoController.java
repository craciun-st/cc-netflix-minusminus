package com.codecool.videoservice.controller;

import com.codecool.videoservice.controller.exceptions.VideoNotFoundException;
import com.codecool.videoservice.model.Video;
import com.codecool.videoservice.repositories.VideoRepo;
import com.codecool.videoservice.representation.VideoModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
// Use the below annotation if you want some other hypermedia standard than HAL (e.g. JSON_COLLECTION)
//@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class VideoController {


    private VideoRepo videoRepo;
    private VideoModelAssembler restAssembler;


    @Autowired
    public VideoController(VideoRepo videoRepo, VideoModelAssembler restAssembler) {
        this.videoRepo = videoRepo;
        this.restAssembler = restAssembler;
    }

    @GetMapping(value = "/videos", produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<EntityModel<Video>> handleGetAllVideos() {
        List<Video> videos = videoRepo.findAll();
        return restAssembler.toCollectionModel(videos);
    }

    @GetMapping(value = "/video/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EntityModel<Video> handleGetOneVideo(
            @PathVariable
            Long id
    ) {
        Video videoForId = videoRepo.findById(id).orElseThrow(() -> new VideoNotFoundException(id));
        return restAssembler.toModel(videoForId);
    }
}
