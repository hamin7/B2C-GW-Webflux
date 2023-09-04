package com.kt.navi.gw.period.impl;

import lombok.Data;

import java.util.Optional;

@Data
public class CommunicationRequestMessage {

    private Optional<String> instanceId;
    private Optional<String> transactionId;
    private Optional<MemberMessage> memberMessage;

    public CommunicationRequestMessage(Optional<String> instanceId, Optional<String> transactionId, Optional<MemberMessage> memberMessage) {
        this.instanceId = instanceId;
        this.transactionId = transactionId;
        this.memberMessage = memberMessage;
    }

    public CommunicationRequestMessage added(String instanceId, String transactionId) {
        return new CommunicationRequestMessage(Optional.of(instanceId), Optional.of(transactionId), memberMessage);
    }

    public CommunicationRequestMessage locationAddedIfNotExists(double lat, double lon) {
        Optional<MemberMessage> updatedMemberMessage = memberMessage.map(memberMsg ->
                memberMsg.locationAddedIfNotExists(lat, lon)
        );

        return new CommunicationRequestMessage(instanceId, transactionId, updatedMemberMessage);
    }
}
