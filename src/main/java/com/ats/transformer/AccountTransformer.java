package com.ats.transformer;

import com.ats.entity.Account;
import org.springframework.stereotype.Service;

import com.ats.dto.AccountDTO;

@Service
public interface AccountTransformer {
	Account convertToEntity(AccountDTO dto);

	AccountDTO convertToDTO(Account account);
}
