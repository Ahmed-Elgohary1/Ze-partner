package com.Elgo.Zepartner.repository;

import com.Elgo.Zepartner.model.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository<Partner,Long> {

}
