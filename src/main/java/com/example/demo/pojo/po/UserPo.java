package com.example.demo.pojo.po;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 使用者PO
 * @author User
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPo {

	private String accountId;
	
	private String password;
	
	private String name;
	
	private BigDecimal age;
	
	private String email;
	
	private LocalDateTime insertDateTime;
}
