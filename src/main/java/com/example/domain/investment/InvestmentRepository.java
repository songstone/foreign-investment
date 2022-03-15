package com.example.domain.investment;

import com.example.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {

    List<Investment> findAllByUser(User user);
}
