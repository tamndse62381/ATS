package com.ats.transformer;

import org.springframework.stereotype.Service;

import com.ats.dto.AccountDTO;
import com.ats.entity.Account;

@Service
public interface AccountTransformer {
	Account convertToEntity(AccountDTO dto);

	AccountDTO convertToDTO(Account account);
}
