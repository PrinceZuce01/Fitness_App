package com.inn.fitness.DAO;

import com.inn.fitness.POJO.Member;
import com.inn.fitness.wrapper.MemberWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberDao extends JpaRepository<Member,Integer> {


    MemberWrapper getMemberById(@Param("id") Integer id);
}
