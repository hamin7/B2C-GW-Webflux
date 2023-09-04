package com.kt.navi.gw.period.impl;

import lombok.Data;

@Data
public class CommunicationRequestMessage {

    public String instanceId;
    public String transactionId;
    public MemberMessage memberMessage;

    public CommunicationRequestMessage() {}
    public CommunicationRequestMessage(String instanceId, String transactionId, MemberMessage memberMessage) {
        this.instanceId = instanceId;
        this.transactionId = transactionId;
        this.memberMessage = memberMessage;
    }
}
