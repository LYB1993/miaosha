package com.oliver.test;

import com.oliver.MiaoshaApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void HttpTest() {
        System.out.println("");
    }


}
