package com.ats.service;

import com.ats.entity.Socialactivities;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SocialactivityService {
    public List<Socialactivities> findListSocialactivityByCVID(int id);

    public boolean editASocialactivity(Socialactivities editedSocialactivity,int id);

    public Socialactivities createANewSocialactivity(Socialactivities newSocialactivity);

    public Socialactivities findOneByID(int id);

    public void deleteOneByID(int id);
}
