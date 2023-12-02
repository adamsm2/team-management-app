package pl.adamsm2.teammanagementapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    private Integer number;

    @ManyToOne
    @JoinColumn(name = "team_name", referencedColumnName = "name")
    private Team team;

}
