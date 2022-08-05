package com.study.board.entity;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long memberid; // 구분을 위한 시스템에 저장하는
    // @Column(name = "username") username 하고 Mapping
    private String memberpass;

    private String membername;


    public Long getMemberId() {
        return memberid;
    }

    public void setMemberId(Long MemberId) { this.memberid = memberid;}

    public String getMemberPass() { return memberpass; }

    public void setMemberPass(String MemberPass) { this.memberpass = memberpass;}

    public String getMemberName() {
        return membername;
    }

    public void setName(String MemberName) { this.membername = membername; }
}
