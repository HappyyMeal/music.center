package com.bsuir.client.service;

import com.bsuir.center.domain.User;

public interface IUserService extends IGenericService<User, String> {

	public boolean isMatchable(String uiPassword, String dbPassword);

}
