package org.whispersystems.textsecuregcm.util;

import org.whispersystems.textsecuregcm.configuration.DynamoDbConfiguration;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClientBuilder;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

public class DynamoDbFromConfig {

  private static ClientOverrideConfiguration clientOverrideConfiguration(DynamoDbConfiguration config) {
    return ClientOverrideConfiguration.builder()
        .apiCallTimeout(config.getClientExecutionTimeout())
        .apiCallAttemptTimeout(config.getClientRequestTimeout())
        .build();
  }

  public static DynamoDbClient client(DynamoDbConfiguration config, AwsCredentialsProvider credentialsProvider) {
    return DynamoDbClient.builder()
        .region(Region.of(config.getRegion()))
        .credentialsProvider(credentialsProvider)
        .overrideConfiguration(clientOverrideConfiguration(config))
        .build();
  }

  public static DynamoDbAsyncClient asyncClient(
      DynamoDbConfiguration config, AwsCredentialsProvider credentialsProvider) {
    DynamoDbAsyncClientBuilder builder = DynamoDbAsyncClient.builder()
        .region(Region.of(config.getRegion()))
        .credentialsProvider(credentialsProvider)
        .overrideConfiguration(clientOverrideConfiguration(config));
    return builder.build();
  }
}
