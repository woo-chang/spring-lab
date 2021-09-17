package org.zerock.sample;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.config.RootConfig;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml") xml 이용하는 방법
@ContextConfiguration(classes = {RootConfig.class}) // Java 이용하는 방법
@Log4j
public class SampleTests {

    @Setter(onMethod_ = {@Autowired})
    private Restaurant restaurant;

    @Test
    public void testExist() {

        assertNotNull(restaurant);

        log.info(restaurant);
        log.info("-------------------------");
        log.info(restaurant.getChef());

    }
}
