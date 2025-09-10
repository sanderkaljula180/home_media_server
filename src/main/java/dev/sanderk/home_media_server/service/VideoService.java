package dev.sanderk.home_media_server.service;

import dev.sanderk.home_media_server.model.EpisodeVideo;
import dev.sanderk.home_media_server.repository.MovieRepository;
import dev.sanderk.home_media_server.repository.SeriesRepository;
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
    private final MovieRepository movieRepository;
    private final SeriesRepository seriesRepository;

    final int CHUNK_SIZE = 4 * 1024 * 1024;

    public VideoService(VideoRepository videoRepository, MovieRepository movieRepository, SeriesRepository seriesRepository) {
        this.videoRepository = videoRepository;
        this.movieRepository = movieRepository;
        this.seriesRepository = seriesRepository;
    }

    public List<EpisodeVideo> getAllVideos() {
        List<EpisodeVideo> listOfAllEpisodeVideos = videoRepository.findAll();
        return listOfAllEpisodeVideos;
    }

    public ResponseEntity<byte[]> streamSelectedVideo(String httpRangeList, String contentName, String contentType) throws IOException {

        Path contentFilePath = "movie".equals(contentType)
                ? movieRepository.findMoviePath(contentName)
                : seriesRepository.findSeriesPath(contentName);

        File videoFile = Optional.ofNullable(contentFilePath)
                .map(Path::toFile)
                .filter(File::exists)
                .orElseThrow(() -> new FileNotFoundException("File not found: " + contentName));

        String[] splitIntoRegularRange = httpRangeList.split("=");
        String[] splitHttpRangeListIntoStart = splitIntoRegularRange[1].split("-");

        int httpRangeListStart = Integer.parseInt(splitHttpRangeListIntoStart[0]);

        RandomAccessFile raf = new RandomAccessFile(videoFile, "r");
        Long videoFileFullLength = raf.length();
        raf.seek(httpRangeListStart);

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
