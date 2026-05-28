package in.pradip.dto;

import in.pradip.entity.CityEntity;
import in.pradip.entity.CountryEntity;
import in.pradip.entity.StateEntity;
import lombok.Data;

@Data
public class UserDto {
    private Integer userId;

    private String name;

    private String email;

    private String pwd;

    private String passUpdated;

    private Long phno;

    private Integer countryId;

    private Integer stateId;

    private Integer cityId;
}
