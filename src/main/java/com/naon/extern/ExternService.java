package com.naon.extern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yannishin
 */
@Service
public class ExternService {
    @Autowired(required = false)
    ExternRepository sampleeRepository;
}
