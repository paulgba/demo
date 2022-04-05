package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.bo.UserBo;
import com.example.demo.pojo.dto.FindUserDto;
import com.example.demo.pojo.dto.UserDto;
import com.example.demo.pojo.po.UserPo;
import com.example.demo.repository.interfaces.IUserRepository;
import com.example.demo.validation.AgeValidator;
import com.example.demo.validation.InsertDateTimeValidator;
import com.example.demo.validation.NameValidator;

@Service
public class UserService {
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	/*public UserService(IUserRepository userRepository, UserMapper userMapper) {
	    this.userRepository = userRepository;
	    //this.userMapper = userMapper;
	}*/
	
	/*public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
        //this.userMapper = userMapper;
    }*/

	/**
	 * 查詢使用者DTO陣列
	 * @param findUserDto 查詢使用者DTO
	 * @return List<UserDto> 使用者DTO陣列
	 * @throws Exception Exception
	 * @author User 2022年4月2日
	 */
	public List<UserDto> findUsers(FindUserDto findUserDto) throws Exception {
		// 1. 檢查參數
		checkFindUserDto(findUserDto);
		// 2. 查詢使用者PO陣列
		List<UserPo> userPoList = userRepository.findUsers(findUserDto);
		// 3. 調整使用者PO陣列
		List<UserBo> userBoList = transToUserBoList(userPoList, "*", "*");
		// 4. 使用者BO陣列轉使用者DTO陣列
		List<UserDto> userDtoList = userMapper.userBoListToUserDtoList(userBoList);
//		List<UserDto> userDtoList = userBoList.stream().map(userBo -> new UserDto(userBo)).toList();
		// 5. 回傳
		return userDtoList;
	}
	
	/**
	 * 檢查參數
	 * @param findUserDto 查詢使用者DTO
	 * @throws Exception Exception
	 * @author User 2022年4月2日
	 */
	private void checkFindUserDto(FindUserDto findUserDto) throws Exception {
	    // 1. 檢查姓名
	    NameValidator.validate(findUserDto.getName());
		// 2. 檢查年齡
		AgeValidator.validate(findUserDto.getAge());
		// 3. 檢查新增時間
		InsertDateTimeValidator.validate(findUserDto.getInsertDateTime());
	}
	
	/**
     * 調整使用者PO陣列
     * @param userPoList 使用者PO陣列
     * @param prefix name前綴字
     * @param suffix name後綴字
     * @return List<UserBo>
     * @author User 2022年4月3日
     */
	private List<UserBo> transToUserBoList(List<UserPo> userPoList, String prefix, String suffix) {
        return userPoList
                .stream()
                .map(userPo -> {
                    UserBo userBo = new UserBo(userPo);
                    userBo.setName(prefix + userBo.getName() + suffix);
                    return userBo;
                })
                .toList();
    }
}
