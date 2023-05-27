package de.morrisbr.wolfscripthubserver.modules.system.accountsystem.account.perms;

import io.javalin.core.security.RouteRole;

public enum RoleType implements RouteRole {

    OWNER, ADMIN, MODERATOR
//why login? -> FriendSystem, script save, and combatible with Ferest Android!
    //script post, setting autostart scripts, join anonyme users
    //chat with other wolfscript users
    //see other users


    //without:
    //See Wolfscript users
    // use scripthub
    // NOT: Chat
    // NOT Friends
}
