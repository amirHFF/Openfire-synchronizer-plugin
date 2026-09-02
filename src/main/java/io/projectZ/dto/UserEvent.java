package io.projectZ.dto;
/*
  Project : Openfire-Synchonorizer-provdier
  Author  : AmirHFF
  Created : 7/12/2026 - 11:24 PM
*/

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.Map;

@JsonInclude(value = JsonInclude.Include.NON_ABSENT)
public class UserEvent {

    private String id;
    private long occurredAt;
    private String realmId;
    private Map<String , String> details;

    private UserInfo userInfo;
    private boolean userEvent;
    private String userid;
    private UserEventType eventType;
    private String clientId;

    private String resourceType;
    private String resourceId;
    private String operationType;
    private AuthDetails authDetails;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getOccurredAt() {
        return occurredAt;
    }

    public void setOccurredAt(long occurredAt) {
        this.occurredAt = occurredAt;
    }

    public String getRealmId() {
        return realmId;
    }

    public void setRealmId(String realmId) {
        this.realmId = realmId;
    }

    public Map<String, String> getDetails() {
        return details;
    }

    public void setDetails(Map<String, String> details) {
        this.details = details;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public UserEventType getEventType() {
        return eventType;
    }

    public void setEventType(UserEventType eventType) {
        this.eventType = eventType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public AuthDetails getAuthDetails() {
        return authDetails;
    }

    public void setAuthDetails(AuthDetails authDetails) {
        this.authDetails = authDetails;
    }

    public boolean isUserEvent() {
        return userEvent;
    }

    public void setUserEvent(boolean userEvent) {
        this.userEvent = userEvent;
    }

    @Override
    public String toString() {
        return "UserEvent{" +
                "id='" + id + '\'' +
                ", occurredAt=" + occurredAt +
                ", realmId='" + realmId + '\'' +
                ", details=" + details +
                ", userInfo=" + userInfo +
                ", userid='" + userid + '\'' +
                ", eventType=" + eventType +
                ", clientId='" + clientId + '\'' +
                ", resourceType='" + resourceType + '\'' +
                ", resourceId='" + resourceId + '\'' +
                ", operationType='" + operationType + '\'' +
                ", authDetails=" + authDetails +
                '}';
    }
}

