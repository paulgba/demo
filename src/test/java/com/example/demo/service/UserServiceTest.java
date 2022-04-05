package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.bo.UserBo;
import com.example.demo.pojo.dto.FindUserDto;
import com.example.demo.pojo.dto.UserDto;
import com.example.demo.pojo.po.UserPo;
import com.example.demo.repository.interfaces.IUserRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class UserServiceTest {

    @Mock
    private FindUserDto findUserDto;
    
    @Mock
    private IUserRepository userRepository;
    
    @Mock
    private UserMapper userMapper;
    
    @InjectMocks
    private UserService userService;
    
//    @Disabled
    @Order(1)
    @DisplayName(value = "findUsers(FindUserDto findUserDto);，1. 不拋出Exception，2. userDtoList.get(0).getName() = prefix + name + suffix")
    @ParameterizedTest(name = "provideFindUserDtoIsValid: {index} {0}")
    @MethodSource("provideFindUserDtoIsValid")
    public void findUsers_userDtoList_hasDataAndFirstElementNameIsExpected(FindUserDto findUserDto) {
        String name = findUserDto.getName();
        // TODO: 研究@Nested
        // 設定假資料
        UserPo userPoForFakeUserPoList = new UserPo("0001", "aaa", name, new BigDecimal(10), "apple@a.com", LocalDateTime.of(2012, Month.APRIL, 2, 1, 20, 10));
        List<UserPo> fakeUserPoList = Arrays.asList(userPoForFakeUserPoList);
        // 設定假資料，transToUserBoList方法會改變name的值，例如：prefix + name + suffix，若假資料不符合，mapper由BO陣列轉DTO陣列結果會變成空陣列，造成userDtoList.get(0)拋出IndexOutOfBoundsException
        String prefix = "*";
        String suffix = "*";
        UserPo userPoForfakeUserBoList = new UserPo("0001", "aaa", prefix + name + suffix, new BigDecimal(10), "apple@a.com", LocalDateTime.of(2012, Month.APRIL, 2, 1, 20, 10));
        List<UserBo> fakeUserBoList = Arrays.asList(new UserBo(userPoForfakeUserBoList));
        // 設定假資料
        List<UserDto> fakeUserDtoList = Arrays.asList(new UserDto(fakeUserBoList.get(0)));
        
        // 將假資料設定到，查詢DB的方法上
        when(userRepository.findUsers(findUserDto)).thenReturn(fakeUserPoList);
        // 將假資料設定到，使用mapstruct的方法上
        when(userMapper.userBoListToUserDtoList(fakeUserBoList)).thenReturn(fakeUserDtoList);
        // 預期不拋出Exception
        assertDoesNotThrow(() -> {
            List<UserDto> userDtoList = userService.findUsers(findUserDto);
            // TODO: 改成assertAll，將每個欄位都驗證
            // 預期userDtoList.get(0).getName() = prefix + name + suffix
            assertEquals(prefix + name + suffix, userDtoList.get(0).getName());
        });
    }
    
    /**
     * @MethodSource provideFindUserDtoIsValid
     * @return Stream<FindUserDto>
     * @author User 2022年4月3日
     */
    private static Stream<FindUserDto> provideFindUserDtoIsValid() {
        return Stream.of(
                    new FindUserDto("apple", BigDecimal.ZERO, LocalDateTime.now()),
                    new FindUserDto("bird", BigDecimal.ONE, LocalDateTime.now()),
                    new FindUserDto("cat", BigDecimal.TEN, LocalDateTime.now().minusYears(1)),
                    new FindUserDto("dog", new BigDecimal(99), LocalDateTime.now().plusHours(10))//,
                    //new FindUserDto("egg", new BigDecimal(300), LocalDateTime.now().plusMonths(5)),
                    //new FindUserDto("flower", new BigDecimal(-1), LocalDateTime.now()),
                    //new FindUserDto("gun", null, LocalDateTime.now())
                );
    }
    
//    @Disabled
    @Order(2)
    @DisplayName(value = "findUsers(FindUserDto findUserDto);，1. \"姓名異常\"")
    @ParameterizedTest
    @MethodSource
    public void findUsers_name_invalid(FindUserDto findUserDto) {
        // 預期會拋出Exception
        Exception thrown = assertThrows(Exception.class, () -> {
            userService.findUsers(findUserDto);
        });
        // 預期thrown.getMessage = "姓名異常"
        assertEquals("姓名異常", thrown.getMessage());
    }
    
    /**
     * @MethodSource findUsers_name_invalid
     * @return Stream<FindUserDto>
     * @author User 2022年4月3日
     */
    private static Stream<FindUserDto> findUsers_name_invalid() {
        return Stream.of(
                new FindUserDto(null, BigDecimal.ONE, LocalDateTime.now()),
                new FindUserDto("", BigDecimal.ONE, LocalDateTime.now()),
                new FindUserDto(" ", BigDecimal.ONE, LocalDateTime.now()),
                new FindUserDto("  ", BigDecimal.ONE, LocalDateTime.now()),
                new FindUserDto("　", BigDecimal.ONE, LocalDateTime.now()),
                new FindUserDto("　 ", BigDecimal.ONE, LocalDateTime.now()),
                new FindUserDto("0", BigDecimal.ONE, LocalDateTime.now()),
                new FindUserDto("123", BigDecimal.ONE, LocalDateTime.now()),
                new FindUserDto("45678", BigDecimal.ONE, LocalDateTime.now()),
                new FindUserDto("7.89", BigDecimal.ONE, LocalDateTime.now()),
                new FindUserDto("-123", BigDecimal.ONE, LocalDateTime.now()),
                new FindUserDto("-45678", BigDecimal.ONE, LocalDateTime.now()),
                new FindUserDto("-7.89", BigDecimal.ONE, LocalDateTime.now())
            );
    }
    
    @Disabled
    @Order(3)
    @DisplayName(value = "aa")
    @Test
    public void aa() {
        when(findUserDto.getName()).thenReturn("123");
        Exception thrown = assertThrows(Exception.class, () -> {
            ReflectionTestUtils.invokeMethod(userService, "checkFindUserDto", findUserDto);
        });
        assertEquals("姓名異常", thrown.getCause().getMessage());
    }
    
    @Disabled
    @Order(4)
    @DisplayName(value = "bb")
    @Test
    public void bb() {
        UserPo userPoForFakeUserPoList = new UserPo("0001", "aaa", "apple", new BigDecimal(10), "apple@a.com", LocalDateTime.of(2012, Month.APRIL, 2, 1, 20, 10));
        List<UserPo> fakeUserPoList = Arrays.asList(userPoForFakeUserPoList);
        String prefix = "@";
        String suffix = "$";
        
        //UserPo userPoForfakeUserBoList = new UserPo("1", "aaa", prefix + "apple" + suffix, new BigDecimal(10), "apple@a.com", LocalDateTime.of(2012, Month.APRIL, 2, 1, 20, 10));
        //List<UserBo> fakeUserBoList = Arrays.asList(new UserBo(userPoForfakeUserBoList));
        
        //when(ReflectionTestUtils.invokeMethod(userService, "transToUserBoList", fakeUserPoList, prefix, suffix)).thenReturn(fakeUserBoList);
        
        List<UserBo> userBoList = ReflectionTestUtils.invokeMethod(userService, "transToUserBoList", fakeUserPoList, prefix, suffix);
        assertEquals(prefix + "apple" + suffix, userBoList.get(0).getName());
    }
}
