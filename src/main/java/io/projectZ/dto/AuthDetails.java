package io.projectZ.dto;
/*
  Project : Openfire-Synchonorizer-provdier
  Author  : AmirHFF
  Created : 7/14/2026 - 12:43 PM
*/

public class AuthDetails {
    private String realmId;
    private String realmName;

    private String clientId;

    private String userId;

    private String ipAddress;

    public String getRealmId() {
        return realmId;
    }

    public void setRealmId(String realmId) {
        this.realmId = realmId;
    }

    public String getRealmName() {
        return realmName;
    }

    public void setRealmName(String realmName) {
        this.realmName = realmName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}

