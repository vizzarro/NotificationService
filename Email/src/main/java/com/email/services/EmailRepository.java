package com.email.services;

import com.email.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository  extends JpaRepository<Email,Integer> { }