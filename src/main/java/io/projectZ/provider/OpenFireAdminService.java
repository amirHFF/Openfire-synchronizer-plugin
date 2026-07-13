package io.projectZ.provider;

import io.projectZ.dto.UserEvent;

/*
    Project : openfire-Sync-provider
    Author  : a.FouladiFar
    Created : 13/07/2026
*/
public interface OpenFireAdminService {

    void createUser(UserEvent userEvent);

    void deleteUser(UserEvent userEvent);

    void changeEmail(UserEvent userEvent);
}
