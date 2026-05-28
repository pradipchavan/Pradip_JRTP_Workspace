package in.pradip.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "state_master")
@Setter
@Getter
public class StateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stateId;

    private String stateName;

    @ManyToOne//many states belongs to one country
    @JoinColumn(name = "country_id")
    private CountryEntity country;
}
