package org.ishop.support;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * OpenSessionInView interceptor
 *
 * @author Alexander Burchak
 */
public class OpenSessionInViewInterceptor {
    @AroundInvoke
    public Object around(final InvocationContext invocationContext) throws Exception {
        final Object result[] = new Object[2];
        OpenSessionInViewTemplate.invoke(new Runnable() {
            public void run() {
                try {
                    result[0] = invocationContext.proceed();
                } catch (Exception e) {
                    result[1] = e;
                }
            }
        });
        if (result[0] != null) {
            return result[0];
        } else {
            throw (Exception) result[1];
        }
    }
}
