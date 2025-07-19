package com.digital_tolk.transalation_service.service;

import com.digital_tolk.transalation_service.dto.TranslationDTO;
import com.digital_tolk.transalation_service.entity.TranslationDomain;

import java.time.LocalDateTime;
import java.util.List;

public interface TranslationService {
    TranslationDomain create(TranslationDTO dto);
    TranslationDomain update(Long id, TranslationDTO dto);
    List<TranslationDomain> getAll();
    List<TranslationDomain> search(String keyword);
    List<TranslationDomain> exportJson(LocalDateTime since);
}
