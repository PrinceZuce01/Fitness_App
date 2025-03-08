package com.inn.fitness.rest;

import com.inn.fitness.POJO.Member;
import com.inn.fitness.wrapper.MemberWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/member")
@CrossOrigin(origins = "*")
public interface MemberRest {

    @PostMapping(path = "/addNewMember")
    public ResponseEntity<String> addNewMember(@RequestBody(required = true) Map<String, String> requestMap);

    @GetMapping(path = "/getAllMember")
    public ResponseEntity<List<Member>> getAllMember();

    @PostMapping(path = "/updateMember")
    public ResponseEntity<String> updateMember(@RequestBody(required = true) Map<String, String> requestMap);

    @DeleteMapping(path = "/deleteMember/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable int id);

    @GetMapping(path = "/getMemberById/{id}")
    public ResponseEntity<MemberWrapper> getMemberById(@PathVariable Integer id);


}
