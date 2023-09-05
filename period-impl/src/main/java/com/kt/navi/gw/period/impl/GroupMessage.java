package com.kt.navi.gw.period.impl;

import lombok.Data;

@Data
public class GroupMessage {
    public String groupId;
    public int myMemberId;
    public Long groupTimestamp;
    public GroupState groupState;
    public MemberStatus[] membersStatus;

    public GroupMessage(String groupId, int myMemberId, Long groupTimestamp) {
        this.groupId = groupId;
        this.myMemberId = myMemberId;
        this.groupTimestamp = groupTimestamp;
    }

    public GroupMessage(String groupId, int myMemberId, Long groupTimestamp, GroupState groupState, MemberStatus[] membersStatus) {
        this.groupId = groupId;
        this.myMemberId = myMemberId;
        this.groupTimestamp = groupTimestamp;
        this.groupState = groupState;
        this.membersStatus = membersStatus;
    }
}
