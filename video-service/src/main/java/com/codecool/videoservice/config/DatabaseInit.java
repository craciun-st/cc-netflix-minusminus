package com.codecool.videoservice.config;

import com.codecool.videoservice.model.Video;
import com.codecool.videoservice.repositories.VideoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DatabaseInit {

    private VideoRepo videoRepo;

    @Autowired
    public DatabaseInit(VideoRepo videoRepo) {
        this.videoRepo = videoRepo;
    }

    @PostConstruct
    private void fillInSomeData() {

        Video uncleBobExpectations = new Video(
                null,
                "Uncle Bob - Clean Code / Lesson 3",
                "https://www.youtube.com/watch?v=Qjywrq2gM8o&list=PLmmYSbUCWJ4x1GO839azG_BBw8rkh-zOj&index=3"
        );
        Video henneyIneffectiveHabits = new Video(
                null,
                "Kevlin Henney - Seven Ineffective Coding Habits of Many Programmers",
                "https://www.youtube.com/watch?v=ZsHMHukIlJY"

        );
        Video farleyMicroservices = new Video(
                null,
                "Dave Farley - The Problem With Microservices",
                "https://www.youtube.com/watch?v=zzMLg3Ys5vI"
        );

        Video functionalSpeedTricks = new Video(
                null,
                "Victor Rentea - Functional Programming Patterns with Java 8",
                "https://www.youtube.com/watch?v=F02LKnWJWF4"
        );

        videoRepo.save(uncleBobExpectations);
        videoRepo.save(henneyIneffectiveHabits);
        videoRepo.save(farleyMicroservices);
        videoRepo.save(functionalSpeedTricks);
    }
}
