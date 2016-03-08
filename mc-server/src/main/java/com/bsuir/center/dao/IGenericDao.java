package com.bsuir.center.dao;

import java.io.Serializable;
import java.util.List;

import com.bsuir.center.exception.DaoException;

public interface IGenericDao<Entity, PK extends Serializable> {

	public PK create(Entity entity) throws DaoException;

	public Entity read(PK entityId) throws DaoException;

	public List<Entity> readAll() throws DaoException;

	public void update(Entity entity) throws DaoException;

	public void delete(PK entityId) throws DaoException;
}
