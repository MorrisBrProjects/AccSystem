package de.morrisbr.wolfscripthubserver.session;

import de.morrisbr.wolfscripthubserver.modules.system.accountsystem.account.Account;
import de.morrisbr.wolfscripthubserver.webserver.RouteRole;

import java.util.Calendar;

public class SessionData {

    private String sessionToken;
    private Account account;
    private RouteRole role = RouteRole.NOT_LOGIN;
    private Calendar lastInteractionDate;

    public Calendar getLastInteractionDate() {
        return lastInteractionDate;
    }

    public void setLastInteractionDate(Calendar lastInteractionDate) {
        this.lastInteractionDate = lastInteractionDate;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public RouteRole getRole() {
        return role;
    }

    public void setRole(RouteRole role) {
        this.role = role;
    }

}
