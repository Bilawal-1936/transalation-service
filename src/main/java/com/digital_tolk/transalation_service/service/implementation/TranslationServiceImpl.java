package com.digital_tolk.transalation_service.service.implementation;


import com.digital_tolk.transalation_service.dto.TranslationDTO;
import com.digital_tolk.transalation_service.entity.TranslationDomain;
import com.digital_tolk.transalation_service.repository.TranslationRepository;
import com.digital_tolk.transalation_service.service.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TranslationServiceImpl implements TranslationService {

    @Autowired
    private TranslationRepository repo;

    @Override
    public TranslationDomain create(TranslationDTO dto) {
        return repo.save(TranslationDomain.builder()
                .locale(dto.getLocale())
                .key(dto.getKey())
                .value(dto.getValue())
                .context(dto.getContext())
                .updatedAt(LocalDateTime.now())
                .build());
    }

    @Override
    public TranslationDomain update(Long id, TranslationDTO dto) {
        TranslationDomain t = repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Translation with ID " + id + " not found"));
        t.setLocale(dto.getLocale());
        t.setKey(dto.getKey());
        t.setValue(dto.getValue());
        t.setContext(dto.getContext());
        t.setUpdatedAt(LocalDateTime.now());
        return repo.save(t);
    }

    @Override
    public List<TranslationDomain> getAll() {
        return repo.findAll();
    }

    @Override
    public List<TranslationDomain> search(String keyword) {
        return repo.findByKeyContainingIgnoreCase(keyword);
    }

    @Override
    public List<TranslationDomain> exportJson(LocalDateTime since) {
        return repo.findByUpdatedAtAfter(since);
    }
}

