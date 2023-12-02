package pl.adamsm2.teammanagementapp.form;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlayerForm {

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 255, message = "Max name length = 255")
    private String name;

    @NotBlank(message = "Surname cannot be blank")
    @Size(max = 255, message = "Max surname length = 255")
    private String surname;

    @Min(value = 1, message = "Min player number = 1")
    @Max(value = 99, message = "Max player number = 99")
    private Integer number;

    private String teamName;
}
