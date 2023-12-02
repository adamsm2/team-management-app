package pl.adamsm2.teammanagementapp.dto;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
public record TeamResource(
        String name,
        String abbreviation
) {
}
