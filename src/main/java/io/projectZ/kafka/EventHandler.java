package io.projectZ.kafka;
/*
    Project : openfire-Sync-provider
    Author  : a.FouladiFar
    Created : 13/07/2026
*/

import io.projectZ.dto.UserEvent;
import io.projectZ.provider.OpenFireAdminService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EventHandler {
    Logger logger = LogManager.getLogger(EventHandler.class);
    private final OpenFireAdminService openFireAdminService;

    public EventHandler(OpenFireAdminService openFireAdminService) {
        this.openFireAdminService = openFireAdminService;
    }

    public void handle(UserEvent userEvent) {

        switch (userEvent.getEventType()) {
            case REGISTER -> openFireAdminService.createUser(userEvent);

            case DELETE_ACCOUNT -> openFireAdminService.deleteUser(userEvent);

            case UPDATE_EMAIL -> openFireAdminService.changeEmail(userEvent);

            default -> logger.info("no handler event found for type {} and id {} " ,userEvent.getEventType() , userEvent.getId());
        }
    }

}
