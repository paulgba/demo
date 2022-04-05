package com.example.demo.pojo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 查詢使用者DTO
 * @author User
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindUserDto {

	private String name;
	
	private BigDecimal age;
	
	private LocalDateTime insertDateTime;
}
