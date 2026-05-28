package in.pradip.service.impl;

import in.pradip.dto.*;
import in.pradip.entity.CityEntity;
import in.pradip.entity.CountryEntity;
import in.pradip.entity.StateEntity;
import in.pradip.entity.UserEntity;
import in.pradip.repo.CityRepo;
import in.pradip.repo.CountryRepo;
import in.pradip.repo.StateRepo;
import in.pradip.repo.UserRepo;
import in.pradip.service.EmailService;
import in.pradip.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private CountryRepo countryRepo;

    @Autowired
    private StateRepo stateRepo;

    @Autowired
    private CityRepo cityRepo;

    @Autowired
    private UserRepo userRepo;

    private ModelMapper mapper;

    private EmailService emailService;

    @Override
    public List<CountryDto> getCountries() {
        List<CountryEntity> countryEntityList = countryRepo.findAll();

        return  countryEntityList.stream()
                .map(country -> mapper.map(country, CountryDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public List<StateDto> getStates(Integer countryId) {

        List<StateEntity> stateEntityList = stateRepo.findByCountryCountryId(countryId);

        return stateEntityList.stream()
                       .map(state -> mapper.map(state, StateDto.class))
                       .collect(Collectors.toList());
    }

    @Override
    public List<CityDto> getCities(Integer stateId) {

        List<CityEntity> cityEntityList = cityRepo.findByStateStateId(stateId);

        return cityEntityList.stream()
                .map(city -> mapper.map(city, CityDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isEmailUnique(String emailId) {
        /*UserEntity userEntity = userRepo.findByEmail(emailId);
        return userEntity != null;*/
        return  !userRepo.existsByEmail(emailId);
    }

    @Override
    public boolean register(UserDto userDto) {
        CountryEntity  country =  countryRepo.findById(userDto.getCountryId()).orElseThrow();
        StateEntity state =  stateRepo.findById(userDto.getStateId()).orElseThrow();
        CityEntity city = cityRepo.findById(userDto.getCityId()).orElseThrow();

        /*UserEntity userEntity =  new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);*/

        //copy data/properties from DTO obj to Entity Obj
        UserEntity userEntity = mapper.map(userDto, UserEntity.class);

        userEntity.setCountry(country);
        userEntity.setState(state);
        userEntity.setCity(city);

        userEntity.setPwd(generateRandomPwd(8));//pwd length 8
        userEntity.setPassUpdated("No");

        UserEntity saveUserEntity = userRepo.save(userEntity);

        if (saveUserEntity.getUserId() != null) {
            String subject = "Pradip Chavan - Your Account Created";
            String body = "<h2> Your Temporary password is :"+userEntity.getPwd();
            return emailService.sendEmail(subject, body, userDto.getEmail());
        }

        return false;
    }

    @Override
    public UserDto login(String email, String pwd) {
        UserEntity userEntity = userRepo.findByEmailAndPwd(email, pwd);
        if (userEntity != null) {
            /*UserDto userDto = new UserDto();
            BeanUtils.copyProperties(userEntity, userDto);
            return userDto;*/

            //copy properties from entity to dto object
            return  mapper.map(userEntity, UserDto.class);
        }
        return null;

    }

    @Override
    public boolean resetPwd(ResetPwdDto resetPwdDto) {
        UserEntity userEntity = userRepo.findByEmail(resetPwdDto.getEmail());

        if (userEntity != null) {
            userEntity.setPwd(resetPwdDto.getNewPassword());
            userEntity.setPassUpdated("Yes");
            userRepo.save(userEntity);//update user record with new pwd and pwdUpdate flag

            return true;
        }
        return false;
    }

    @Override
    public QuoteApiResponseDto getQuote() {
        String APIUrl = "https://dummyjson.com/quotes/random";

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(APIUrl, QuoteApiResponseDto.class).getBody();
    }

    private String generateRandomPwd(int pwdLenth) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        String str = "ABCDEFGHIJKLMNPRSTUVWXYZ123456789";
        for (int i = 0; i < pwdLenth; i++) {
            int randomIndex = random.nextInt(str.length());
            char c = str.charAt(randomIndex);

            stringBuilder.append(c);
        }

        return stringBuilder.toString();
    }
}
