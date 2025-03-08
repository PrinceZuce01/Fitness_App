package com.inn.fitness.POJO;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@NamedQuery(name = "Member.getMemberById", query = "select new com.inn.fitness.wrapper.MemberWrapper(m.id,m.name,m.age,m.weight,m.height,m.bmi) from Member m where m.id=:id")

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "member")
@XmlRootElement(name = "member")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "weight")
    private Float weight;

    @Column(name = "height")
    private Float height;

    @Column(name = "bmi")
    private Float bmi;





}
