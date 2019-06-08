package com.ats.service;

import com.ats.entity.Socialactivity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SocialactivityService {
    public List<Socialactivity> findAllSocialactivityByCVID(int id);

    public boolean editASocialactivity(Socialactivity editedSocialactivity,int id);

    public Socialactivity createANewSocialactivity(Socialactivity newSocialactivity);

    public Socialactivity findOneByID(int id);

    
}
