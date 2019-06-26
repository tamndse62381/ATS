package com.ats.service.impl;

import java.util.Date;

import com.ats.dto.UsersDTO;
import com.ats.entity.Users;
import com.ats.repository.UsersRepository;
import com.ats.service.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ats.service.UsersService;

import com.ats.token.TokenAuthenticationService;
import com.ats.util.EncrytedPasswordUtils;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    TokenAuthenticationService tokenService;

    ModelMapper modelMapper;


    private static final Logger LOGGER = LogManager.getLogger(UsersServiceImpl.class);

    private EncrytedPasswordUtils passwordUtil;

    @Override
    public UsersDTO login(String email, String password) {
        LOGGER.info("Begin login in Account Service with email - password: {}", email + " - " + password);
        UsersDTO usersDTO;
        UsersDTO reTurnUsersDTO = new UsersDTO();
        passwordUtil = new EncrytedPasswordUtils();
        modelMapper = new ModelMapper();
        if (email != null) {
            usersDTO = findUserByEmail(email);
            reTurnUsersDTO.setEmail("Account not Existed !! ");
            if (usersDTO != null) {
                reTurnUsersDTO.setEmail("Wrong password !! ");
                if (passwordUtil.compare(password, usersDTO.getPassword())) {

                    if (usersDTO.getStatus().matches("new")) {
                        Date lastLoginDate = new Date();
                        usersRepository.editAccountLastLogin(lastLoginDate, usersDTO.getEmail(), usersDTO.getAccessToken());

                        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
//                       reTurnUsersDTO = modelMapper.map(usersDTO,UsersDTO.class);
                        reTurnUsersDTO = new UsersDTO(usersDTO.getId(), usersDTO.getFullname(),
                                usersDTO.getEmail(), usersDTO.getRoleId(), usersDTO.getAccessToken());

                        return reTurnUsersDTO;
                    } else {
                        reTurnUsersDTO.setEmail("is Banned !!");
                    }
                }
            }
            LOGGER.info("End login in Account Service with result: {}", usersDTO);
        }
        return reTurnUsersDTO;
    }

    @Override
    public int registration(UsersDTO dto) {
        LOGGER.info("Begin registration in Account Service with Account DTO Email : {}", dto.getEmail());
        Users newUsers = null;
        modelMapper = new ModelMapper();
        EncrytedPasswordUtils passwordUtil = new EncrytedPasswordUtils();
        String newPassword = passwordUtil.encrytePassword(dto.getPassword());
        dto.setPassword(newPassword);
        Users users = modelMapper.map(dto, Users.class);
        UsersDTO existedUsers;
        existedUsers = findUserByEmail(dto.getEmail());

        if (existedUsers == null) {
            if (users != null) {
                try {

                    newUsers = usersRepository.save(users);
                    LOGGER.info("End registration in Account Service with result: {}", newUsers.toString());
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } else {
            return -1;
        }
        return newUsers.getId();
    }


    @Override
    public boolean checkPassword(String password, int id) {
        boolean same = true;
        passwordUtil = new EncrytedPasswordUtils();
        if (passwordUtil.compare(password, usersRepository.getOne(id).getPassword())) {
            same = false;
        }
        return same;
    }

    @Override
    public UsersDTO findUserByEmail(String email) {
        LOGGER.info("Begin findAccountByEmail in Account Service with email {}", email);
        modelMapper = new ModelMapper();
        UsersDTO usersDTO = null;
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        Users users;
        if (email != null) {
            users = usersRepository.findAccountByEmail(email);
            if (users != null) {
                usersDTO = modelMapper.map(users, UsersDTO.class);

            }
        }
        LOGGER.info("End findAccountByEmail in Account Service with result: {}", usersDTO);
        return usersDTO;
    }


    @Override
    public UsersDTO findUserByToken(String token) {
        LOGGER.info("Begin findAccountByToken in Account Service with token {}", token);
        UsersDTO usersDTO;
        modelMapper = new ModelMapper();
        Users users;
        UsersDTO reTurnUsersDTO = null;
        int i;

        if (token != null) {
            users = usersRepository.findAccountByToken(token);
            if (users != null) {
                Date nowDate = new Date();
                i = nowDate.compareTo(users.getLastLogin());
                if (i > 10) {
                    return null;
                } else {
                    usersDTO = modelMapper.map(users, UsersDTO.class);
                    reTurnUsersDTO = new UsersDTO(usersDTO.getId(), usersDTO.getFullname(),
                            usersDTO.getEmail(), usersDTO.getRoleId(), usersDTO.getAccessToken());
                    LOGGER.info("End findAccountByToken in Account Service with token: {}",
                            reTurnUsersDTO.getAccessToken());
                }
            } else {
                return null;
            }
        }
        return reTurnUsersDTO;
    }

    @Override
    public int changeStatus(int id, String newStatus) {
        LOGGER.info("Begin changeStatus in Account Service with Account id - newStatus : {}", id + newStatus);
        int success;
        success = usersRepository.changeStatus(id, newStatus);

        LOGGER.info("End changeStatus in Account Service with result: {}", success);
        return success;
    }

    @Override
    public int changePassword(int id, String newPassword, String oldPassword) {
        LOGGER.info("Begin changePassword in Account Service with Account id : {}", id);
        boolean existedPassword = checkPassword(oldPassword, id);
        int success = 0;
        if (existedPassword) {
            return success;
        } else {
            ModelMapper modelMapper = new ModelMapper();
            Users users = usersRepository.getOne(id);
            System.out.println("Account id : " + users.getId());
            UsersDTO usersDTO = modelMapper.map(users, UsersDTO.class);
            if (usersDTO.getStatus().matches("new")) {
                EncrytedPasswordUtils passwordUtil = new EncrytedPasswordUtils();
                newPassword = passwordUtil.encrytePassword(newPassword);
                success = usersRepository.changePassword(id, newPassword);
                LOGGER.info("End changePassword in Account Service with result: {}", success);
            }
        }
        return success;
    }

}
