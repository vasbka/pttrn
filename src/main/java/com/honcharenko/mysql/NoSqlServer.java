package com.honcharenko.mysql;

import de.flapdoodle.embed.mongo.*;
import de.flapdoodle.embed.mongo.config.*;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.apache.log4j.BasicConfigurator;

import java.io.File;

public class NoSqlServer {
    public static void main(String[] args) throws Exception {
        MongodStarter starter = MongodStarter.getDefaultInstance();

        String bindIp = "127.0.0.1";
        int port = 27018;

        IMongodConfig mongodConfig = new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(bindIp, port, Network.localhostIsIPv6()))
                .build();

        MongodExecutable mongodExecutableNonConf = null;
        mongodExecutableNonConf = starter.prepare(mongodConfig);
        mongodExecutableNonConf.start();

        BasicConfigurator.configure();
        IMongoImportConfig mongodConfigEnrollee = new MongoImportConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(bindIp, port, Network.localhostIsIPv6()))
                .db("test")
                .collection("enrollee")
                .upsert(true)
                .dropCollection(true)
                .jsonArray(true)
                .importFile(new File("./enrollee.json").getAbsolutePath())
                .build();

        IMongoImportConfig mongodConfigSubject = new MongoImportConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(bindIp, port, Network.localhostIsIPv6()))
                .db("test")
                .collection("subject")
                .upsert(true)
                .dropCollection(true)
                .jsonArray(true)
                .importFile(new File("./subject.json").getAbsolutePath())
                .build();

        IMongoImportConfig mongodConfigFaculty = new MongoImportConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(bindIp, port, Network.localhostIsIPv6()))
                .db("test")
                .collection("faculty")
                .upsert(true)
                .dropCollection(true)
                .jsonArray(true)
                .importFile(new File("./faculty.json").getAbsolutePath())
                .build();

        IMongoImportConfig mongodConfigStatement = new MongoImportConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(bindIp, port, Network.localhostIsIPv6()))
                .db("test")
                .collection("statement")
                .upsert(true)
                .dropCollection(true)
                .jsonArray(true)
                .importFile(new File("./statement.json").getAbsolutePath())
                .build();

        MongoImportExecutable mongodExecutable = null;
        MongoImportStarter defaultInstance = MongoImportStarter.getDefaultInstance();
        mongodExecutable = defaultInstance.prepare(mongodConfigEnrollee);
        mongodExecutable.start();
        mongodExecutable = defaultInstance.prepare(mongodConfigSubject);
        mongodExecutable.start();
        mongodExecutable = defaultInstance.prepare(mongodConfigFaculty);
        mongodExecutable.start();
        mongodExecutable = defaultInstance.prepare(mongodConfigStatement);

        mongodExecutable.start();
        Thread.sleep(50000000);

    }
}
