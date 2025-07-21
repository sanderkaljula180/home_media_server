package dev.sanderk.home_media_server.controller;

import dev.sanderk.home_media_server.model.EpisodeVideo;
import dev.sanderk.home_media_server.service.VideoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    // API for seeing all videos. Should send list of videos with video name, duration and thumbnail
    @GetMapping
    public ResponseEntity<List<EpisodeVideo>> getAllVideos() {
        return new ResponseEntity<>(videoService.getAllVideos(), HttpStatus.OK);
    }

    // API for watching a video
    @GetMapping("/stream/{videoFormat}/{videoName}")
    public ResponseEntity<byte[]> streamSelectedVideo(@RequestHeader(value = "Range", required = false) String httpRangeList, @PathVariable("videoFormat") String videoFormat, @PathVariable("videoName") String videoName) throws IOException {
        return videoService.streamSelectedVideo(httpRangeList, videoFormat, videoName);
    }

}
