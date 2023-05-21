package com.example.fw.common.dynamodb.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

/**
 * DynamoDB 本番用の設定クラス
 *
 */
@Configuration
@EnableConfigurationProperties(DynamoDBConfigurationProperties.class)
public class DynamoDBProdConfig {

    /**
     * DynamoDB Localに接続するDynamoDBClient
     */
    @Bean
    public DynamoDbClient dynamoDbClient(DynamoDBConfigurationProperties dynamoDBConfigurationProperties) {
        Region region = Region.of(dynamoDBConfigurationProperties.getRegion());
        return DynamoDbClient.builder()//
                .region(region)//                
                .build();
    }

    //TODO: DynamoDbAsyncClient、DynamoDbEnhancedAsyncClientを使った非同期プログラミングも検討する    
    // https://github.com/aws-samples/aws-lambda-java-workshop/blob/main/labs/unicorn-stock-broker/src/main/java/com/unicorn/broker/data/TransactionRepository.java  
    // https://docs.aws.amazon.com/ja_jp/sdk-for-java/latest/developer-guide/ddb-en-client-getting-started-dynamodbTable.html

}