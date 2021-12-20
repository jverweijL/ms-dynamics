package com.liferay.msdynamics.integration.rest.client.function;

import javax.annotation.Generated;

/**
 * @author Filipe Afonso
 * @generated
 */
@FunctionalInterface
@Generated("")
public interface UnsafeSupplier<T, E extends Throwable> {

	public T get() throws E;

}