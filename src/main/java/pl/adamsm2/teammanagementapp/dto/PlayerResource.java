package pl.adamsm2.teammanagementapp.dto;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
public record PlayerResource(
        Long id,
        String name,
        String surname,
        Integer number,
        String teamName
) {
}
