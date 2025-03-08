package com.inn.fitness.restImpl;

import com.inn.fitness.POJO.Member;
import com.inn.fitness.constant.FitnessConstant;
import com.inn.fitness.rest.MemberRest;
import com.inn.fitness.service.MemberService;
import com.inn.fitness.wrapper.MemberWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class MemberRestImpl implements MemberRest {

    @Autowired
    MemberService memberService;

    @Override
    public ResponseEntity<String> addNewMember(Map<String, String> requestMap) {
        try{
            return memberService.addNewMember(requestMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<String >(FitnessConstant.UNABLE_TO_ADD_DATA, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Member>> getAllMember() {
        try {
            return memberService.getAllMember();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<List<Member> >(new ArrayList<Member>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateMember(Map<String, String> requestMap) {
        try{
            return memberService.updateMember(requestMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<String >(FitnessConstant.UNABLE_TO_ADD_DATA, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteMember(int id) {
        try{
            return memberService.deleteMember(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String >(FitnessConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<MemberWrapper> getMemberById(Integer id) {
        try{
            return memberService.getMemberById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new MemberWrapper(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
