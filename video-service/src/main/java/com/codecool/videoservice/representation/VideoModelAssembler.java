package com.codecool.videoservice.representation;

import com.codecool.videoservice.controller.VideoController;
import com.codecool.videoservice.model.Video;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class VideoModelAssembler implements RepresentationModelAssembler<Video, EntityModel<Video>> {
    @Override
    public EntityModel<Video> toModel(Video video) {
        return EntityModel.of(
                video,
                linkTo(methodOn(VideoController.class).handleGetOneVideo(video.getId())).withSelfRel(),
                linkTo(methodOn(VideoController.class).handleGetAllVideos()).withRel("videos")
        );
    }

    @Override
    public CollectionModel<EntityModel<Video>> toCollectionModel(Iterable<? extends Video> videos) {
        CollectionModel<EntityModel<Video>> result =
                RepresentationModelAssembler.super.toCollectionModel(videos);   // super gives a plain collection

        //... therefore we must add any pertaining Links
        result.add(
                linkTo(methodOn(VideoController.class).handleGetAllVideos()).withSelfRel()
        );
        return result;
    }
}
