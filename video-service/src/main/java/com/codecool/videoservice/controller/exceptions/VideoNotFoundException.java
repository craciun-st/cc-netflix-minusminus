package com.codecool.videoservice.controller.exceptions;

public class VideoNotFoundException extends RuntimeException {

    public VideoNotFoundException(Long id) {
        super("Could not find video " + id);
    }

}
