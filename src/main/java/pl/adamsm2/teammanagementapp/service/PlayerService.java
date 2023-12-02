package pl.adamsm2.teammanagementapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.adamsm2.teammanagementapp.dto.CreatePlayerRequest;
import pl.adamsm2.teammanagementapp.dto.PlayerResource;
import pl.adamsm2.teammanagementapp.mapper.PlayerMapper;
import pl.adamsm2.teammanagementapp.model.Player;
import pl.adamsm2.teammanagementapp.repository.PlayerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    @Transactional(readOnly = true)
    public List<PlayerResource> getPlayers() {
        return playerRepository.findAll().stream()
                .map(playerMapper::toResource)
                .toList();
    }

    @Transactional
    public void createPlayer(CreatePlayerRequest createPlayerRequest) {
        Player player = playerMapper.toEntity(createPlayerRequest);
        playerRepository.save(player);
    }

    @Transactional
    public void deletePlayer(Long id) {
        playerRepository.findById(id)
                .ifPresentOrElse(
                        playerRepository::delete,
                        () -> {
                            throw new IllegalArgumentException("Player with id " + id + " does not exist");
                        }
                );
    }

    @Transactional(readOnly = true)
    public boolean doesPlayerWithNumberExistInTeam(Integer number, String teamName) {
        return playerRepository.existsByNumberAndTeamName(number, teamName);
    }

    @Transactional(readOnly = true)
    public List<PlayerResource> getPlayersByTeamName(String teamName) {
        return playerRepository.findAllByTeamName(teamName).stream()
                .map(playerMapper::toResource)
                .toList();
    }
}
