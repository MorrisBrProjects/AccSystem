package de.morrisbr.wolfscripthubserver.database;

import de.morrisbr.wolfscripthubserver.config.WebConfig;

public class Connection {
    public String getConnectionString() {
        return WebConfig.getMongodb();
    }
}