package com.inn.fitness.restImpl;

import com.inn.fitness.constant.FitnessConstant;
import com.inn.fitness.rest.UserRest;
import com.inn.fitness.service.UserService;
import com.inn.fitness.utils.FitnessUtils;
import com.inn.fitness.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class UserRestIpml implements UserRest {

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try{
            return userService.signUp(requestMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return FitnessUtils.getResponseEntity(FitnessConstant.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        try{
            return userService.login(requestMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return FitnessUtils.getResponseEntity(FitnessConstant.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUser() {
        try{
            return userService.getAllUser();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<List<UserWrapper>>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {
        try{
            return userService.update(requestMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return FitnessUtils.getResponseEntity(FitnessConstant.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> checkToken() {
        try{
            return userService.checkToken();
        }catch (Exception e){
            e.printStackTrace();
        }
        return FitnessUtils.getResponseEntity(FitnessConstant.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> changePass(Map<String, String> requestMap) {
        try{
            return userService.changePass(requestMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return FitnessUtils.getResponseEntity(FitnessConstant.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> forgotPass(Map<String, String> requestMap) {
        try{
            return userService.forgotPass(requestMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return FitnessUtils.getResponseEntity(FitnessConstant.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

