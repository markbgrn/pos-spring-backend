package com.pos.posspringbackend.user.enumerated;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.pos.posspringbackend.user.enumerated.Permission.*;
import static com.pos.posspringbackend.user.enumerated.Permission.ADMIN_READ;

@RequiredArgsConstructor
public enum Role {
    USER(Collections.emptySet()),
    ADMIN(
            Set.of(
                    ADMIN_READ,
                    ADMIN_CREATE,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    INVENTORY_CLERK_READ,
                    INVENTORY_CLERK_CREATE,
                    INVENTORY_CLERK_UPDATE,
                    INVENTORY_CLERK_DELETE,
                    CASHIER_READ,
                    CASHIER_CREATE,
                    CASHIER_UPDATE,
                    CASHIER_DELETE
            )
    ),
    INVENTORY_CLERK(
            Set.of(
                    ADMIN_READ,
                    INVENTORY_CLERK_READ,
                    INVENTORY_CLERK_CREATE,
                    INVENTORY_CLERK_UPDATE
            )
    ),
    CASHIER(
            Set.of(
                    INVENTORY_CLERK_READ,
                    CASHIER_READ,
                    CASHIER_CREATE,
                    CASHIER_UPDATE
            )
    );
    @Getter
    private final Set<Permission> permissions;
    public List<SimpleGrantedAuthority> getAuthorities() {
           var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
