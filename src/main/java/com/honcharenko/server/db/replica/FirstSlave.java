package com.honcharenko.server.db.replica;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.config.*;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

import java.util.Scanner;

import static com.honcharenko.server.db.replica.StarterUtil.bindIp;
import static com.honcharenko.server.db.replica.StarterUtil.port;


public class FirstSlave {
    public static void main(String[] args) throws Exception {

        IMongodConfig second = new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .replication(new Storage("./db2/", "test", 5))
                .cmdOptions(new MongoCmdOptionsBuilder().useNoJournal(false).build())
                .net(new Net(bindIp, port + 1, Network.localhostIsIPv6()))
                .build();

        MongodExecutable secondExecute = null;
        secondExecute = StarterUtil.getStarter().prepare(second);
        secondExecute.start();

        new Scanner(System.in).nextLine();
    }
}
