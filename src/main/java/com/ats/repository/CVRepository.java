package com.ats.repository;

import org.springframework.data.domain.Page;
import com.ats.entity.Cv;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CVRepository extends JpaRepository<Cv, Integer> {
    @Query("Select c from Cv c where c.email = :email")
     Cv findCVByEmail (@Param("email") String email);

    @Query("select c from Cv c where c.userId = :userid and c.isActive = :status")
    List<Cv> checkIsActive(@Param("userid") Integer userid, @Param("status") Integer status);

    @Query("select c from Cv c where c.userId = :userid and c.isActive = :status and c.isActive = 1")
    List<Cv> checkActive(@Param("userid") Integer userid, @Param("status") Integer status);

    Optional<Cv> findById(int id);

    List<Cv> findByUserIdAndStatus(int id, String status);

    List<Cv> findByUserId(int id);


    @Query("select c from Cv  c " +
            "inner join c.skillincvsById s " +
            "inner join s.skillBySkillId a " +
            "inner join a.skillmasterBySkillMasterId m " +
            "inner join c.cityByCityId ci " +
            "inner join c.industryByIndustryId i where c.status = 1 " +
            "and ci.id = :city " +
            "and i.id = :industry " +
            "or m.skillName in (:skillstring) order by c.lastModify desc")
    Page<Cv> searchCv(@Param("skillstring") String skillstring, Pageable pageable,
                      @Param("city") int city, @Param("industry") int industry);
}
