package com.ddd.domain.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ResidentDomainService {
    public String nextId() {
        return UUID.randomUUID().toString();
    }
}
