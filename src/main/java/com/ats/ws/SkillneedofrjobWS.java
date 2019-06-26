package com.ats.ws;


import com.ats.dto.SkillNeedForJobDTO;
import com.ats.entity.Skill;
import com.ats.service.SkillNeedForJobService;
import com.ats.service.SkillService;
import com.ats.util.RestResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value ="/skillneedforjob")
public class SkillneedofrjobWS {
    @Autowired
    SkillService skillService;
    @Autowired
    SkillNeedForJobService skillNeedForJobService;

    private static final Logger LOGGER = LogManager.getLogger(SkillneedofrjobWS.class);

    @ResponseBody
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/addNewSkill")
    public RestResponse createSkill(@RequestBody SkillNeedForJobDTO dto) {
        LOGGER.info("Begin createSkill in SkillneedofrjobWS with Job id : {}" + dto.getJobid());

        List<Integer> listSkillId = new ArrayList<>();
        try {
            for (int i = 0; i < dto.getListSkill().size(); i++) {
                listSkillId.add(skillService.addNewSkill(dto.getListSkill().get(i)));
            }
            LOGGER.info("End createSkill in SkillneedofrjobWS with Job id : {}" + dto.getJobid());
            boolean finish = skillNeedForJobService.addSkillForJob(listSkillId, dto.getJobid());
            if (finish) {
                return new RestResponse(true, "Create New Skill Successfull", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "Fail To Create New Skill ", null);
    }
}
