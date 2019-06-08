package com.ats.service.impl;

import java.util.Date;
//import java.util.HashMap;

import com.ats.dto.UsersDTO;
import com.ats.entity.Account;
import com.ats.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ats.service.AccountService;

import com.ats.token.TokenAuthenticationService;
import com.ats.util.EncrytedPasswordUtils;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    TokenAuthenticationService tokenService;

    ModelMapper modelMapper;


    private static final Logger LOGGER = LogManager.getLogger(AccountServiceImpl.class);

    private EncrytedPasswordUtils passwordUtil;

    @Override
    public UsersDTO login(String email, String password) {
        LOGGER.info("Begin login in Account Service with email - password: {}", email + " - " + password);
        UsersDTO usersDTO;
        UsersDTO reTurnUsersDTO = new UsersDTO();
        passwordUtil = new EncrytedPasswordUtils();
        if (email != null) {
            usersDTO = findAccountByEmail(email);
            reTurnUsersDTO.setEmail("Account not Existed !! ");
            if (usersDTO != null) {
                reTurnUsersDTO.setEmail("Wrong password !! ");
                if (passwordUtil.compare(password, usersDTO.getPassword())) {
                    if (usersDTO.getStatus().matches("new")) {
                        Date lastLoginDate = new Date();
                        userRepository.editAccountLastLogin(lastLoginDate, usersDTO.getEmail(), usersDTO.getAccessToken());
                        reTurnUsersDTO = new UsersDTO(usersDTO.getId(), usersDTO.getFullname(),
                                usersDTO.getEmail(), usersDTO.getRoleId(), usersDTO.getAccessToken());
                        return reTurnUsersDTO;
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
        Account newAccount = null;
        modelMapper = new ModelMapper();
        EncrytedPasswordUtils passwordUtil = new EncrytedPasswordUtils();
        String newPassword = passwordUtil.encrytePassword(dto.getPassword());
        dto.setPassword(newPassword);
        Account account = modelMapper.map(dto, Account.class);
        UsersDTO existedAccount;
        existedAccount = findAccountByEmail(dto.getEmail());

        if (existedAccount == null) {
            if (account != null) {
                try {
                    newAccount = userRepository.save(account);
                    System.out.println("NEW ID  " + newAccount.getID());
                    LOGGER.info("End registration in Account Service with result: {}", newAccount.toString());
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } else {
            return -1;
        }
        return newAccount.getID();
    }

    @Override
    public boolean checkAccountValidation(String email) {
        boolean valid = true;
        if (userRepository.findAccountByEmail(email) != null) {
            valid = false;
        }
        return valid;
    }

    @Override
    public boolean checkPassword(String password, int id) {
        boolean same = true;
        passwordUtil = new EncrytedPasswordUtils();
        if (passwordUtil.compare(password, userRepository.getOne(id).getPassword())) {
            same = false;
        }
        return same;
    }

    @Override
    public UsersDTO findAccountByEmail(String email) {
        LOGGER.info("Begin findAccountByEmail in Account Service with email {}", email);
        modelMapper = new ModelMapper();
        UsersDTO usersDTO = null;
        Account account;
        if (email != null) {
            account = userRepository.findAccountByEmail(email);
            if (account != null) {
                usersDTO = modelMapper.map(account, UsersDTO.class);
            }
        }
        LOGGER.info("End findAccountByEmail in Account Service with result: {}", usersDTO);
        return usersDTO;
    }


    @Override
    public UsersDTO findAccountByToken(String token) {
        LOGGER.info("Begin findAccountByToken in Account Service with token {}", token);
        UsersDTO usersDTO;
        modelMapper = new ModelMapper();
        Account account;
        UsersDTO reTurnUsersDTO = null;
        int i;

        if (token != null) {
            account = userRepository.findAccountByToken(token);
            if (account != null) {
                Date nowDate = new Date();
                i = nowDate.compareTo(account.getLastLogin());
                if (i > 10) {
                    return null;
                } else {
                    usersDTO = modelMapper.map(account, UsersDTO.class);
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
        success = userRepository.changeStatus(id, newStatus);
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
            Account account = userRepository.getOne(id);
            System.out.println("Account id : " + account.getID());
            UsersDTO usersDTO = modelMapper.map(account, UsersDTO.class);
            if (usersDTO.getStatus().matches("new")) {
                EncrytedPasswordUtils passwordUtil = new EncrytedPasswordUtils();
                newPassword = passwordUtil.encrytePassword(newPassword);
                success = userRepository.changePassword(id, newPassword);
                LOGGER.info("End changePassword in Account Service with result: {}", success);
            }
        }
        return success;
    }

}
