package org.ishop.support;

import org.jboss.annotation.ejb.LocalBinding;

import javax.ejb.Stateless;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * {@link EntityManagerFactory} Locator implementation
 *
 * @author Alexander Burchak
 */
@Stateless(name = "entityManagerFactoryLocator")
@LocalBinding(jndiBinding = "entityManagerFactoryLocatorLocal")
public class EntityManagerFactoryLocatorImpl implements EntityManagerFactoryLocator {
    @PersistenceUnit(unitName = "ishopPU")
    private EntityManagerFactory entityManagerFactory;

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
