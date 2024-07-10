package com.example.config_server.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.google.gson.Gson;


@Configuration
public class SecretsManagerService 
{

    private Gson gson = new Gson();

    public static void getSecret() 
    {

        String secretName = "SECRET-spring.cloud.config.server.git.uri";
        String region = "us-east-2";
        //Region region = Region.of("us-east-2");

        // Create a Secrets Manager client
        AWSSecretsManager client = AWSSecretsManagerClientBuilder.standard()
                .withRegion(region)
                .build();

        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
                .withSecretId(secretName);

        GetSecretValueResult getSecretValueResult;
                                                                
        try 
        {
            getSecretValueResult = client.getSecretValue(getSecretValueRequest);
        } 
        catch (Exception e) 
        {
            throw e;
        }

        String secret = getSecretValueResult.getSecretString();

        return gson.fromJson(secret);

        // Your code goes here.
    }
}







