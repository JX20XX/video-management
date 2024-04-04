import org.example.bean.VideoBean;
import org.example.Main;
import org.example.constant.VideoStatus;
import org.example.dao.VideoRepository;
import org.example.service.VideoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest(classes = {Main.class})
public class TestVideo {

    @Autowired
    private VideoRepository videoRepository;

    @Test
    public void addTestVideos() {
        VideoBean video = new VideoBean();
        video.setTitle("test video");
        video.setDescription("this is test video metadata adding by running junit test code");
        video.setUploadTimestamp(LocalDateTime.now());
        video.setDuration(1800L);
        video.setResolution("1080P");
        video.setFormat("AVC/H.264");
        video.setStatus(VideoStatus.PENDING);
        videoRepository.save(video);
    }

    @Autowired
    private VideoService videoService;
    @Test
    public void allVideos() {
        Iterable<VideoBean> videoBeans = videoService.allVideos();
        for (VideoBean videoBean : videoBeans) {
            System.out.println(videoBean.getTitle());
        }
    }
}
