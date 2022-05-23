package com.example.demoawssdk2;

import com.example.demoawssdk2.source.AwsRdsResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoAwsSdk2Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoAwsSdk2Application.class, args);
	}

	@Autowired
	private AwsRdsResources awsRdsResources;

	@Override
	public void run(String... args) throws Exception {
		awsRdsResources.getAllRdsinstancesFromAllRegions();
	}
}
