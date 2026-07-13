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
    private UserManager manager =XMPPServer.getInstance().getUserManager();

    public void createUser(UserEvent event) {
        try {
            manager.createUser(
                    event.getUsername(),
                    event.getPassword(),
                    event.getFirstName()+" "+event.getLastName(),
                    event.getEmail()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(UserEvent userEvent){
        try {
            manager.deleteUser(manager.getUser(userEvent.getUsername()));
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
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
            user = UserManager.getInstance().getUser(userEvent.getUsername().concat(jidPostFix));
        } catch (UserNotFoundException e) {
            logger.error("get user throw exception : " , e);
            return null;
        }
        return user;
    }


}
