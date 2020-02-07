package com.oliver.test;

import com.oliver.MiaoshaApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.TimeZone;

/**
 * com.oliver.test.MiaoshaTestApplication
 *
 * @author oliver
 * @date 2019/12/26 16:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MiaoshaApplication.class})
public class MiaoshaTestApplication {


    @Test
    public void HttpTest() throws ParseException {
        String id = TimeZone.getDefault().toZoneId().getId();
        System.out.println(TimeZone.getDefault().toZoneId());
    }


    @Test
    public void files(){
        String filepath = "";

    }


}
