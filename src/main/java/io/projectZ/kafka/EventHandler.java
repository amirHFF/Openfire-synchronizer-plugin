package io.projectZ.kafka;
/*
    Project : openfire-Sync-provider
    Author  : a.FouladiFar
    Created : 13/07/2026
*/

import io.projectZ.dto.UserEvent;
import io.projectZ.provider.OpenFireAdminService;

public class EventHandler {
    private final OpenFireAdminService openFireAdminService ;

    public EventHandler(OpenFireAdminService openFireAdminService) {
        this.openFireAdminService = openFireAdminService;
    }

    public void handle(UserEvent userEvent){

        switch (userEvent.getEventType()){
            case USER_CREATED -> openFireAdminService.createUser(userEvent);
            case USER_DELETED -> openFireAdminService.deleteUser(userEvent);
        }
    }

}
