package com.bridgelabz.atmsystem.repository;

import com.bridgelabz.atmsystem.entity.AtmEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Purpose : To implement atmRepo in atm-System program.
 *
 * @author : DAMINI CHANDRAKAR
 * @since : 03-12-2021
 */
public interface AtmRepo extends JpaRepository<AtmEntity, Integer> {
}
