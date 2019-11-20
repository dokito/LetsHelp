package com.dokito.letshelp.service.services.implementation;

import com.dokito.letshelp.service.services.HashingService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@Service
public class HashingServiceImpl implements HashingService {
    @Override
    public String hash(String password) {
        return DigestUtils.sha256Hex(password);
    }
}
