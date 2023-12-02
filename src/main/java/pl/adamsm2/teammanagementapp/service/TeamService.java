package pl.adamsm2.teammanagementapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.adamsm2.teammanagementapp.dto.CreateTeamRequest;
import pl.adamsm2.teammanagementapp.dto.TeamResource;
import pl.adamsm2.teammanagementapp.mapper.TeamMapper;
import pl.adamsm2.teammanagementapp.repository.TeamRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    @Transactional(readOnly = true)
    public List<TeamResource> getTeams() {
        return teamRepository.findAll().stream()
                .map(teamMapper::toResource)
                .toList();
    }

    @Transactional
    public void createTeam(CreateTeamRequest createTeamRequest) {
        teamRepository.findByName(createTeamRequest.name())
                .ifPresentOrElse(
                        team -> {
                            throw new IllegalArgumentException("Team with name " + team.getName() + " already exists");
                        },
                        () -> teamRepository.save(teamMapper.toEntity(createTeamRequest))
                );
    }

    @Transactional
    public void deleteTeam(String name) {
        teamRepository.findByName(name)
                .ifPresentOrElse(
                        teamRepository::delete,
                        () -> {
                            throw new IllegalArgumentException("Team with name " + name + " does not exist");
                        }
                );
    }

    @Transactional(readOnly = true)
    public boolean doesTeamExist(String teamName) {
        return teamRepository.existsByName(teamName);
    }
}
