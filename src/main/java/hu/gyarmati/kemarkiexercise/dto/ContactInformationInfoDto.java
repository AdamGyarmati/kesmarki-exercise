package hu.gyarmati.kemarkiexercise.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactInformationInfoDto {
    private Long id;
    private String contactInfo;
    private String contactInformationType;
}
