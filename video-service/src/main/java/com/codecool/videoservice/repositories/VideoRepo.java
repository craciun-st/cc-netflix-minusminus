package com.codecool.videoservice.repositories;

import com.codecool.videoservice.model.Video;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepo extends CrudRepository<Video, Long> {

}
