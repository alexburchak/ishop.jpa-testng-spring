package org.ishop.support;

import javax.ejb.Local;
import javax.persistence.EntityManagerFactory;

/**
 * Locator for {@link EntityManagerFactory}
 *
 * @author Alexander Burchak
 */
@Local
public interface EntityManagerFactoryLocator {
    EntityManagerFactory getEntityManagerFactory();
}
