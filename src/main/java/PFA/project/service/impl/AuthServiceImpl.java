package PFA.project.service.impl;

import PFA.project.config.authority.UserRole;
import PFA.project.config.exceptions.BadRequestException;
import PFA.project.config.exceptions.DataNotFoundException;
import PFA.project.config.exceptions.utils.ErrorMessage;
import PFA.project.config.exceptions.utils.ExceptionCodes;
import PFA.project.config.mail.EmailService;
import PFA.project.config.security.config.JwtConfig;
import PFA.project.config.utils.JwtUtil;
import PFA.project.dao.AuthRepository;
import PFA.project.dao.entity.Student;
import PFA.project.dao.entity.Teacher;
import PFA.project.dao.entity.UserInfo;
import PFA.project.dto.authDto.AuthenticationRequest;
import PFA.project.dto.authDto.AuthenticationResponse;
import PFA.project.dto.authDto.UserDetailsDto;
import PFA.project.dto.authDto.UserRegistrationDto;
import PFA.project.enums.NotificationContextEnum;
import PFA.project.enums.TypeRegisterEnum;
import PFA.project.enums.UserValidityStatusEnum;
import PFA.project.event.createNotification.CreatingNotificationPublisher;
import PFA.project.event.createUser.CreatingUserPublisher;
import PFA.project.persistence.service.AbstractServiceImpl;
import PFA.project.service.AuthService;
import PFA.project.service.StudentService;
import PFA.project.service.TeacherService;
import PFA.project.utils.Constants;
import PFA.project.utils.ObjectsValidatorUtil;
import PFA.project.utils.Utils;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static PFA.project.enums.UserValidityStatusEnum.*;

@Service
@Slf4j
public class AuthServiceImpl extends AbstractServiceImpl<UserInfo, AuthRepository> implements AuthService {
    @Autowired
    private EmailService emailService;
    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private JwtConfig jwtConfig;
    @Autowired
    private ObjectsValidatorUtil objectsValidatorUtil;
    @Autowired
    private CreatingUserPublisher creatingUserPublisher;

    @Autowired
    private AuthRepository authRepository;
    @Autowired
    private  AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Autowired
    private CreatingNotificationPublisher creatingNotificationPublisher;



    public AuthServiceImpl(AuthRepository abstractRepository) {
        super(abstractRepository);
    }


    @Override
    public UserInfo createOrUpdateUser(UserInfo userInfo) {
        return ( super.createOrUpdate(userInfo));
    }


    @Override
    public void verifyMail(String otp, String token, HttpServletResponse response) {


    }



    private void verifyClient(UUID uuidClient, String otp) {

    }

    private void verifyMember(UUID uuidMember, String otp){


    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        List<String> errors  = objectsValidatorUtil.validate(authenticationRequest);
        if(!errors.isEmpty()){
            throw new BadRequestException(ExceptionCodes.ERROR_BAD_REQUEST.getValue(), ErrorMessage.prepareErrorMessage(ExceptionCodes.ERROR_BAD_REQUEST.getMessage()),errors);
        }
        UserInfo userInfo = getByMail(authenticationRequest.getEmail()).orElseThrow(() -> new DataNotFoundException(ExceptionCodes.ERROR_USER_NOT_FOUND.getValue(),ErrorMessage.prepareErrorMessage(ExceptionCodes.ERROR_USER_NOT_FOUND.getMessage())));

        if(!this.encoder.matches(authenticationRequest.getPassword(), userInfo.getPassword())){
           throw new BadRequestException(ExceptionCodes.ERROR_PASSWORD_IS_WRONG.getValue(),ErrorMessage.prepareErrorMessage(ExceptionCodes.ERROR_PASSWORD_IS_WRONG.getMessage()));
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userInfo.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        var jwtToken = JwtUtil.generateToken(userInfo,jwtConfig);
        log.trace("jwt token generated for user {}",userInfo.getId());
        return AuthenticationResponse.builder().accessToken(jwtToken).build();

    }

    @Override
    public Optional<UserInfo> getByMail(String email) {
           return authRepository.findByEmail(email);
    }

    @Override
    public AuthenticationResponse register(UserRegistrationDto userRegistrationDto) {
        List<String> errors  = objectsValidatorUtil.validate(userRegistrationDto);
        if(!errors.isEmpty()){
            throw new BadRequestException(ExceptionCodes.ERROR_BAD_REQUEST.getValue(), ErrorMessage.prepareErrorMessage(ExceptionCodes.ERROR_BAD_REQUEST.getMessage()),errors);
        }

        if(getByMail(userRegistrationDto.getEmail()).isPresent()){
            throw new BadRequestException(ExceptionCodes.ERROR_USER_ALREADY_EXIST.getValue(), ErrorMessage.prepareErrorMessage(ExceptionCodes.ERROR_USER_ALREADY_EXIST.getMessage()),errors);
        }
        if(TypeRegisterEnum.TEACHER.equals(userRegistrationDto.getTypeRegister())){
            Teacher teacher =createTeacher(userRegistrationDto);
            creatingUserPublisher.publish(userRegistrationDto.getEmail(),teacher.getUuid(),userRegistrationDto.getPassword(),TypeRegisterEnum.TEACHER);
            log.trace("Creating  teacher  with id  {}",teacher.getId());

        }else {
            Student student =createStudent(userRegistrationDto);
            log.trace("Creating  student  with id  {}",student.getId());
            creatingUserPublisher.publish(userRegistrationDto.getEmail(),student.getUuid(),userRegistrationDto.getPassword(),TypeRegisterEnum.STUDENT);
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userRegistrationDto.getEmail(),
                        userRegistrationDto.getPassword()
                )
        );
        UserInfo userInfo = getByMail(userRegistrationDto.getEmail()).orElseThrow(() -> new DataNotFoundException(ExceptionCodes.ERROR_USER_NOT_FOUND.getValue(),ErrorMessage.prepareErrorMessage(ExceptionCodes.ERROR_USER_NOT_FOUND.getMessage())));

        var jwtToken = JwtUtil.generateToken(userInfo,jwtConfig);
        log.trace("jwt token generated for user {}",userInfo.getId());
        return AuthenticationResponse.builder().accessToken(jwtToken).build();



    }

    private Teacher createTeacher(UserRegistrationDto userRegistrationDto) {
        Teacher teacher = new Teacher();
        teacher.setEmail(userRegistrationDto.getEmail());
        teacher.setFullName(userRegistrationDto.getFullName());
        return teacherService.createOrUpdateTeacher(teacher);
    }

    private Student createStudent(UserRegistrationDto userRegistrationDto) {
        Student student = new Student();
        student.setEmail(userRegistrationDto.getEmail());
        student.setFullName(userRegistrationDto.getFullName());
        return studentService.createOrUpdateStudent(student);
    }

    @Override
    public UserDetailsDto userDetails(Principal user) {

        return null;
    }

    @Override
    public UserValidityStatusEnum verifyUserValidity(String token) {
     return null;
    }





}
