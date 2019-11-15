package com.jiangconglin.spring.bean.wiring;

import com.jiangconglin.spring.bean.wiring.bean.CompactDisc;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * JavaConfig Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Jul 7, 2019</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JavaConfigAutoWiring.class)
public class JavaConfigAotoWiringTest {

    @Autowired
    private CompactDisc disc;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void testConfigByContext()
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfigAutoWiring.class);
        CompactDisc disc = context.getBean(CompactDisc.class);
        disc.play();
    }

    @Test
    public void testConfigByTestWiring()
    {
        disc.play();
    }


} 
