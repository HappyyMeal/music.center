package com.bsuir.client.service;

import java.io.Serializable;
import java.util.List;

import com.bsuir.client.exception.ServiceException;

public interface IGenericService<Entity, PK extends Serializable> {

	public PK create(Entity entity) throws ServiceException;

	public Entity read(PK entityId) throws ServiceException;

	public List<Entity> readAll() throws ServiceException;

	public void update(Entity entity);

	public void delete(PK entityId);
}
