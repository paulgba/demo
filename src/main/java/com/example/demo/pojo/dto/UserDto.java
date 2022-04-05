package com.example.demo.pojo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.demo.pojo.bo.UserBo;

import lombok.Data;

/**
 * 使用者DTO
 * @author User
 *
 */
@Data
public class UserDto {

	private String accountId;
	
	private String password;
	
	private String name;
	
	private BigDecimal age;
	
	private String email;
	
	private LocalDateTime insertDateTime;
	
	public UserDto(UserBo userBo) {
        this.accountId = userBo.getAccountId();
        this.password = userBo.getPassword();
        this.name = userBo.getName();
        this.age = userBo.getAge();
        this.email = userBo.getEmail();
        this.insertDateTime = userBo.getInsertDateTime();
    }
}
