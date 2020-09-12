package service.impl;

import dao.UserDao;
import dao.entity.User;
import dao.impl.UserDaoImpl;
import dto.Page;
import service.UserService;

public class UserServiceImpl implements UserService{
	
	UserDao fqDao = new UserDaoImpl();
	@Override
	public Page<User> getOnePage(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return fqDao.getOnePage(currentPage, pageSize);
	}

	@Override
	public int save(User fq) {
		// TODO Auto-generated method stub
		return fqDao.save(fq);
	}

	@Override
	public User getById(int id) {
		// TODO Auto-generated method stub
		return fqDao.getById(id);
	}

	@Override
	public int update(User fq) {
		// TODO Auto-generated method stub
		return fqDao.update(fq);
	}

	@Override
	public int deleteById(int id) {
		// TODO Auto-generated method stub
		return fqDao.deleteById(id);
	}

	@Override
	public User login(String name, String password) {
		// TODO Auto-generated method stub
		return fqDao.login(name, password);
	}

}
