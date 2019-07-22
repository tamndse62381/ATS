package com.ats.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FunctionPackageService {
    boolean createNewFunctionPackage(int packageId , List<Integer> functionIdList);
}
