package hu.gyarmati.kemarkiexercise.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDetailsDto {
    private Long id;
    private String addressType;
    private List<ContactInformationDetailsDto> contactInformationList;
}
