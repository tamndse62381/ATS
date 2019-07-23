package com.ats.service;


import com.ats.dto.UsersDTO;
import com.ats.dto.UsersDTO2;
import com.ats.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsersService {
    UsersDTO login(String email, String password);

    int registration(UsersDTO dto);

    int changePassword(int id, String newPassword, String oldPassword);

    int changeStatus(int id, String newStatus);

    boolean checkPassword(String password, int id);

    UsersDTO findUserByEmail(String email);

    UsersDTO2 findUserByToken(String token);

    int updateUser(UsersDTO dto);

    int changeRole(int id, int role);

    Page<Users> getAllUser(Pageable pageable, String search, String status);


}
