package ru.elena.testwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("application")
@SpringBootApplication
public class Main {

    /**
     * postgres=# CREATE DATADASE testdb;
     * #UPDATE DATABASECHANGELOGLOCK SET LOCKED=FALSE, LOCKGRANTED=null, LOCKEDBY=null where ID=1
     */
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class);
    }

}
