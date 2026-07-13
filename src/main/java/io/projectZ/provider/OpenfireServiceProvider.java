package io.projectZ.provider;
/*
  Project : Openfire-Synchonorizer-provdier
  Author  : AmirHFF
  Created : 7/12/2026 - 11:20 PM
*/

import io.projectZ.dto.UserEvent;
import org.jivesoftware.openfire.XMPPServer;
import org.jivesoftware.openfire.user.UserManager;
import org.jivesoftware.openfire.user.UserNotFoundException;

public class OpenfireServiceProvider {
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

    public void updateUser(UserEvent userEvent){
        throw new UnsupportedOperationException("we do not support update user yet");
    }


}
