package pl.coderslab.charity.donation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.coderslab.charity.category.Category;
import pl.coderslab.charity.institution.Institution;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = Donation.TABLE)
@Getter
@Setter
@RequiredArgsConstructor
public class Donation {
    public static final String TABLE = "donation";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Integer quantity;
    @ManyToOne
    private Category categories;
    @ManyToOne
    private Institution institution;
    private String street;
    private String city;
    private String zipCode;
    private LocalDate pickUpDate;
    private LocalTime pickUpTime;
    private String pickUpComment;
}
