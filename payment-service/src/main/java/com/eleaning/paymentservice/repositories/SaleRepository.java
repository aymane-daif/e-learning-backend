package com.eleaning.paymentservice.repositories;


import com.eleaning.paymentservice.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SaleRepository extends JpaRepository<Sale, String> {
    List<Sale> findAllByUserId(String userId);

}
