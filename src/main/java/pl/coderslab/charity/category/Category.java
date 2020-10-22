package pl.coderslab.charity.category;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.coderslab.charity.donation.Donation;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = Category.TABLE)
@Getter
@Setter
@RequiredArgsConstructor
public class Category {
    public final static String TABLE = "category";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Please provide name")
    private String name;
    @OneToMany
    private List<Donation> donations;


}
