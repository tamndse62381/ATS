package com.ats.repository.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.ats.repository.GenericDao;

@Repository
public class GenericDaoImpl<T,ID extends Serializable> implements GenericDao<T, ID> {
	/**
	 * Entity Manager
	 * 
	 */
	@PersistenceContext
	protected EntityManager entitymanager;

	/**
	 **
	 * @return the entitymanager
	 */
	public final EntityManager getEntitymanager() {
		return entitymanager;
	}

	/**
	 * @param entitymanager
	 *            the entitymanager to set
	 */
	public final void setEntitymanager(EntityManager entitymanager) {
		this.entitymanager = entitymanager;
	}

	protected Class<T> entityClass;

	/**
	* 
	*/
	public GenericDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	/**
	* 
	*/
	public GenericDaoImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	@Override
	public T create(T t) {
		this.entitymanager.persist(t);
		return t;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gcs.rms.repository.GenericDao#read(java.lang.Object)
	 */
	@Override
	public T read(ID id) {
		return this.entitymanager.find(entityClass, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gcs.rms.repository.GenericDao#getAll()
	 */
	@Override
	public List<T> getAll() {
		List<T> list = this.entitymanager.createQuery("from " + this.entityClass.getName()).getResultList();
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gcs.rms.repository.GenericDao#update(java.lang.Object)
	 */
	@Override
	public T update(T t) {
		return this.entitymanager.merge(t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gcs.rms.repository.GenericDao#delete(java.lang.Object)
	 */
	@Override
	public void delete(T t) {
		// TODO Auto-generated method stub
		this.entitymanager.remove(t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gcs.rms.repository.GenericDao#exists(java.lang.Object)
	 */
	@Override
	public boolean exists(ID id) {
		// TODO Auto-generated method stub
		T entity = this.entitymanager.find(entityClass, id);

		return entity != null;
	}
}
