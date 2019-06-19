package com.ats.service;

import com.ats.dto.CitySummary;
import com.ats.entity.City;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CityService {

    List<City> getAllCity();

    CitySummary getCityById(int id);
}
