package pl.adamsm2.teammanagementapp.mapper;

import org.springframework.stereotype.Component;
import pl.adamsm2.teammanagementapp.dto.CreateTeamRequest;
import pl.adamsm2.teammanagementapp.dto.TeamResource;
import pl.adamsm2.teammanagementapp.model.Team;

@Component
public class TeamMapper {

    public TeamResource toResource(Team team) {
        return TeamResource.builder()
                .name(team.getName())
                .abbreviation(team.getAbbreviation())
                .build();
    }

    public Team toEntity(CreateTeamRequest updateTeamRequest) {
        return Team.builder()
                .name(updateTeamRequest.name())
                .abbreviation(updateTeamRequest.abbreviation())
                .build();
    }
}
