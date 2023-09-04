package com.kt.navi.gw.period.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemberMessage {
    public String groupId;
    public Long groupTimestamp;
    public Optional<MemberStatus> memberStatus;

    public MemberMessage(String groupId, long groupTimestamp, Optional<MemberStatus> memberStatus) {
        this.groupId = groupId;
        this.groupTimestamp = groupTimestamp;
        this.memberStatus = memberStatus;
    }

    public MemberMessage locationAddedIfNotExists(double lat, double lon) {
        Optional<MemberStatus> updatedMemberStatus = memberStatus.map(memberStat -> {
            if (!memberStat.getStatus().has(MemberStatus.locationStatusName)) {
                ObjectNode newStatus = JsonNodeFactory.instance.objectNode();
                newStatus.set(MemberStatus.locationStatusName, createLocationJson(lat, lon));
                return memberStat.copyWithNewStatus(newStatus);
            }
            return memberStat;
        });
        return new MemberMessage(groupId, groupTimestamp, updatedMemberStatus);
    }

    private JsonNode createLocationJson(double lat, double lon) {
        ObjectNode locationJson = JsonNodeFactory.instance.objectNode();
        locationJson.put(MemberStatus.latitudeName, lat);
        locationJson.put(MemberStatus.longitudeName, lon);
        return locationJson;
    }

    class MemberStatus {
        private JsonNode status;

        public MemberStatus(JsonNode status) {
            this.status = status;
        }

        public JsonNode getStatus() {
            return status;
        }

        public MemberStatus copyWithNewStatus(JsonNode newStatus) {
            return new MemberStatus(newStatus);
        }

        public Double getLatitude() {
            if (status.has(latitudeName)) {
                return status.get(latitudeName).asDouble();
            }
            return null;
        }

        public Double getLongitude() {
            if (status.has(longitudeName)) {
                return status.get(longitudeName).asDouble();
            }
            return null;
        }

        public static final String locationStatusName = "location";
        public static final String latitudeName = "lat";
        public static final String longitudeName = "lon";
    }
}
