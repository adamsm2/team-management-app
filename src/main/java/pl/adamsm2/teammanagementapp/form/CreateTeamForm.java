package pl.adamsm2.teammanagementapp.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTeamForm {

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 255, message = "Max name length = 255")
    private String name;

    @NotBlank(message = "Abbreviation cannot be blank")
    @Size(max = 3, message = "Max abbreviation length = 3")
    private String abbreviation;

}
