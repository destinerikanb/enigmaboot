package com.enigma.enigmaboot.repository;

import com.enigma.enigmaboot.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String>, JpaSpecificationExecutor {
//    Page<Customer> findAll(Specification<Customer> customerSpecification, Pageable pageable);
    //Memetakan parameter agar pencarian selanjutnya dapat dilakukan
    public List<Customer> findCustomersByFirstNameContainsIgnoreCaseAndLastNameContainingIgnoreCase(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
