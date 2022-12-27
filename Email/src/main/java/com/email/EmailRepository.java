package com.email;

import com.email.model.Email;
import org.springframework.data.repository.CrudRepository;

public interface EmailRepository  extends CrudRepository<Email,Integer> { }