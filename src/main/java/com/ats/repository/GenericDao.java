package com.ats.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface GenericDao<T, ID> {
	/**
	 * Create.
	 * 
	 * @param t
	 *            Entity Object
	 * @return Entity Object
	 */
	T create(T t);

	/**
	 * Read.
	 * 
	 * @param id
	 *            Primary key
	 * @return Entity Object
	 */
	T read(ID id);

	/**
	 * Generic method used to get all objects of a particular type. This is the same
	 * as lookup up all rows in a table.
	 * 
	 * @return List of populated objects
	 */
	List<T> getAll();

	/**
	 * Update.
	 * 
	 * @param t
	 *            Entity Object
	 * @return Entity Object
	 */
	T update(T t);

	/**
	 * Delete.
	 * 
	 * @param t
	 *            Entity Object
	 */
	void delete(T t);

	/**
	 * Checks for existence of an object of type T using the id arg.
	 * 
	 * @param id
	 *            the id of the entity
	 * @return - true if it exists, false if it doesn't
	 */
	boolean exists(ID id);
}
