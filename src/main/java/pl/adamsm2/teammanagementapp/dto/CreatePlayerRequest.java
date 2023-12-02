package pl.adamsm2.teammanagementapp.dto;

public record CreatePlayerRequest(
        String name,
        String surname,
        Integer number,
        String teamName
) {
}
