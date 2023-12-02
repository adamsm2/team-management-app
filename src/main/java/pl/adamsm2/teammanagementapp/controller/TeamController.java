package pl.adamsm2.teammanagementapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.adamsm2.teammanagementapp.dto.CreateTeamRequest;
import pl.adamsm2.teammanagementapp.dto.TeamResource;
import pl.adamsm2.teammanagementapp.form.CreateTeamForm;
import pl.adamsm2.teammanagementapp.service.TeamService;

import java.util.List;

@Controller
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @GetMapping
    public String getTeams(Model model) {
        List<TeamResource> teams = teamService.getTeams();
        model.addAttribute("teams", teams);
        return "teams";
    }

    @GetMapping("/create")
    public String getCreateTeamForm(Model model) {
        model.addAttribute("createTeamForm", new CreateTeamForm());
        return "create-team-form";
    }

    @PostMapping("/create")
    public String createTeam(@ModelAttribute("createTeamForm") @Valid CreateTeamForm createTeamForm, BindingResult result) {
        if (result.hasErrors()) {
            return "create-team-form";
        }
        teamService.createTeam(new CreateTeamRequest(createTeamForm.getName(), createTeamForm.getAbbreviation()));
        return "redirect:/teams";
    }

}
