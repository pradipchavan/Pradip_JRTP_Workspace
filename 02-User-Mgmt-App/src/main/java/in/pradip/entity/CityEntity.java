package in.pradip.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "city_master")
@Setter
@Getter
public class CityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cityId;

    private String cityName;

    @ManyToOne//many cities belongs to one state
    @JoinColumn(name = "state_id")
    private StateEntity state;
}
