package com.digital_tolk.transalation_service;

import com.digital_tolk.transalation_service.dto.TranslationDTO;
import com.digital_tolk.transalation_service.entity.TranslationDomain;
import com.digital_tolk.transalation_service.repository.TranslationRepository;
import com.digital_tolk.transalation_service.service.implementation.TranslationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TranslationServiceImplTest {

    @Mock
    private TranslationRepository repo;

    @InjectMocks
    private TranslationServiceImpl service;

    @Test
    void testCreate() {
        TranslationDTO dto = new TranslationDTO("en", "greeting", "Hello", "home");
        TranslationDomain saved = TranslationDomain.builder()
                .id(1L).locale("en").key("greeting").value("Hello").context("home").build();

        when(repo.save(any())).thenReturn(saved);

        TranslationDomain result = service.create(dto);
        assertEquals("Hello", result.getValue());
    }

    @Test
    void testUpdate() {
        Long id = 1L;
        TranslationDTO dto = new TranslationDTO("en", "farewell", "Bye", "app");
        TranslationDomain existing = TranslationDomain.builder()
                .id(id).locale("en").key("hello").value("Hello").context("web").build();

        when(repo.findById(id)).thenReturn(Optional.of(existing));
        when(repo.save(any())).thenAnswer(inv -> inv.getArgument(0));

        TranslationDomain result = service.update(id, dto);

        assertEquals("Bye", result.getValue());
        assertEquals("farewell", result.getKey());
        assertEquals("app", result.getContext());
    }

    @Test
    void testUpdateNotFound() {
        when(repo.findById(99L)).thenReturn(Optional.empty());

        TranslationDTO dto = new TranslationDTO("en", "farewell", "Bye", "app");

        assertThrows(NoSuchElementException.class, () -> service.update(99L, dto));
    }

    @Test
    void testSearch() {
        String keyword = "hello";
        List<TranslationDomain> mockResults = Arrays.asList(
                TranslationDomain.builder().key("hello").value("Hello").build()
        );

        when(repo.findByKeyContainingIgnoreCase(keyword)).thenReturn(mockResults);

        List<TranslationDomain> result = service.search(keyword);
        assertEquals(1, result.size());
        assertEquals("hello", result.get(0).getKey());
    }

    @Test
    void testExportSince() {
        LocalDateTime since = LocalDateTime.now().minusDays(1);
        List<TranslationDomain> mockResults = Arrays.asList(
                TranslationDomain.builder().key("hi").value("Hi").updatedAt(LocalDateTime.now()).build()
        );

        when(repo.findByUpdatedAtAfter(since)).thenReturn(mockResults);

        List<TranslationDomain> result = service.exportJson(since);
        assertEquals(1, result.size());
        assertTrue(result.get(0).getUpdatedAt().isAfter(since));
    }
}