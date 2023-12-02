package pl.adamsm2.teammanagementapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.adamsm2.teammanagementapp.dto.CreatePlayerRequest;
import pl.adamsm2.teammanagementapp.dto.PlayerResource;
import pl.adamsm2.teammanagementapp.form.CreatePlayerForm;
import pl.adamsm2.teammanagementapp.service.PlayerService;
import pl.adamsm2.teammanagementapp.service.TeamService;

import java.util.List;

@Controller
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;
    private final TeamService teamService;

    @GetMapping
    public String getPlayers(Model model) {
        List<PlayerResource> players = playerService.getPlayers();
        model.addAttribute("players", players);
        return "players";
    }

    @GetMapping("/create")
    public String getCreatePlayerForm(Model model) {
        model.addAttribute("createPlayerForm", new CreatePlayerForm());
        return "create-player-form";
    }

    @PostMapping("/create")
    public String createPlayer(@ModelAttribute("createPlayerForm") @Valid CreatePlayerForm createPlayerForm, BindingResult result) {
        if (!createPlayerForm.getTeamName().isBlank() && !teamService.doesTeamExist(createPlayerForm.getTeamName())) {
            result.rejectValue("teamName", "team.notExists", "Team does not exist");
        } else {
            if (createPlayerForm.getNumber() != null && playerService.doesPlayerWithNumberExistInTeam(createPlayerForm.getNumber(), createPlayerForm.getTeamName())) {
                result.rejectValue("number", "player.exists", "Player with this number already exists in this team");
            }
        }

        if (result.hasErrors()) {
            return "create-player-form";
        }
        playerService.createPlayer(new CreatePlayerRequest(createPlayerForm.getName(), createPlayerForm.getSurname(), createPlayerForm.getNumber(), createPlayerForm.getTeamName()));
        return "redirect:/players";
    }

    @GetMapping("/{teamName}")
    public String getPlayersByTeamName(Model model, @PathVariable String teamName) {
        List<PlayerResource> players = playerService.getPlayersByTeamName(teamName);
        model.addAttribute("teamName", teamName);
        model.addAttribute("players", players);
        return "players-in-team";
    }
}
