package com.example.demo.repository.interfaces;

import java.util.List;

import com.example.demo.pojo.dto.FindUserDto;
import com.example.demo.pojo.po.UserPo;

public interface IUserRepository {

    /**
     * 查詢使用者PO陣列
     * @param findUserDto 查詢使用者DTO
     * @return List<UserPo>
     * @author User 2022年4月2日
     */
    public List<UserPo> findUsers(FindUserDto findUserDto);
}
