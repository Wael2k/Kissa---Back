package PFA.project.controller;
import PFA.project.dto.authDto.AuthenticationRequest;
import PFA.project.dto.authDto.AuthenticationResponse;
import PFA.project.dto.authDto.UserDetailsDto;
import PFA.project.dto.authDto.UserRegistrationDto;
import PFA.project.enums.UserValidityStatusEnum;
import PFA.project.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@RestController
@RequestMapping("api/auth/")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("v1/register")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody  UserRegistrationDto userRegistrationDto){

            return ResponseEntity.ok().body(authService.register(userRegistrationDto));
    }


    @PostMapping("v1/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok().body(authService.login(authenticationRequest));
    }
    @PostMapping("v1/verify-mail")
    public ResponseEntity<Void> verifyMail(@RequestParam("token") String token, @RequestParam("otp") String otp, HttpServletResponse response) {
        authService.verifyMail(otp, token, response);
        return ResponseEntity.ok().build();
    }
    @GetMapping("principal")
    public ResponseEntity<UserDetailsDto> user(Principal user) {
        return ResponseEntity.ok().body(authService.userDetails(user));
    }

    @GetMapping("verify-user-validity/{token}")
    public ResponseEntity<UserValidityStatusEnum> verifyUserValidity(@PathVariable String token) {
        return ResponseEntity.ok().body(authService.verifyUserValidity(token));
    }



}
