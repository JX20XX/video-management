package org.example.service;

import org.example.bean.VideoBean;
import org.example.constant.VideoStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class VideoTranscodeService {
    private Logger logger = LoggerFactory.getLogger(VideoTranscodeService.class);

    @Autowired
    private VideoService videoService;
    @Async
    public void transcodeVideo(Long videoID) {
        VideoBean videoBean = videoService.videoDetail(videoID);
        if (videoBean.getStatus().getStatusCode() == VideoStatus.PENDING.getStatusCode()) {
            logger.info("start to transcode the video file");
            try {
                Thread.sleep(3000L);
                videoService.completeVideo(videoBean);
            } catch (InterruptedException e) {
                logger.error("transcode video file failed:", e.getMessage());
                videoService.uncompletedVideo(videoBean);
            }
        } else {
            logger.warn("the video status is " + videoBean.getStatus().getStatusDesc());
        }
    }
}
