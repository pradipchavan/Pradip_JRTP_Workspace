package in.pradip.dto;

import in.pradip.entity.CountryEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class StateDto {
    private Integer stateId;

    private String stateName;

    private Integer countryId;
}
