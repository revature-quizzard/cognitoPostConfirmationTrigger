package com.revature.user_post_confirmation_trigger;

import com.revature.user_post_confirmation_trigger.models.User;
import lombok.SneakyThrows;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.http.apache.ApacheHttpClient;


public class UserRepository {

    private static final UserRepository userRepository = new UserRepository();
    private final DynamoDbTable<User> userTable;

    public UserRepository() {
        DynamoDbClient db = DynamoDbClient.builder().httpClient(ApacheHttpClient.create()).build();
        DynamoDbEnhancedClient dbClient = DynamoDbEnhancedClient.builder().dynamoDbClient(db).build();
        userTable = dbClient.table("Users", TableSchema.fromBean(User.class));
    }

    public static UserRepository getInstance() {
        return userRepository;
    }

    @SneakyThrows
    public User saveUser(User user) {
        userTable.putItem(user);
        return user;
    }
}