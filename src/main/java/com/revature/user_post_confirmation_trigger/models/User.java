package com.revature.user_post_confirmation_trigger.models;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@DynamoDbBean
public class User {

    private String id;
    private String username;
    private String profilePicture;
    private int points;
    private int wins;
    private int losses;
    private String registrationDate;
    private List<String> gameRecords = new ArrayList<>();
    private List<UserSetDoc> createdSets = new ArrayList<>();
    private List<UserSetDoc> favoriteSets = new ArrayList<>();

    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }

    // Instantiate default values for a new user.
    public User(String id, String username){
        this.id = id;
        this.username = username;
        this.profilePicture = "";
        this.points = 0;
        this.wins = 0;
        this.losses = 0;
        this.registrationDate = LocalDateTime.now().toString();
        this.gameRecords = new ArrayList<>();
        this.createdSets = new ArrayList<>();
        this.favoriteSets = new ArrayList<>();
    }

    public User(){
        super();
    }
}

