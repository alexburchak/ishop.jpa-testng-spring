package org.ishop.support;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ejb.EJBException;

/**
 * Template to support OpenSessionInView pattern
 *
 * @author Alexander Burchak
 */
public final class OpenSessionInViewTemplate {
    public static void invoke(Runnable r) {
        OpenSessionInViewCallback callback = null;
        try {
            // make lookup for callback bean
            Context context = new InitialContext();
            callback = (OpenSessionInViewCallback) context.lookup("openSessionInViewCallbackLocal");

            callback.invoke(r);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        } finally {
            if (callback != null) {
                try {
                    // remove bean
                    callback.remove();
                } catch (EJBException t) {
                    // suppress "reference is invalid" for the case invoke() terminates with non-application exception
                }
            }
        }
    }
}
