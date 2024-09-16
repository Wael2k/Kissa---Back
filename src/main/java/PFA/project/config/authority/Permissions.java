package PFA.project.config.authority;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permissions {
    PROFESSOR_READ("professor:read");


    @Getter
    private final String permission;


}