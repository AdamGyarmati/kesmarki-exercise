package hu.gyarmati.kemarkiexercise.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaveAndUpdatePersonDto {
    private String name;
}
