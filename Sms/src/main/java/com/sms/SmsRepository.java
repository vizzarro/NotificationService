package com.sms;

import com.sms.model.Sms;
import org.springframework.data.repository.CrudRepository;

public interface SmsRepository extends CrudRepository<Sms,Integer> { }