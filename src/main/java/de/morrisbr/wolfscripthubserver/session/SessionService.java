package de.morrisbr.wolfscripthubserver.session;

import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.HashMap;

public class SessionService {

    public HashMap<String, SessionData> sessionDataHashMap = new HashMap<>();

    public SessionData getSessionData(Context ctx) {
        System.out.println(ctx.req.getSession().getId());
        return sessionDataHashMap.get(ctx.req.getSession().getId());
    }

    public boolean isSessionDataExist(Context ctx) {
        return sessionDataHashMap.containsKey(ctx.req.getSession().getId());
    }

    public ArrayList<SessionData> removeOldSessions() {
        ArrayList<SessionData> removed = new ArrayList<>();
        for (SessionData sessionData : sessionDataHashMap.values()) {
            removed.add(sessionData);
            sessionDataHashMap.remove(sessionData.getSessionToken());
        }
        return removed;
    }

    public HashMap<String, SessionData> getSessionDataHashMap() {
        return sessionDataHashMap;
    }
}
