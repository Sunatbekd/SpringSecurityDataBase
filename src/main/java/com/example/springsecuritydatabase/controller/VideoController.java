package com.example.springsecuritydatabase.controller;

import com.example.springsecuritydatabase.entity.Video;
import com.example.springsecuritydatabase.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/videos")
public class VideoController {

    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/getAllVideos/{lessonId}")
    public String getAllVideo(@PathVariable("lessonId") Long lessonId, Model model) {
        model.addAttribute("allVideo", videoService.getAllVideo());
        model.addAttribute("id", lessonId);
        return "/video/videos";
    }

    @GetMapping("/addVideo/{lessonId}")
    public String addVideo(@PathVariable("lessonId") Long id, Model model) {
        model.addAttribute("video", new Video());
        model.addAttribute("id", id);
        return "/video/addVideo";
    }

    @PostMapping("/saveVideo/{lessonId}")
    public String saveVideo(@PathVariable("lessonId") Long id,
                            @ModelAttribute("video") Video video) {
        videoService.addVideo(id, video);
        return "redirect:/videos/getAllVideos/" + id;
    }

    @GetMapping("/updateVideo/{videoId}")
    public String updateVideo(@PathVariable("videoId") Long id, Model model) {
        Video video = videoService.getVideoById(id);
        model.addAttribute("video", video);
        model.addAttribute("lessonId", video.getLesson().getId());
        return "/video/updateVideo";
    }

    @PostMapping("/saveUpdateVideo/{lessonId}/{videoId}")
    public String saveUpdateVideo(@PathVariable("videoId") Long videoId,
                                  @ModelAttribute("video") Video video,
                                  @PathVariable("lessonId")Long id) {
        videoService.updateVideo(videoId, video);
        return "redirect:/videos/getAllVideos/ " + id;
    }


    //    @RequestMapping("/edit/{id}")
//    public ModelAndView editProduct(@PathVariable("id") Long id){
//        ModelAndView modelAndView = new ModelAndView("edit_product");
//        Product product = productService.getProductById(id);
//        modelAndView.addObject(product);
//        return modelAndView;
//    }

//    public ModelAndView updateVideo(@PathVariable("id") Long id) {
//        ModelAndView modelAndView = new ModelAndView("/video/updateVideo");
//        Video video = videoService.getVideoById(id);
//        modelAndView.addObject(video);
//        return modelAndView;
//    }

    @RequestMapping("/deleteVideo/{lessonId}/{videoId}")
    public String deleteVideo(@PathVariable("lessonId") Long id, @PathVariable("videoId") Long videoId) {
        videoService.deleteVideo(videoId);
        return "redirect:/videos/getAllVideos/ " + id;
    }

}
