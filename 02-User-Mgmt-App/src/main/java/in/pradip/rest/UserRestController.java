package in.pradip.rest;

import in.pradip.dto.*;
import in.pradip.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserRestController {

    private UserService userService;

    @GetMapping("/countries")
    public ResponseEntity<ApiResponse<List<CountryDto>>> getCountries(){
        ApiResponse<List<CountryDto>> response = new ApiResponse<>();

        List<CountryDto> countries = userService.getCountries();

        if (countries.isEmpty()){
            response.setStatus(500);
            response.setMessage("Failed to fetch Cuntries");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            response.setStatus(200);
            response.setMessage("Countries Fetched Successfully");
            response.setData(countries);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping("/states/{countryId}")
    public ResponseEntity<ApiResponse<List<StateDto>>> getStates(@PathVariable Integer countryId){
        List<StateDto> states = userService.getStates(countryId);

        ApiResponse<List<StateDto>> response = new ApiResponse<>();

        if (states.isEmpty()){
            response.setStatus(500);
            response.setMessage("No states found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            response.setStatus(200);
            response.setMessage("States fetched Successfully");
            response.setData(states);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping("/cities/{stateId}")
    public ResponseEntity<ApiResponse<List<CityDto>>> getCities(@PathVariable Integer stateId){
        List<CityDto> cities = userService.getCities(stateId);

        ApiResponse<List<CityDto>> response = new ApiResponse<>();

        if (cities.isEmpty()){
            response.setStatus(500);
            response.setMessage("No cities found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            response.setStatus(200);
            response.setMessage("Cities fetched Successfully");
            response.setData(cities);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping("/unique/{emailId}")
    public ResponseEntity<ApiResponse<String>> checkUniqueEmailId(@PathVariable String emailId){

        boolean emailUnique = userService.isEmailUnique(emailId);

        ApiResponse<String> response = new ApiResponse<>();

        if (emailUnique){
            response.setStatus(200);
            response.setMessage("No Email id found");
            response.setData("UNIQUE");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus(200);
            response.setMessage("Duplicate Email id found");
            response.setData("DUPLICATE");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserDto>> login(@RequestBody UserDto userDto){

        UserDto user = userService.login(userDto.getEmail(), userDto.getPwd());

        ApiResponse<UserDto> response = new ApiResponse<>();

        if (user != null){
            response.setStatus(200);
            response.setMessage("Login success");
            response.setData(user);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus(401);
            response.setMessage("Invalid Credentials");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/user")
    public ResponseEntity<ApiResponse<String>> registerUser(@RequestBody UserDto userDto){

        boolean register = userService.register(userDto);

        ApiResponse<String> response = new ApiResponse<>();

        if (register){
            response.setStatus(200);
            response.setMessage("User registered successfully");
            response.setData("SUCCESS");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus(500);
            response.setMessage("User registration failed");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/reset-pwd")
    public ResponseEntity<ApiResponse<String>> resetPwd(@RequestBody ResetPwdDto resetPwdDto){

        boolean isResetPwd = userService.resetPwd(resetPwdDto);

        ApiResponse<String> response = new ApiResponse<>();

        if (isResetPwd){
            response.setStatus(200);
            response.setMessage("Password updated successfully");
            response.setData("SUCCESS");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus(500);
            response.setMessage("Record not found");
            response.setData("FAILED");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getQuote")
    public ResponseEntity<ApiResponse<QuoteApiResponseDto>> getQuote(){
        QuoteApiResponseDto quote = userService.getQuote();

        ApiResponse<QuoteApiResponseDto> response = new ApiResponse<>();

        if (quote != null){
            response.setStatus(200);
            response.setMessage("Quote fetched Successfully");
            response.setData(quote);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus(500);
            response.setMessage("Quote fetching failed");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
