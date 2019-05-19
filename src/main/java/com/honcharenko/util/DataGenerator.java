package com.honcharenko.util;

import com.google.gson.Gson;
import com.honcharenko.builder.entity.EnrolleeBuilder;
import com.honcharenko.dao.DAO;
import com.honcharenko.entity.Enrollee;
import com.honcharenko.entity.Property;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DataGenerator {

    private static List<String> names;

    public static void main(String[] args) throws Exception{
        names = FileUtils.readLines(new File("./firstNames.txt"), "utf-8");

        List<Enrollee> list = new ArrayList<>();

        List<String> emails = new ArrayList<>();
        List<String> logins = new ArrayList<>();
            for (int i = 0; i < 500001; i++) {
            System.out.println(i);
            String email;
            do {
                email = RandomStringUtils.randomAlphanumeric(new Random().nextInt(380) + 4).toUpperCase();
            } while (emails.contains(email));
            emails.add(email);

            String login;
            do {
                login = RandomStringUtils.randomAlphanumeric(new Random().nextInt(380) + 4).toUpperCase();
            } while (logins.contains(login));
            logins.add(login);

            Enrollee build = new EnrolleeBuilder()
                    .setFirstName(names.get(new Random().nextInt(998 + 1)))
                    .setEmail(email)
                    .setLastName(RandomStringUtils.randomAlphanumeric(new Random().nextInt(30) + 4).toUpperCase())
                    .setLogin(login)
                    .setPassword(RandomStringUtils.randomAlphanumeric(new Random().nextInt(30) + 4).toUpperCase())
                    .build();
            list.add(build);
        }

        DAO mysql = new DaoManager(DaoType.MYSQL).getFactory().getDaoByEntityType(Enrollee.class);
        DAO nosql = new DaoManager(DaoType.NOSQL).getFactory().getDaoByEntityType(Enrollee.class);

        List<Integer> count = Arrays.asList(100, 1000, 10000, 50000, 100000, 500000);
        count.forEach(integer -> {
            try {
                mysql.clearAll();
            }catch (SQLException e){}
            try{
                nosql.clearAll();
            }catch (SQLException e){}

            System.out.println("For count : " + integer);
            System.out.println("    START INSERTED");
            System.out.print("        MYSQL : ");
            long start = System.currentTimeMillis();
            for (int i = 0; i < integer; i++) {
                try {
                    mysql.add(list.get(i));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            System.out.println(System.currentTimeMillis() - start );

            System.out.print("        NoSQL : ");
            start = System.currentTimeMillis();
            for (int i = 0; i < integer; i++) {
                try {
                    nosql.add(list.get(i));
                }catch (Exception e){}
            }
            System.out.println(System.currentTimeMillis() - start);
            System.out.println("    END INSERTED");

            String nameForSelect = names.get(new Random().nextInt(998 + 1));
            System.out.println("    START SELECT BY NAME : " + nameForSelect);
            System.out.print("        MySql : ");
            start = System.currentTimeMillis();
            try {
                mysql.getByProperty(Arrays.asList(new Property("enrolleeFirstName", nameForSelect)));
            }catch (SQLException e){}
            System.out.println(System.currentTimeMillis() - start);

            System.out.print("        Nosql : ");
            start = System.currentTimeMillis();
            try {
                nosql.getByProperty(Arrays.asList(new Property("enrolleeFirstName", nameForSelect)));
            }catch (SQLException e){}
            System.out.println(System.currentTimeMillis() - start);

            System.out.println("END TEST FOR : " + integer);
            System.out.println("-------------------------------------------------------");
        });

    }

}
