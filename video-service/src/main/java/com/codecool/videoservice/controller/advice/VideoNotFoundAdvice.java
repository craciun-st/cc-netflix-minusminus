package com.codecool.videoservice.controller.advice;

import com.codecool.videoservice.controller.exceptions.VideoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class VideoNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(VideoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String handleVideoNotFound(VideoNotFoundException exception) {
        return exception.getMessage();
    }
}
