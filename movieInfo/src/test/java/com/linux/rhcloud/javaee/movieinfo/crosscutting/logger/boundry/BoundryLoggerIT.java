package com.linux.rhcloud.javaee.movieinfo.crosscutting.logger.boundry;

import java.lang.reflect.Method;
import javax.inject.Inject;
import javax.interceptor.InvocationContext;
import org.easymock.EasyMock;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;

/**
 * Integration Test using Arquilian.
 * @author guru
 */
@RunWith(Arquillian.class)
public class BoundryLoggerIT {
    
    @Deployment
    public static WebArchive create() {
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(BoundryLogger.class, LogSink.class, LogSinkProducer.class, LoggerFactory.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    BoundryLogger ut;
    
    @Test
    public void testInjectionWithArquillian() throws Exception{
        InvocationContext ic = createNiceMock(InvocationContext.class);
        Method m = Object.class.getMethod("equals", Object.class);
        expect(ic.getMethod()).andReturn(m).anyTimes();
        replay(ic);
        
        assertThat(ut.log, is(notNullValue()));
        ut.logManagerCalls(ic);
    }
}
