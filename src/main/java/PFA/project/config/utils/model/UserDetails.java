package PFA.project.config.utils.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDetails {
    private Long id;
    private String username;
    private String role;
}
