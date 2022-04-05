package com.example.demo.pojo.bo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.demo.pojo.po.UserPo;

import lombok.Data;

/**
 * 使用者BO
 * @author User
 *
 */
@Data
public class UserBo {

    private String accountId;
    
    private String password;
    
    private String name;
    
    private BigDecimal age;
    
    private String email;
    
    private LocalDateTime insertDateTime;
    
    public UserBo(UserPo userPo) {
        this.accountId = userPo.getAccountId();
        this.password = userPo.getPassword();
        this.name = userPo.getName();
        this.age = userPo.getAge();
        this.email = userPo.getEmail();
        this.insertDateTime = userPo.getInsertDateTime();
    }
}
