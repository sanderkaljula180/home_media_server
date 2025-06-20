package dev.sanderk.home_media_server.service;

import dev.sanderk.home_media_server.model.Video;
import dev.sanderk.home_media_server.repository.VideoRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class VideoService {

    private final VideoRepository videoRepository;

    final int CHUNK_SIZE = 4 * 1024 * 1024;

    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public List<Video> getAllVideos() {
        List<Video> listOfAllVideos = videoRepository.findAll();
        return listOfAllVideos;
    }

    public ResponseEntity<byte[]> streamSelectedVideo(String httpRangeList, String videoFormat, String videoName) throws IOException {

        String fullVideoFileName = videoName + "." + videoFormat;
        Path videoFilePath = Paths.get("video", fullVideoFileName);
        File videoFile = Optional.of(videoFilePath.toFile())
                .filter(File::exists)
                .orElseThrow(() -> new FileNotFoundException("File not found: " + fullVideoFileName));

        String[] splitIntoRegularRange = httpRangeList.split("=");
        String[] splitHttpRangeListIntoStart = splitIntoRegularRange[1].split("-");

        int httpRangeListStart = Integer.parseInt(splitHttpRangeListIntoStart[0]);


        RandomAccessFile raf = new RandomAccessFile(videoFile, "r");
        Long videoFileFullLength = raf.length();
        raf.seek(httpRangeListStart);

        // WTF was this. I was overshooting the end of file and asked chatgpt help
        int httpRangeListEnd = Math.min(httpRangeListStart + CHUNK_SIZE - 1, videoFileFullLength.intValue() - 1);


        int videoBytesRange = httpRangeListEnd - httpRangeListStart + 1;

        byte[] videoFileBytes = new byte[Math.toIntExact(videoBytesRange)];
        raf.read(videoFileBytes);
        raf.close();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "video/mp4");
        headers.set(HttpHeaders.ACCEPT_RANGES, "bytes");
        headers.set(HttpHeaders.CONTENT_LENGTH, String.valueOf(videoBytesRange)); // length = end - start + 1
        headers.set(HttpHeaders.CONTENT_RANGE, "bytes " + httpRangeListStart + "-" + httpRangeListEnd + "/" + videoFileFullLength);

        return new ResponseEntity<>(videoFileBytes, headers, HttpStatus.PARTIAL_CONTENT);

    }

}
