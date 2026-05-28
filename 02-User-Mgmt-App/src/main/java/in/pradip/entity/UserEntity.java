package in.pradip.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Setter
@Getter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String name;

    private String email;

    private String pwd;

    private String passUpdated;

    private Long phno;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private CountryEntity country;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private StateEntity state;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityEntity city;
}
