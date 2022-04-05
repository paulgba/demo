package com.example.demo.repository.implments;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.pojo.dto.FindUserDto;
import com.example.demo.pojo.po.UserPo;
import com.example.demo.repository.interfaces.IUserRepository;

@Service
public class UserRepository implements IUserRepository {

    @Override
	public List<UserPo> findUsers(FindUserDto findUserDto) {
		List<UserPo> userPoList = Arrays.asList(
				new UserPo("1", "aaa", "apple", new BigDecimal(10), "apple@a.com", LocalDateTime.of(2012, Month.APRIL, 2, 1, 20, 10)),
				new UserPo("2", "bbb", "bird", new BigDecimal(20), "bird@a.com", LocalDateTime.of(2013, Month.MAY, 3, 2, 20, 10)),
				new UserPo("3", "ccc", "cat", new BigDecimal(30), "cat@a.com", LocalDateTime.of(2014, Month.JUNE, 4, 3, 20, 10)),
				new UserPo("4", "ddd", "dog", new BigDecimal(40), "dog@a.com", LocalDateTime.of(2015, Month.JULY, 5, 4, 20, 10)),
				new UserPo("5", "eee", "egg", new BigDecimal(50), "egg@a.com", LocalDateTime.of(2016, Month.AUGUST, 6, 5, 20, 10)),
				new UserPo("6", "fff", "flower", new BigDecimal(60), "flower@a.com", LocalDateTime.of(2017, Month.SEPTEMBER, 7, 1, 20, 10)),
				new UserPo("7", "ggg", "gun", new BigDecimal(70), "gun@a.com", LocalDateTime.of(2018, Month.OCTOBER, 8, 7, 20, 10)),
				new UserPo("8", "hhh", "happy", new BigDecimal(80), "happy@a.com", LocalDateTime.of(2019, Month.NOVEMBER, 9, 8, 20, 10)),
				new UserPo("9", "iii", "illy", new BigDecimal(90), "illy@a.com", LocalDateTime.of(2020, Month.DECEMBER, 10, 9, 20, 10)),
				new UserPo("10", "jjj", "jason", new BigDecimal(100), "jason@a.com", LocalDateTime.of(2021, Month.JANUARY, 11, 10, 20, 10)));
		return userPoList;

	}
}
