package com.honcharenko.server.db.replica;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.config.*;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

import java.util.Scanner;

import static com.honcharenko.server.db.replica.StarterUtil.bindIp;
import static com.honcharenko.server.db.replica.StarterUtil.port;

public class SecondSlave {
    public static void main(String[] args) throws Exception {


        IMongodConfig third = new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .replication(new Storage("./db3/", "test", 5))
                .cmdOptions(new MongoCmdOptionsBuilder().useNoJournal(false).build())
                .net(new Net(bindIp, port + 2, Network.localhostIsIPv6()))
                .build();

        MongodExecutable thirdExecute = null;
        thirdExecute = StarterUtil.getStarter().prepare(third);
        thirdExecute.start();

        new Scanner(System.in).nextLine();
    }
}
