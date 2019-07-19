package com.ats.service;

import com.ats.dto.ServicePackageDTO;
import org.springframework.stereotype.Service;

@Service
public interface ServicePackageService {
    int createServicePack(ServicePackageDTO servicePackageDTO);

    int updateServicePack(ServicePackageDTO servicePackageDTO);

    int changeServicePackStatus(int id , String newStatus);
}
