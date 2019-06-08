package com.ats.service.impl;

import com.ats.entity.Socialactivity;
import com.ats.repository.SocialactivityRepository;
import com.ats.service.SocialactivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialactivityServiceImpl implements SocialactivityService {
    @Autowired
    private SocialactivityRepository socialactivityRepository;

    @Override
    public List<Socialactivity> findAllSocialactivityByCVID(int id) {
        return socialactivityRepository.findAllSocialactivityByCVID(id);
    }

    @Override
    public boolean editASocialactivity(Socialactivity editedSocialactivity ,int id) {
        Socialactivity  soc = socialactivityRepository.findOne(id);
        if (soc != null){
            soc.setName(editedSocialactivity.getName());
            soc.setDescription(editedSocialactivity.getDescription());
            socialactivityRepository.save(soc);
            return true;
        }
        return false;
    }

    @Override
    public Socialactivity createANewSocialactivity(Socialactivity newSocialactivity) {
        try {
            socialactivityRepository.save(newSocialactivity);
            return newSocialactivity;
        } catch (RuntimeException e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Socialactivity findOneByID(int id) {
        return socialactivityRepository.findOne(id);
    }
}

