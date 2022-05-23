package com.example.demoawssdk2.source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rds.RdsClient;
import software.amazon.awssdk.services.rds.model.DBInstance;
import software.amazon.awssdk.services.rds.model.DescribeDbInstancesResponse;

import java.util.List;

@Controller
@RequestMapping("/rds")
public class AwsRdsResources {

    @Value("${aws.access.key.id}")
    private String accessKeyId;

    @Value("${aws.secret.access.key}")
    private String secretAccessKey;

    public static RdsClient rdsClient;

    @GetMapping("/getAllRdsinstances")
    public ResponseEntity<Object> getAllRdsinstancesFromAllRegions() {

        try {
            System.out.println("access key id : "+accessKeyId);
            System.out.println("secret access key : "+secretAccessKey);
            for (Region region : Region.regions()) {
                System.out.println("RDS Instances for region : "+region+" : ");
                rdsClient = RdsClient.builder().credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKeyId, secretAccessKey))).region(region).build();
                DescribeDbInstancesResponse rdsInstances = rdsClient.describeDBInstances();
                for (DBInstance dbInstance : rdsInstances.dbInstances()) {
                    System.out.println("db identifier : "+dbInstance.dbInstanceIdentifier());
                }
            }
        } catch (Exception e) {
            System.out.println("access key id : "+accessKeyId);
            System.out.println("secret access key : "+secretAccessKey);
            e.printStackTrace();
        }

        return null;
    }

}
