package pl.coderslab.charity.institution;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.coderslab.charity.donation.Donation;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = Institution.TABLE)
@Getter
@Setter
@RequiredArgsConstructor
public class Institution {
    public final static String TABLE = "institution";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String name;
    private String description;
    @OneToMany
    private List<Donation> donations;
}
