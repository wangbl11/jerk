package com.kongtiantou.jerk.repository;

import com.kongtiantou.jerk.domain.Jerk;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Jerk entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JerkRepository extends JpaRepository<Jerk, Long> {

}
