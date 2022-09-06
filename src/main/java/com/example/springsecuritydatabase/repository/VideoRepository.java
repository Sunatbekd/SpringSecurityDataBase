package com.example.springsecuritydatabase.repository;

import com.example.springsecuritydatabase.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video,Long> {

    Video findVideoById(Long id);
}
