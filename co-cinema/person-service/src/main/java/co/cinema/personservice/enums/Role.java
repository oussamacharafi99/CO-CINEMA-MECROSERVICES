package co.cinema.personservice.enums;


import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_MANAGER,
    ROLE_ADMIN_SYSTEM;
    @Override
    public String getAuthority() {
        return name();
    }
}