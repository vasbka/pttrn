package com.honcharenko.server.db.replica;

import de.flapdoodle.embed.mongo.MongodStarter;

public class StarterUtil {
    private static MongodStarter starter = MongodStarter.getDefaultInstance();
    public static String bindIp = "127.0.0.1";
    public static int port = 27018;
    public static MongodStarter getStarter() {
        return starter;
    }
}
