package org.ishop.support;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import javax.ejb.PrePassivate;
import javax.ejb.PostActivate;
import javax.annotation.PreDestroy;
import javax.annotation.PostConstruct;
import javax.interceptor.InvocationContext;

/**
 * Spring context interceptor
 *
 * @author Alexander Burchak
 */
public class SpringContextInterceptor extends SpringBeanAutowiringInterceptor {
    private BeanFactory beanFactory;

    protected BeanFactory getBeanFactory(Object o) {
        if (beanFactory == null) {
            beanFactory = new ClassPathXmlApplicationContext("/applicationContext.xml").getAutowireCapableBeanFactory();
        }
        return beanFactory;
    }

    @PreDestroy
    @PrePassivate
    public void releaseBean(InvocationContext invocationContext) {
        super.releaseBean(invocationContext);
    }

    @PostConstruct
    @PostActivate
    public void autowireBean(InvocationContext invocationContext) {
        super.autowireBean(invocationContext);
    }
}
