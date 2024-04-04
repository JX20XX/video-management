package org.example.service;

import org.example.bean.VideoBean;
import org.example.constant.VideoStatus;
import org.example.dao.VideoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VideoService {
    private Logger logger = LoggerFactory.getLogger(VideoService.class);
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private VideoTranscodeService videoTranscodeService;

    public void addNewVideo(VideoBean newVideo) {
        newVideo.setStatus(VideoStatus.PENDING);
        videoRepository.save(newVideo);
    }

    public Iterable<VideoBean> allVideos() {
        return videoRepository.findAll();
    }

    public VideoBean videoDetail(Long videoID) {
        Optional<VideoBean> videoBeanOptional = videoRepository.findById(videoID);
        return videoBeanOptional.get();
    }

    public VideoBean processVideo(Long videoID) {
        VideoBean videoBean = this.videoDetail(videoID);
        if (videoBean.getStatus().getStatusCode() == VideoStatus.PENDING.getStatusCode()) {
            logger.info("video status is pending, start to process the video");
            videoBean.setStatus(VideoStatus.PROCESSING);
            videoRepository.save(videoBean);
            videoTranscodeService.transcodeVideo(videoID);
        } else {
            logger.warn("video status is not pending, the status is " + videoBean.getStatus().getStatusDesc());
        }

        return videoBean;
    }


    public void completeVideo(VideoBean videoBean) {
        videoBean.setStatus(VideoStatus.COMPLETED);
        videoRepository.save(videoBean);
    }

    public void uncompletedVideo(VideoBean videoBean) {
        videoBean.setStatus(VideoStatus.FAILED);
        videoRepository.save(videoBean);
    }

    public VideoBean deleteVideo(Long videoID) {
        VideoBean videoBean = this.videoDetail(videoID);
        videoRepository.delete(videoBean);
        return videoBean;
    }
}
