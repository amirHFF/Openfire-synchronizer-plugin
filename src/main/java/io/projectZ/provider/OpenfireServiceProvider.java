package io.projectZ.provider;
/*
  Project : Openfire-Synchonorizer-provdier
  Author  : AmirHFF
  Created : 7/12/2026 - 11:20 PM
*/

import io.projectZ.dto.UserEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jivesoftware.openfire.XMPPServer;
import org.jivesoftware.openfire.user.User;
import org.jivesoftware.openfire.user.UserManager;
import org.jivesoftware.openfire.user.UserNotFoundException;

public class OpenfireServiceProvider implements OpenFireAdminService{
    private final Logger logger = LogManager.getLogger(OpenfireServiceProvider.class);
    private final String jidPostFix="@Zchat.ir";
//    private UserManager manager =XMPPServer.getInstance().getUserManager();
    private UserManager manager = null;

    public void createUser(UserEvent event) {
        logger.info("creating user in openfire ..");
        if (event.getUserInfo() == null){
            throw new IllegalArgumentException("user info is empty inside user event");
        }
        try {
            String fullName = (event.getDetails().get("first_name") !=null &&
                    event.getDetails().get("last_name") !=null) ? event.getDetails().get("firstname").concat(" ")
                    .concat(event.getDetails().get("lastname")) : event.getUserInfo().getUsername();
            manager.createUser(
                    event.getUserInfo().getUsername(),
                    "xxx",
                    event.getUserInfo().getUsername(),
                    event.getUserInfo().getEmail()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        logger.info("user created successfully");
    }

    public void deleteUser(UserEvent userEvent){
        logger.info("removing user in openfire ..");

        if (userEvent.getUserInfo() == null){
            throw new IllegalArgumentException("user info is empty inside user event");
        }
        try {
            manager.deleteUser(manager.getUser(userEvent.getUserInfo().getUsername()));
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
        logger.info("user removed");

    }


    @Override
    public void changeEmail(UserEvent userEvent) {
        User user = getUser(userEvent);
        if (user!=null){
            user.setEmail(user.getEmail());
        }
    }

    private User getUser(UserEvent userEvent){
        User user = null;
        try {
            user = UserManager.getInstance().getUser(userEvent.getUserInfo().getUsername());
        } catch (UserNotFoundException e) {
            logger.error("get user throw exception : " , e);
            return null;
        }
        return user;
    }


}
