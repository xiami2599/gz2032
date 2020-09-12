package dao;

import java.util.List;

import dao.entity.User;
import dto.Page;

public interface UserDao {

	public Page<User> getOnePage(int currentPage,int pageSize) ;
	public int save(User fq);
	public User getById(int id);
	public int update(User user);
	public int deleteById(int id) ;
	public User login(String name,String password );
	public List<User> findAll();
}
