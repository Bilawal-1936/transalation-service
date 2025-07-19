package com.digital_tolk.transalation_service.controller;

import com.digital_tolk.transalation_service.dto.TranslationDTO;
import com.digital_tolk.transalation_service.service.TranslationService;
import com.digital_tolk.transalation_service.entity.TranslationDomain;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@SecurityRequirement(name = "bearerAuth") // << This adds JWT requirement to Swagger
@RestController
@RequestMapping("/api/v1/translations")
public class TranslationController {

    @Autowired
    private TranslationService translationService;

    @PostMapping
    public ResponseEntity<TranslationDomain> create(@RequestBody TranslationDTO dto) {
        return ResponseEntity.ok(translationService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TranslationDomain> update(@PathVariable Long id, @RequestBody TranslationDTO dto) {
        return ResponseEntity.ok(translationService.update(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<TranslationDomain>> getAll() {
        return ResponseEntity.ok(translationService.getAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<TranslationDomain>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(translationService.search(keyword));
    }

    @GetMapping("/export")
    public ResponseEntity<List<TranslationDomain>> export(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime since) {
        return ResponseEntity.ok(translationService.exportJson(since));
    }
}
