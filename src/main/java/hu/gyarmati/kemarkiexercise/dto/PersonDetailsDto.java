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
public class PersonDetailsDto {
    private Long id;
    private String name;
    private List<AddressDetailsDto> addressList;
}
