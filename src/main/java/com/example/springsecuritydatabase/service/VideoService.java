package com.example.springsecuritydatabase.service;


import com.example.springsecuritydatabase.entity.Video;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface VideoService {
    List<Video> getAllVideo();
    void addVideo(Long lessonId,Video video);
    Video getVideoById(Long id);
    void updateVideo(Long videoId,Video video);
    void deleteVideo(Long videoId);
}
