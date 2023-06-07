package com.fss.card.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fss.card.entity.TransactionHistory;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionHistory, Long>{

}
