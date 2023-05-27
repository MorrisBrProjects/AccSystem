package de.morrisbr.wolfscripthubserver.modules.system.accountsystem.account.perms;

import java.util.ArrayList;

public class Role {
    private RoleType type;
    private ArrayList<Permission> permissions;

    public Role() {}
    public Role(RoleType type) {
        this.type = type;
    }
    public Role(RoleType type, ArrayList<Permission> permissions) {
        this.type = type;
        this.permissions = permissions;
    }

    public ArrayList<Permission> getPermissions() {
        return permissions;
    }
}
