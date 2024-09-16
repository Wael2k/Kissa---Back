package PFA.project.dto.authDto;

import PFA.project.enums.TypeRegisterEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;



@Builder
@Data
public class UserRegistrationDto {
    @NotEmpty(message = "fullName name should not be empty")
    @NotNull(message = "fullName name should not be null")
    private String fullName;



    @NotEmpty(message = "Email should not be empty")
    @NotNull(message = "Email should not be null")
    private String email;


    @NotEmpty(message = "Password should not be empty")
    @NotNull(message = "Password should not be null")
    private String password;



    @NotNull(message = "Type register should not be null")
    private TypeRegisterEnum typeRegister;
}
