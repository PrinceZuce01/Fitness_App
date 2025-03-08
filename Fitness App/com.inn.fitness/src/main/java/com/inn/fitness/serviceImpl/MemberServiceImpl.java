package com.inn.fitness.serviceImpl;

import com.inn.fitness.DAO.MemberDao;
import com.inn.fitness.JWT.JwtFilter;
import com.inn.fitness.POJO.Member;
import com.inn.fitness.constant.FitnessConstant;
import com.inn.fitness.service.MemberService;
import com.inn.fitness.utils.FitnessUtils;
import com.inn.fitness.wrapper.MemberWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberDao memberDao;

    @Autowired
    JwtFilter jwtFilter;

    @Override
    public ResponseEntity<String> addNewMember(Map<String, String> requestMap) {
        log.info("inside_AddMember{}", requestMap);
        try {
            if (jwtFilter.isAdmin()) {
                if (validateAddNewMember(requestMap, false)) {

                    memberDao.save(getMemberFromMap(requestMap, true));

                    return FitnessUtils.getResponseEntity("Yeay!! someone just joined your Fitness Centre!!", HttpStatus.OK);
                } else {
                    return new ResponseEntity<String>("Please fill your new member data appropriately", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                return new ResponseEntity<String>("Sorry, you are not the admin..", HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<String>(FitnessConstant.INVALID_DATA, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private boolean validateAddNewMember(Map<String, String> requestMap, Boolean validateId) {
        if (requestMap.containsKey("name") && requestMap.containsKey("age") && requestMap.containsKey("weight")
                && requestMap.containsKey("height")
        ) {
            if (validateId && requestMap.containsKey("id")) {
                return true;
            } else if (!validateId) {
                return true;

            } else {
                return false;
            }
        }
        return false;
    }

    private Member getMemberFromMap(Map<String, String> requestMap, Boolean isAdd) {
        Member member = new Member();
        if (!isAdd) {
            member.setId(Integer.valueOf(requestMap.get("id")));
        }
        member.setName(requestMap.get("name"));
        member.setAge(Integer.parseInt(requestMap.get("age")));
        member.setWeight(Float.valueOf(requestMap.get("weight")));
        member.setHeight(Float.valueOf(requestMap.get("height")));

        //calculate BMI
        float heightInMeters = member.getHeight() / 100;
        float bmi = member.getWeight() / (heightInMeters * heightInMeters);
        member.setBmi(bmi);

        return member;
    }

    @Override
    public ResponseEntity<List<Member>> getAllMember() {
        try {
            return new ResponseEntity<List<Member>>(memberDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<List<Member>>(new ArrayList<Member>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateMember(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validateAddNewMember(requestMap, true)) {
                    Optional optional = memberDao.findById(Integer.valueOf(requestMap.get("id")));
                    if (optional.isPresent()) {
                        memberDao.save(getMemberFromMap(requestMap, true));
                        return FitnessUtils.getResponseEntity("Successfully Updated Member", HttpStatus.OK);
                    } else {
                        return FitnessUtils.getResponseEntity("Do you have this member in your Fitness Centre?", HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                } else {
                    return new ResponseEntity<String>(FitnessConstant.UNABLE_TO_ADD_DATA, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                return new ResponseEntity<String>("Sorry, you are not the admin..", HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>(FitnessConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteMember(int id) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional<Member> optionalMember = memberDao.findById(id);
                if (optionalMember.isPresent()) {
                    memberDao.deleteById(id);
                    return FitnessUtils.getResponseEntity("It is sad to see a member gone:_(", HttpStatus.OK);
                } else {
                    return FitnessUtils.getResponseEntity("Something went wrong", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<String>("Sorry, you are not the admin..", HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>(FitnessConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<MemberWrapper> getMemberById(Integer id) {
        try {
            Optional<Member> optionalMember = memberDao.findById(id);
            if (optionalMember.isPresent()) {
                return new ResponseEntity<>(memberDao.getMemberById(id), HttpStatus.OK);
            } else {

                return new ResponseEntity<>(new MemberWrapper(), HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new MemberWrapper(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
