package pl.adamsm2.teammanagementapp.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.adamsm2.teammanagementapp.dto.CreatePlayerRequest;
import pl.adamsm2.teammanagementapp.dto.PlayerResource;
import pl.adamsm2.teammanagementapp.model.Player;
import pl.adamsm2.teammanagementapp.model.Team;
import pl.adamsm2.teammanagementapp.repository.TeamRepository;

@Component
@RequiredArgsConstructor
public class PlayerMapper {

    private final TeamRepository teamRepository;

    public PlayerResource toResource(Player player) {
        return PlayerResource.builder()
                .id(player.getId())
                .name(player.getName())
                .surname(player.getSurname())
                .number(player.getNumber())
                .teamName(player.getTeam() != null ? player.getTeam().getName() : "")
                .build();
    }

    public Player toEntity(CreatePlayerRequest createPlayerRequest) {
        Team team = null;
        if (!createPlayerRequest.teamName().isBlank()) {
            team = teamRepository.findByName(createPlayerRequest.teamName())
                    .orElseThrow(() -> new IllegalArgumentException("Team with name " + createPlayerRequest.teamName() + " does not exist"));
        }
        return Player.builder()
                .name(createPlayerRequest.name())
                .surname(createPlayerRequest.surname())
                .number(createPlayerRequest.number())
                .team(team)
                .build();
    }
}
