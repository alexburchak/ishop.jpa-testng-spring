package org.ishop.support;

import org.jboss.annotation.ejb.LocalBinding;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;

import javax.ejb.Init;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.*;

/**
 * Callback implementation to support OpenSessionInView pattern. Have to keep it public for EJB
 *
 * @author Alexander Burchak
 */
@Stateful(name = "openSessionInViewCallback")
@LocalBinding(jndiBinding = "openSessionInViewCallbackLocal")
public class OpenSessionInViewCallbackImpl implements OpenSessionInViewCallback {
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    @PersistenceUnit(unitName = "ishopPU")
    private EntityManagerFactory entityManagerFactory;

    @Init
    public void invoke(Runnable r) {
        TransactionSynchronizationManager.bindResource(entityManagerFactory, new EntityManagerHolder(entityManager));

        r.run();
    }

    @Remove
    public void remove() {
        TransactionSynchronizationManager.unbindResource(entityManagerFactory);
        EntityManagerFactoryUtils.closeEntityManager(entityManager);
    }
}
