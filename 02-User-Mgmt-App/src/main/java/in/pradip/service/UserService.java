package in.pradip.service;

import in.pradip.dto.*;
import in.pradip.entity.CityEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    public List<CountryDto> getCountries();

    public  List<StateDto> getStates(Integer countryId);

    public List<CityDto> getCities(Integer stateId);

    public boolean isEmailUnique(String emailId);

    public boolean register(UserDto userDto);

    public UserDto login(String email, String pwd);

    public boolean resetPwd(ResetPwdDto resetPwdDto);

    public QuoteApiResponseDto getQuote();
}
