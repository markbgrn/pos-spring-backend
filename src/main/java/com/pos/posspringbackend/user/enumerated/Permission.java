package com.pos.posspringbackend.user.enumerated;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("admin:read"),
    ADMIN_CREATE("admin:create"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_DELETE("admin:delete"),
    INVENTORY_CLERK_READ("inventory_clerk:read"),
    INVENTORY_CLERK_CREATE("inventory_clerk:create"),
    INVENTORY_CLERK_UPDATE("inventory_clerk:update"),
    CASHIER_READ("cashier:read"),
    CASHIER_CREATE("cashier:create"),
    CASHIER_UPDATE("cashier:update");

    @Getter
    private final String permission;
}
