package com.digital_tolk.transalation_service.repository;


import com.digital_tolk.transalation_service.entity.TranslationDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TranslationRepository extends JpaRepository<TranslationDomain, Long> {
    List<TranslationDomain> findByLocaleAndContext(String locale, String context);
    List<TranslationDomain> findByKeyContainingIgnoreCase(String key);
    List<TranslationDomain> findByUpdatedAtAfter(LocalDateTime timestamp);
}