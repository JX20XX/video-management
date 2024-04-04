package org.example.controller;

import org.example.bean.VideoBean;
import org.example.restful.Result;
import org.example.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController()
public class VideoController {

    @Autowired
    private VideoService videoService;
    @RequestMapping(value = "videos", method = RequestMethod.POST)
    public @ResponseBody Result<String> addVideo(@Validated @RequestBody VideoBean video) {
                videoService.addNewVideo(video);
        return Result.success("Saved");
    }

    @GetMapping("videos")
    public @ResponseBody Result<Iterable<VideoBean>> videos() {
        Iterable<VideoBean> videos = videoService.allVideos();
        return Result.success(videos);
    }

    @GetMapping("videos/{id}")
    public @ResponseBody Result<VideoBean> videoDetail(@PathVariable("id") Long id) {
        VideoBean video = videoService.videoDetail(id);
        return Result.success(video);
    }

    @PutMapping("videos/{id}")
    public Result<VideoBean> processVideo(@PathVariable("id") Long id) {
        VideoBean video =videoService.processVideo(id);
        return Result.success(video, "The video is already under processing");
    }

    @DeleteMapping("videos/{id}")
    public Result<VideoBean> deleteVideo(@PathVariable("id") Long id) {
        VideoBean videoDetail = videoService.deleteVideo(id);
        return Result.success(videoDetail, "The video has been deleted");
    }
}
