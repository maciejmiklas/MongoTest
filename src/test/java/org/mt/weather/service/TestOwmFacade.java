package org.mt.weather.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mt.weather.config.MtConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MtConfiguration.class })
public class TestOwmFacade {

    @Test
    public void testGetForecast() {
	int a = -9971;
	System.out.println(9971 & a);
    }
}
