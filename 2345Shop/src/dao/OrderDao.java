package dao;

import dao.entity.Order;
import dao.entity.User;
import dto.Page;

public interface OrderDao {

	public Page<Order> getOnePage(int currentPage,int pageSize) ;
	public int save(Order order);
	public Order getById(int id);
	public int deleteById(int id) ;
}
