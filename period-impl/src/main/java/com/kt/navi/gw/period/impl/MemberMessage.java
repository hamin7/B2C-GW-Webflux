package com.kt.navi.gw.period.impl;

public class MemberMessage {
    public String groupId;
    // scala에서 number타입 -> 일단 double로 해둠
    public double groupTimestamp;
    public double memberId;

    // member.status.name 이렇게 들어가는데... 이렇게 구현해도 되는가...
    public class MemberStatus {
        public String name;
        // TODO : json 타입 객체는 어떻게 처리해야 하는가... (member.status.value)

        public MemberStatus() {}
        public MemberStatus(String name) {
            this.name = name;
        }
    }
}
