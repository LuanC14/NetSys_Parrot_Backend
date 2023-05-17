package com.SocialNetSys.NetSys.Providers;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
    public class AwsConfig {
    private String ACESS_KEY = "mykey";
    private String SECRET_KEY = "mykey";
    private  String ENDPOINT_NAME = "http://s3.localhost.localstack.cloud:4566";

    @Bean
    public AmazonS3 AmazonS3() {
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(ACESS_KEY, SECRET_KEY)))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(ENDPOINT_NAME, Regions.US_WEST_2.getName()))
                .build();
    }
}
