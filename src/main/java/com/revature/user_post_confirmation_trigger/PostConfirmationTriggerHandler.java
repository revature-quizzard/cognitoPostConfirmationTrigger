package com.revature.user_post_confirmation_trigger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.CognitoUserPoolPostConfirmationEvent;
import com.revature.user_post_confirmation_trigger.models.User;

import java.util.Map;

public class PostConfirmationTriggerHandler implements RequestHandler<CognitoUserPoolPostConfirmationEvent, CognitoUserPoolPostConfirmationEvent> {

    private final UserRepository userRepo;

    public PostConfirmationTriggerHandler(){
        this.userRepo = UserRepository.getInstance();
    }

    public PostConfirmationTriggerHandler(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    /**
     * This function is used to persist user data to DynamoDB after a user confirms their account information,
     *  bypassing the need to call our own post user Lambda.
     *
     * TODO: Containerize this later (in a future sprint) so it spins up faster when deployed.
     *
     * @param cuppe - Obviously.
     *              As descriptive as AWS.
     * @author Cody McDonald
     */

    @Override
    public CognitoUserPoolPostConfirmationEvent handleRequest(CognitoUserPoolPostConfirmationEvent cuppe, Context context) {

        LambdaLogger logger = context.getLogger();
        logger.log("RECEIVED EVENT: " + cuppe);

        Map<String, String> userAttributes = cuppe.getRequest().getUserAttributes();
        logger.log("ATTRIBUTES RECIEVED: " + userAttributes);

        String username = cuppe.getUserName();
        String uId = userAttributes.get("sub");

        logger.log("Creating new user and attempting to save...");
        User newUser = new User(uId, username);

        userRepo.saveUser(newUser);

        return cuppe;
    }

}
