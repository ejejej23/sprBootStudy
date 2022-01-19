package com.naon.study.beanscope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * @author yannishin
 */
@Component
@Scope("prototype")
//@Scope(value="prototype" , proxyMode = ScopedProxyMode.TARGET_CLASS) // proto by single method 1
public class Proto {
//    @Autowired
//    Single single;
}
