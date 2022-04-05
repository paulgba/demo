package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.demo.pojo.bo.UserBo;
import com.example.demo.pojo.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * 使用者BO陣列轉使用者DTO陣列
     * @param userBoList 使用者BO陣列
     * @return List<UserDto>
     * @author User 2022年4月2日
     */
	public List<UserDto> userBoListToUserDtoList(List<UserBo> userBoList);
}
