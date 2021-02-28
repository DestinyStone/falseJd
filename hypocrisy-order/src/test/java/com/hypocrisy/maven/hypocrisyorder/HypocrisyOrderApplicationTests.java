package com.hypocrisy.maven.hypocrisyorder;

import com.github.wujun234.uid.impl.CachedUidGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HypocrisyOrderApplicationTests {

    @Autowired
    private CachedUidGenerator cachedUidGenerator;

    @Test
    public void contextLoads() {
        long uid = cachedUidGenerator.getUID();
        System.out.println(uid);
    }

}
