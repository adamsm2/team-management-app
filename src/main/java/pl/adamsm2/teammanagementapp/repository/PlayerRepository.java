package pl.adamsm2.teammanagementapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.adamsm2.teammanagementapp.model.Player;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    boolean existsByNumber(Integer number);

    boolean existsByNumberAndTeamName(Integer number, String team_name);

    List<Player> findAllByTeamName(String team_name);
}
