package com.enigma.enigmaboot.specification;

import com.enigma.enigmaboot.dto.CustomerSearchDTO;
import com.enigma.enigmaboot.entity.Customer;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Locale;

public class CustomerSpecification {
    //Menerima parameter dari class CustomerSearchDTO
    public static Specification<Customer> getSpecification(CustomerSearchDTO customerSearchDTO){
        return new Specification<Customer>() {
            @Override
            //Predicate merupakan sebuah metode, predicate berisi query untuk cek kondisi
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(!(customerSearchDTO.getSearchCustomerFirstName()==null)){
                    Predicate customerFirstNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" +customerSearchDTO.getSearchCustomerFirstName().toLowerCase(Locale.ROOT)+ "%");
                    predicates.add(customerFirstNamePredicate);
                }
                if(!(customerSearchDTO.getSearchCustomerLastName()==null)){
                    Predicate customerLastNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%" +customerSearchDTO.getSearchCustomerLastName().toLowerCase(Locale.ROOT)+ "%");
                    predicates.add(customerLastNamePredicate);
                }
                if(!(customerSearchDTO.getSearchCustomerDateOfBirth()==null)){
                    Predicate customerDateOfBirthPredicate = criteriaBuilder.like((root.get("dateOfBirth")), "%" +customerSearchDTO.getSearchCustomerDateOfBirth()+ "%");
                    predicates.add(customerDateOfBirthPredicate);
                }
                if (!(customerSearchDTO.getSearchCustomerDateOfBirth() == null)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    //Agar mengambil yyyy-MM-dd tanpa mengambil jamnya
                    String modifiedDate = sdf.format(customerSearchDTO.getSearchCustomerDateOfBirth());
                    Predicate createDatePredicate = criteriaBuilder.equal(criteriaBuilder.function(
                            "TO_CHAR", String.class, root.get("dateOfBirth"), criteriaBuilder.literal("yyyy-MM-dd")), modifiedDate);
                    predicates.add(createDatePredicate);

                }

                Predicate[] arrayPredicate = predicates.toArray(new Predicate[predicates.size()]);
                return criteriaBuilder.and(arrayPredicate);
            }
        };
    }
}
