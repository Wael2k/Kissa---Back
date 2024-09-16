package PFA.project.service;
import PFA.project.dao.entity.UserInfo;
import PFA.project.dto.authDto.AuthenticationRequest;
import PFA.project.dto.authDto.AuthenticationResponse;
import PFA.project.dto.authDto.UserDetailsDto;
import PFA.project.dto.authDto.UserRegistrationDto;
import PFA.project.enums.UserValidityStatusEnum;
import jakarta.servlet.http.HttpServletResponse;

import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

public interface AuthService {
    UserInfo createOrUpdateUser(UserInfo userInfo);

    void verifyMail(String otp, String token, HttpServletResponse response);

    AuthenticationResponse login(AuthenticationRequest authenticationRequest);

    Optional<UserInfo> getByMail(String email);

    AuthenticationResponse register(UserRegistrationDto userRegistrationDto);

    UserDetailsDto userDetails(Principal user);

    UserValidityStatusEnum verifyUserValidity(String token);
}
