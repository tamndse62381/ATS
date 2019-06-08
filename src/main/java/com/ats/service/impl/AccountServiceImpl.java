package com.ats.service.impl;

import java.util.Date;
//import java.util.HashMap;

import com.ats.entity.Account;
import com.ats.repository.AccountRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ats.dto.AccountDTO;
import com.ats.service.AccountService;

import com.ats.token.TokenAuthenticationService;
import com.ats.util.EncrytedPasswordUtils;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    TokenAuthenticationService tokenService;

    ModelMapper modelMapper;


    private static final Logger LOGGER = LogManager.getLogger(AccountServiceImpl.class);

    private EncrytedPasswordUtils passwordUtil;

    @Override
    public AccountDTO login(String email, String password) {
        LOGGER.info("Begin login in Account Service with email - password: {}", email + " - " + password);
        AccountDTO accountDTO;
        AccountDTO reTurnAccountDTO = new AccountDTO();
        passwordUtil = new EncrytedPasswordUtils();
        if (email != null) {
            accountDTO = findAccountByEmail(email);
            reTurnAccountDTO.setEmail("Account not Existed !! ");
            if (accountDTO != null) {
                reTurnAccountDTO.setEmail("Wrong password !! ");
                if (passwordUtil.compare(password, accountDTO.getPassword())) {
                    if (accountDTO.getStatus().matches("new")) {
                        Date lastLoginDate = new Date();
                        accountRepository.editAccountLastLogin(lastLoginDate, accountDTO.getEmail(), accountDTO.getAccessToken());
                        reTurnAccountDTO = new AccountDTO(accountDTO.getId(), accountDTO.getFullname(),
                                accountDTO.getEmail(), accountDTO.getRoleId(), accountDTO.getAccessToken());
                        return reTurnAccountDTO;
                    }
                }
            }
            LOGGER.info("End login in Account Service with result: {}", accountDTO);
        }
        return reTurnAccountDTO;
    }

    @Override
    public int registration(AccountDTO dto) {
        LOGGER.info("Begin registration in Account Service with Account DTO Email : {}", dto.getEmail());
        Account newAccount = null;
        modelMapper = new ModelMapper();
        EncrytedPasswordUtils passwordUtil = new EncrytedPasswordUtils();
        String newPassword = passwordUtil.encrytePassword(dto.getPassword());
        dto.setPassword(newPassword);
        Account account = modelMapper.map(dto, Account.class);
        AccountDTO existedAccount;
        existedAccount = findAccountByEmail(dto.getEmail());

        if (existedAccount == null) {
            if (account != null) {
                try {
                    newAccount = accountRepository.save(account);
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
        if (accountRepository.findAccountByEmail(email) != null) {
            valid = false;
        }
        return valid;
    }

    @Override
    public boolean checkPassword(String password, int id) {
        boolean same = true;
        passwordUtil = new EncrytedPasswordUtils();
        if (passwordUtil.compare(password, accountRepository.getOne(id).getPassword())) {
            same = false;
        }
        return same;
    }

    @Override
    public AccountDTO findAccountByEmail(String email) {
        LOGGER.info("Begin findAccountByEmail in Account Service with email {}", email);
        modelMapper = new ModelMapper();
        AccountDTO accountDTO = null;
        Account account;
        if (email != null) {
            account = accountRepository.findAccountByEmail(email);
            if (account != null) {
                accountDTO = modelMapper.map(account, AccountDTO.class);
            }
        }
        LOGGER.info("End findAccountByEmail in Account Service with result: {}", accountDTO);
        return accountDTO;
    }


    @Override
    public AccountDTO findAccountByToken(String token) {
        LOGGER.info("Begin findAccountByToken in Account Service with token {}", token);
        AccountDTO accountDTO;
        modelMapper = new ModelMapper();
        Account account;
        AccountDTO reTurnAccountDTO = null;
        int i;

        if (token != null) {
            account = accountRepository.findAccountByToken(token);
            if (account != null) {
                Date nowDate = new Date();
                i = nowDate.compareTo(account.getLastLogin());
                if (i > 10) {
                    return null;
                } else {
                    accountDTO = modelMapper.map(account, AccountDTO.class);
                    reTurnAccountDTO = new AccountDTO(accountDTO.getId(), accountDTO.getFullname(),
                            accountDTO.getEmail(), accountDTO.getRoleId(), accountDTO.getAccessToken());
                    LOGGER.info("End findAccountByToken in Account Service with token: {}",
                            reTurnAccountDTO.getAccessToken());
                }
            } else {
                return null;
            }
        }
        return reTurnAccountDTO;
    }

    @Override
    public int changeStatus(int id, String newStatus) {
        LOGGER.info("Begin changeStatus in Account Service with Account id - newStatus : {}", id + newStatus);
        int success;
        success = accountRepository.changeStatus(id, newStatus);
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
            Account account = accountRepository.getOne(id);
            System.out.println("Account id : " + account.getID());
            AccountDTO accountDTO = modelMapper.map(account, AccountDTO.class);
            if (accountDTO.getStatus().matches("new")) {
                EncrytedPasswordUtils passwordUtil = new EncrytedPasswordUtils();
                newPassword = passwordUtil.encrytePassword(newPassword);
                success = accountRepository.changePassword(id, newPassword);
                LOGGER.info("End changePassword in Account Service with result: {}", success);
            }
        }
        return success;
    }

}
