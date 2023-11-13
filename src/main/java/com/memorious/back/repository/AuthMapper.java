package com.memorious.back.repository;

import com.memorious.back.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthMapper {
    public User findUserByEmail(String email);
    public User findUserByOAuth2Id(String oauth2Id);
    public Integer checkDuplicate(User user);
    public int saveUser(User user);
//    todo : 수정요함
    public int joinFamily(User user);
}
