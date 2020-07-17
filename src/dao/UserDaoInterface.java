package dao;

import java.io.IOException;

import model.User;

public interface UserDaoInterface {
	int signUp(User user) throws ClassNotFoundException, IOException;
	boolean loginUser(User user) throws ClassNotFoundException, IOException;
}
