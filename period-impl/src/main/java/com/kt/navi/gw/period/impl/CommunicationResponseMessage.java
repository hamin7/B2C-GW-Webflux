package com.kt.navi.gw.period.impl;

import lombok.Data;

@Data
public class CommunicationResponseMessage {
    public GroupMessage groupMessage;

    public CommunicationResponseMessage(){}
    public CommunicationResponseMessage(GroupMessage groupMessage){
        this.groupMessage = groupMessage;
    }
}
