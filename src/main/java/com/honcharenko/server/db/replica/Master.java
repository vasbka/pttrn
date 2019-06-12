package com.honcharenko.server.db.replica;

import de.flapdoodle.embed.mongo.MongoImportExecutable;
import de.flapdoodle.embed.mongo.MongoImportStarter;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.config.*;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;

import java.io.File;
import java.util.Scanner;

import static com.honcharenko.server.db.replica.StarterUtil.*;

public class Master {
    public static void main(String[] args) throws Exception {

        IMongoCmdOptions cmdOptions = new MongoCmdOptionsBuilder().verbose(false)
                .enableAuth(true).build();

        IMongodConfig mongodConfig = new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .replication(new Storage("./db1/", "test", 5))
                .cmdOptions(new MongoCmdOptionsBuilder().useNoJournal(false).verbose(false).enableAuth(false).build())
                .net(new Net(bindIp, port, Network.localhostIsIPv6()))
                .build();

        MongodExecutable mongodExecutableNonConf = null;
        mongodExecutableNonConf = StarterUtil.getStarter().prepare(mongodConfig);
        mongodExecutableNonConf.start();


        new Scanner(System.in).nextLine();
    }
}
