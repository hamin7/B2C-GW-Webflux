package com.kt.navi.gw.period.impl;

@lombok.Data
public class LogMessage {

    // double 타입으로 하는것이 맞는가? scala 코드에는 number 타입임...
    public double timestamp;
    public String instanceId;
    public Location location;

    public Data data;
    public CommunicationRequestMessage communication;

    public LogMessage() {}
    public LogMessage(double timestamp, String instanceId, Location location) {
        this.timestamp = timestamp;
        this.instanceId = instanceId;
        this.location = location;
    }
}
