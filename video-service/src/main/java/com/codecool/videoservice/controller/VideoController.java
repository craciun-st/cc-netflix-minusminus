package com.codecool.videoservice.controller;

import com.codecool.videoservice.controller.exceptions.VideoNotFoundException;
import com.codecool.videoservice.model.Video;
import com.codecool.videoservice.repositories.VideoRepo;
import com.codecool.videoservice.representation.VideoModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        //TODO call Video Recommendation Service to also return recommendation data for this id
        Video videoForId = videoRepo.findById(id).orElseThrow(() -> new VideoNotFoundException(id));
        return restAssembler.toModel(videoForId);
    }

    // POST to a collection route creates a new item in the collection
    @PostMapping(value ="/videos", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> handleNewVideoPost(
            @RequestBody
            Video someVideo
    ) {
        //TODO call Video Recommendation Service to POST recommendation data
        EntityModel<Video> entityModel = restAssembler.toModel(videoRepo.save(someVideo));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    // PUT to an item route replaces the item in the collection (if it exists), otherwise creates it (if possible)
    @PutMapping(value = "/video/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> handleReplacingOneVideo(
            @RequestBody
            Video someVideo,

            @PathVariable
            Long id
    ) {
        //TODO call Video Recommendation Service to PUT recommendation data there
        Video updatedVideo = videoRepo.findById(id)
                .map(video -> {
                    video.setName(someVideo.getName());
                    video.setUrl(someVideo.getUrl());
                    return videoRepo.save(video);
                })
                .orElseGet( () -> {
                            someVideo.setId(id);
                            return videoRepo.save(someVideo);
                        }
                );
        EntityModel<Video> entityModel = restAssembler.toModel(updatedVideo);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

}
