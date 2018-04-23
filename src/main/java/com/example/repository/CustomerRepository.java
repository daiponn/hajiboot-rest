package com.example.repository;

import com.example.domain.Customer;
import com.example.domain.CustomerFirstname;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("SELECT x FROM Customer x ORDER BY x.firstName, x.lastName")
    List<Customer> findAllOrderByName();

//    <T> T findOneById(Integer id, Class<T> type);

    @Query("select new com.example.domain.CustomerFirstname(x.firstName) from Customer x where x.id = :id")
    CustomerFirstname findOneforFirstname(@Param("id") Integer id);

}
