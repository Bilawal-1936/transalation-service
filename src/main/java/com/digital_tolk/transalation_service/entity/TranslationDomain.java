package com.digital_tolk.transalation_service.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "translations")
public class TranslationDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "locale")
    private String locale;
    @Column(name = "translation_key")
    private String key;
    @Column(name = "translation_value")
    private String value;
    @Column(name = "context")
    private String context;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
