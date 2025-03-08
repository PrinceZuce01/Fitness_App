package com.inn.fitness.serviceImpl;

import com.google.common.base.Strings;
import com.inn.fitness.DAO.UserDao;
import com.inn.fitness.JWT.JwtFilter;
import com.inn.fitness.JWT.JwtUtil;
import com.inn.fitness.JWT.MyUserDetailsService;
import com.inn.fitness.POJO.User;
import com.inn.fitness.constant.FitnessConstant;
import com.inn.fitness.service.UserService;
import com.inn.fitness.utils.EmailUtils;
import com.inn.fitness.utils.FitnessUtils;
import com.inn.fitness.wrapper.UserWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    EmailUtils emailUtils;


    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside signup{}", requestMap);
        try {
            if (validateSignUpMap(requestMap)) {
                User user = userDao.findByEmailId(requestMap.get("email"));
                if (Objects.isNull(user)) {
                    userDao.save(getUserFromMap(requestMap));
                    return FitnessUtils.getResponseEntity("Successfully Registered", HttpStatus.OK);
                } else {
                    return FitnessUtils.getResponseEntity("Email already exists", HttpStatus.BAD_REQUEST);
                }
            } else {
                return FitnessUtils.getResponseEntity(FitnessConstant.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FitnessUtils.getResponseEntity(FitnessConstant.INVALID_DATA, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private boolean validateSignUpMap(Map<String, String> requestMap) {
        if (requestMap.containsKey("name") && requestMap.containsKey("contactNumber")
                && requestMap.containsKey("email") && requestMap.containsKey("password")) {
            return true;
        }
        return false;
    }

    private User getUserFromMap(Map<String, String> requestMap) {
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setContactNumber(requestMap.get("contactNumber"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setRole("user");
        user.setStatus("false");
        return user;
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        log.info("Inside login{}", requestMap);
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password"))
            );
            if (auth.isAuthenticated()) {
                if (myUserDetailsService.getUserDetail().getStatus().equalsIgnoreCase("true")) {
                    return new ResponseEntity<String>("{\"token\":\""
                            + jwtUtil.generateToken(myUserDetailsService.getUserDetail().getEmail()
                            , myUserDetailsService.getUserDetail().getRole()) + "\"}",
                            HttpStatus.OK);
                } else {
                    return FitnessUtils.getResponseEntity("Wait for admin approval.. maybe took several days (during working hours only)", HttpStatus.BAD_REQUEST);
                }
            }
        } catch (Exception e) {
            log.error("{}", e);
        }
        return FitnessUtils.getResponseEntity("Your password is wrong", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUser() {
        try {
            if (jwtFilter.isAdmin()) {
                return new ResponseEntity<>(userDao.getAllUser(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional<User> optional = userDao.findById(Integer.valueOf(requestMap.get("id")));
                if (optional.isPresent()) {
                    userDao.updateStatus(requestMap.get("status"), Integer.valueOf(requestMap.get("id")));
                    sendMailToAllAdmin(requestMap.get("status"), optional.get().getEmail(), userDao.getAllAdmin());

                    return FitnessUtils.getResponseEntity("Successfully Update User", HttpStatus.OK);
                } else {
                    return FitnessUtils.getResponseEntity("Id does not exist", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                return FitnessUtils.getResponseEntity(FitnessConstant.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>(FitnessConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> checkToken() {
        return FitnessUtils.getResponseEntity("true", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> changePass(Map<String, String> requestMap) {
        try {
            User userObj = userDao.findByEmail(jwtFilter.getCurrentUser());
            if (!userObj.equals(null)) {
                if (userObj.getPassword().equals(requestMap.get("oldPass"))) {
                    userObj.setPassword(requestMap.get("newPass"));
                    userDao.save(userObj);
                    return FitnessUtils.getResponseEntity("Password Updated Successfully", HttpStatus.OK);
                } else {
                    return FitnessUtils.getResponseEntity("Incorrect Old Password", HttpStatus.BAD_REQUEST);
                }
            } else {
                return FitnessUtils.getResponseEntity(FitnessConstant.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FitnessUtils.getResponseEntity(FitnessConstant.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> forgotPass(Map<String, String> requestMap) {
        try {
            User user = userDao.findByEmail(requestMap.get("email"));
            if (!Objects.isNull(user) && !Strings.isNullOrEmpty(user.getEmail()))
                emailUtils.forgotMail(user.getEmail(),"Credentials by Fitness Management", user.getPassword());
                return FitnessUtils.getResponseEntity("Check your mail for credentials.", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FitnessUtils.getResponseEntity(FitnessConstant.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
    }

    private void sendMailToAllAdmin(String status, String user, List<String> allAdmin) {
        allAdmin.remove(jwtFilter.getCurrentUser());
        if (status != null && status.equalsIgnoreCase("true")) {
            emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(), "Account Approved", "USER:- " + user + "\n is approved by \nADMIN:-" + jwtFilter.getCurrentUser(), allAdmin);
        } else {
            emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(), "Account Disabled", "USER:- " + user + "\n is disabled by \nADMIN:-" + jwtFilter.getCurrentUser(), allAdmin);
        }
    }
}
