package com.nowcoder.SensitiveServiceTest;


import com.nowcoder.WendaApplication;
import com.nowcoder.service.SensitiveService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WendaApplication.class)
@WebAppConfiguration
public class TestSensitiveService {
    @Autowired
    SensitiveService sensitiveService;
    @Test
    public void Test(){
        String str="hi 色#情#片";
        sensitiveService.addWord("色情");
        String res=sensitiveService.filter(str);
        System.out.println(res);
    }
}
