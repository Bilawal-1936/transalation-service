package com.digital_tolk.transalation_service.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TranslationDTO {
    private String locale;
    private String key;
    private String value;
    private String context;
}
