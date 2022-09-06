package com.example.springsecuritydatabase.serviceimpl;

import com.example.springsecuritydatabase.entity.Lesson;
import com.example.springsecuritydatabase.entity.Video;
import com.example.springsecuritydatabase.repository.LessonRepository;
import com.example.springsecuritydatabase.repository.VideoRepository;
import com.example.springsecuritydatabase.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;
    private final LessonRepository lessonRepository;


    @Override
    public List<Video> getAllVideo() {
        return videoRepository.findAll();
    }

    public void addVideo(Long id, Video video){
        Lesson lesson = lessonRepository.findLessonById(id);
        lesson.setVideo(video);
        video.setLesson(lesson);
        videoRepository.save(video);
    }

    public Video getVideoById(Long id ){
        return videoRepository.findVideoById(id);
    }

    @Override
    public void updateVideo(Long videoId, Video video) {
    Video video1 = videoRepository.findVideoById(videoId);
    video1.setVodeoName(video.getVodeoName());
    video1.setVideoLink(video.getVideoLink());
    videoRepository.save(video1);
    }

    public void  deleteVideo(Long id){
        Video video = videoRepository.findVideoById(id);
        video.setLesson(null);
        videoRepository.delete(video);
    }
}
