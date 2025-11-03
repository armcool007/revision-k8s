// src/main/java/com/bhim/bank/repository/CustomerRepository.java
package com.bhim.bank.repository;

import com.bhim.bank.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
}
