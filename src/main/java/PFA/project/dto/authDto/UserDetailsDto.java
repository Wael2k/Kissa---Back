package PFA.project.dto.authDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDto {
    private String email ;
    private String name;
    private boolean isAuthenticated;
    private UUID uuid;
    private Long id;
    private String role;

}
