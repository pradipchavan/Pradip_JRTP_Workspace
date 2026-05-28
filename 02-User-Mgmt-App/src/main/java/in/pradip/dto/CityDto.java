package in.pradip.dto;

import in.pradip.entity.StateEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class CityDto {
    private Integer cityId;

    private String cityName;

    private Integer stateId;
}
