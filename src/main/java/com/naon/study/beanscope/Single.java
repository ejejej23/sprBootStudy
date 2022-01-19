package com.naon.study.beanscope;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yannishin
 */
@Component
public class Single {
    @Autowired
    Proto proto;

    public Proto getProto() {
        return proto;
    }

    // proto by single method 1
//    @Autowired
//    private ObjectProvider<Proto> proto;
//
//    public Proto getProto() {
//        return proto.getIfAvailable();
//    }
}
