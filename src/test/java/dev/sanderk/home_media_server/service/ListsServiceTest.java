package dev.sanderk.home_media_server.service;

import dev.sanderk.home_media_server.dto.SeriesListDTO;
import dev.sanderk.home_media_server.helper.CreateSeriesObject;
import dev.sanderk.home_media_server.model.Series;
import dev.sanderk.home_media_server.repository.SeriesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ListsServiceTest {
    @Mock
    private SeriesRepository seriesRepository;

    @InjectMocks
    private ListsServiceImpl listsService;

    @Test
    void getMainScreenSeriesListTest_Success() {
        // Arrange
        List<Series> listOfSeries = CreateSeriesObject.newListOfSeriesForTest();
        when(seriesRepository.findAll()).thenReturn(listOfSeries);

        // Act
        List<SeriesListDTO> result = listsService.seriesListDTO();

        // Assert
        verify(seriesRepository, times(1)).findAll();
        assertEquals(2, result.size());
        assertEquals("seriesName", result.get(0).getSeries_name());
        assertEquals(5.2, result.get(0).getRating());
        assertEquals("https://example.com/image.jpg", result.get(0).getThumbnail_url());
        assertEquals("seriesName", result.get(1).getSeries_name());
        assertEquals(5.2, result.get(1).getRating());
        assertEquals("https://example.com/image.jpg", result.get(1).getThumbnail_url());
    }
}
