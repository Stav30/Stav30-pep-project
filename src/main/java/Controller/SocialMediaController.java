package Controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/login", this:: postAccountHandler );
        app.post("/messages", this::postMessageHandler );
        //app.get("example-endpoint", this::exampleHandler);
        app.start(8080);

        return app;
    }
    /*
     * User Registration
As a user, I should be able to create a new Account on the endpoint POST localhost:8080/register. 
The body will contain a representation of a JSON Account, but will not contain an account_id.

The registration will be successful iff the username is not blank, the password is at least 4 characters long, 
and an Account with that username does not already exist. If all these conditions are met, the response body should 
contain a JSON of the Account, including its account_id. The response status should be 200 OK, which is the default. 
The new account should be persisted(added) to the database.

If the registration is not successful, the response status should be 400. (Client error) 
     */   
    private void postAccountHandler(Context ctx) {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account addedAccount = AccountService.addAccount(account);
        if(addedAccount!=null){ // ie account successfully added
            ctx.json(mapper.writeValueAsString(addedAccount));
        } else { ctx.status(400);}
    }
        //ctx.json("sample text");
       
    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */

/*
 * ----------------
Create New Message
As a user, I should be able to submit a new post on the endpoint POST localhost:8080/messages. The request body will 
contain a JSON representation of a message, which should be persisted to the database, but will not contain a 
message_id.

The creation of the message will be successful iff the message_text is not blank, is under 255 characters, 
and posted_by refers to a real, existing user. If successful, the response body should contain a JSON of the message, 
including its message_id. The response status should be 200, which is the default. The new message should be persisted
to the database.

If the creation of the message is not successful, the response status should be 400. (Client error)

--------------
 */

    private void postMessageHandler(Context ctx) {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        Message addedMessage = MessageService.addMessage(message);
        if(addedMessage != null && addedMessage.length() <= 255 ){
           ctx.json(mapper.writeValueAsString(addedMessage)); 
           ctx.status(200);
        } else {
            ctx.status(400);
        }

        //message.posted_by
    }

}
/* 
Below are the users stories that must be completed:




*/