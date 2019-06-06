package com.honcharenko.server.db;

import de.flapdoodle.embed.mongo.*;
import de.flapdoodle.embed.mongo.config.*;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

public class NoSqlReplication {

        public static void main(String[] args) throws Exception {
            MongodStarter starter = MongodStarter.getDefaultInstance();

            String bindIp = "127.0.0.1";
            int port = 27018;

            IMongodConfig mongodConfig = new MongodConfigBuilder()
                    .version(Version.Main.PRODUCTION)
                    .replication(new Storage("./db1/", "test", 5))
                    .cmdOptions(new MongoCmdOptionsBuilder().useNoJournal(false).build())
                    .net(new Net(bindIp, port, Network.localhostIsIPv6()))
                    .build();

            MongodExecutable mongodExecutableNonConf = null;
            mongodExecutableNonConf = starter.prepare(mongodConfig);
            mongodExecutableNonConf.start();

            IMongodConfig second = new MongodConfigBuilder()
                    .version(Version.Main.PRODUCTION)
                    .replication(new Storage("./db2/", "test", 5))
                    .cmdOptions(new MongoCmdOptionsBuilder().useNoJournal(false).build())
                    .net(new Net(bindIp, port + 1, Network.localhostIsIPv6()))
                    .build();

            MongodExecutable secondExecute = null;
            secondExecute = starter.prepare(second);
            secondExecute.start();


            IMongodConfig third = new MongodConfigBuilder()
                    .version(Version.Main.PRODUCTION)
                    .replication(new Storage("./db3/", "test", 5))
                    .cmdOptions(new MongoCmdOptionsBuilder().useNoJournal(false).build())
                    .net(new Net(bindIp, port + 2, Network.localhostIsIPv6()))
                    .build();

            MongodExecutable thirdExecute = null;
            thirdExecute = starter.prepare(third);
            thirdExecute.start();
            Thread.sleep(50000000);
        }
}
