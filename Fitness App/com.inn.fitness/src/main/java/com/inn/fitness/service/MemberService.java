package com.inn.fitness.service;

import com.inn.fitness.POJO.Member;
import com.inn.fitness.wrapper.MemberWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

public interface MemberService {
    public ResponseEntity<String> addNewMember(Map<String, String> requestMap);

    public ResponseEntity<List<Member>> getAllMember();

    public ResponseEntity<String> updateMember(Map<String, String> requestMap);

    public ResponseEntity<String> deleteMember(int id);

    ResponseEntity<MemberWrapper> getMemberById(Integer id);
}

