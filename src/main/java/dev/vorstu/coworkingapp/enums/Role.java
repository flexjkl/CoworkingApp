package dev.vorstu.coworkingapp.enums;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {
    ADMIN("ADMIN"),
    CLIENT("CLIENT"),
    OWNER("OWNER");

    private final String value;

    @Override
    public String getAuthority() {
        return value;
    }
}
