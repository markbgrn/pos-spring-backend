package com.pos.posspringbackend.user.enumerated;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum Role {
    USER(Collections.emptyList()),
    ADMIN(
            Arrays.asList(
                    Permission.ADMIN_READ,
                    Permission.ADMIN_CREATE,
                    Permission.ADMIN_UPDATE,
                    Permission.ADMIN_DELETE
            )
    ),
    INVENTORY_CLERK(
            Arrays.asList(
                    Permission.INVENTORY_CLERK_READ,
                    Permission.INVENTORY_CLERK_CREATE,
                    Permission.INVENTORY_CLERK_UPDATE
            )
    ),
    CASHIER(
            Arrays.asList(
                    Permission.CASHIER_READ,
                    Permission.CASHIER_CREATE,
                    Permission.CASHIER_UPDATE
            )
    );
    @Getter
    private final List<Permission> permissions;
    public List<SimpleGrantedAuthority> getAuthorities() {
           var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
