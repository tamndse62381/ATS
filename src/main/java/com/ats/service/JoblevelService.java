package com.ats.service;

import com.ats.entity.Joblevel;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JoblevelService {
    List<Joblevel> getAllJobLevel();
}
