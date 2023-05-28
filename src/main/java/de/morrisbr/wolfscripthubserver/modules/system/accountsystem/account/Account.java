package de.morrisbr.wolfscripthubserver.modules.system.accountsystem.account;

import de.morrisbr.wolfscripthubserver.modules.system.accountsystem.account.perms.Permission;
import de.morrisbr.wolfscripthubserver.modules.system.accountsystem.account.perms.Role;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.UUID;

public class Account {

    public String username;
    public Long id;
    public String password; //add HWID, every device can only 1x register

    private ArrayList<Permission> permissions;
    private ArrayList<Role> roles;
    //private PermController permController;

    //public String salt;
    public OnlineState onlineState = OnlineState.OFFLINE;
    //public HashMap<UUID, Account> friends;


    public Account() {
        permissions = new ArrayList<>();
        roles = new ArrayList<>();
        //friends = new HashMap<>();
        //permController = new PermController(this);
    }



    public boolean hasPerm(Permission perm) { //or with contain
        for (Permission permission : this.getPermissions()) {
            if(permission == perm) return true;
        }
        for (Role role : this.getRoles()) {
            for (Permission permission : role.getPermissions()) {
                if(permission == perm) return true;
            }
        }
        return false;
    }


    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        //salt = BCrypt.gensalt();
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    //public HashMap<UUID, Account> getFriendsMap() {
    //    return friends;
    //}
    //public ArrayList<Account> getFriends() {
   //     return new ArrayList<>(getFriendsMap().values());
    //}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OnlineState getOnlineState() {
        return onlineState;
    }

    public void setOnlineState(OnlineState onlineState) {
        this.onlineState = onlineState;
    }

    //public String getSalt() {
    //    return salt;
    //}

    //public void setSalt(String salt) {
    //    this.salt = salt;
    //}


    public ArrayList<Role> getRoles() {
        return roles;
    }

    public ArrayList<Permission> getPermissions() {
        return permissions;
    }

    //public PermController getPermController() {
    //    return permController;
    //}
}
