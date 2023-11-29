package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.iuh.fit.backend.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
    boolean existsByEmail(String email);

    Customer findByEmail(String email);


}