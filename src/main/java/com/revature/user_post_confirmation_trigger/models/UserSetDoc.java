package com.revature.user_post_confirmation_trigger.models;

import com.revature.user_post_confirmation_trigger.models.Tag;
import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

import java.util.List;

//This class represents the fields of a Set that the User Document cares about storing (All but Cards)
@Data
@DynamoDbBean
public class UserSetDoc {

    private String id;
    private String set_name;
    private List<Tag> tags;
    private String author;
    private boolean is_public;
    private int views;
    private int plays;
    private int studies;
    private int favorites;


}