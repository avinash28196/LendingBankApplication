package com.lendingclub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootApplication
public class LendingClubAppApplication implements CommandLineRunner{

	@Autowired
	DataSource dataSource;


	public static void main(String[] args) throws Exception {
		SpringApplication.run(LendingClubAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("DATASOURCE = " + dataSource);
		System.out.println("Hello");
		try (Connection connection = dataSource.getConnection()) {
			System.out.println("catalog:" + connection.getCatalog());
			System.out.println(dataSource.getConnection());
		}

	}


}

