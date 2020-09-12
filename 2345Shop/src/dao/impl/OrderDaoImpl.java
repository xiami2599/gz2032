package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.OrderDao;
import dao.entity.Order;
import dto.Page;
import utils.JdbcUtil;

public class OrderDaoImpl implements OrderDao{

	@Override
	public Page<Order> getOnePage(int currentPage, int pageSize) {
		int count = this.getCount();
		List<Order> lists = this.getOnePageInfo(currentPage, pageSize);
		return new Page<Order>(currentPage, count, pageSize, lists);
	}

	@Override
	public int save(Order order) {
		String saveSql = "INSERT INTO `order` VALUE(?,?,?,?,?,?,?,?,?)";
		Object[] objects = {order.getId(),order.getOrderName(),order.getPrice(),order.getClientName(),
							order.getAddress(),order.getOrderTime(),order.getPhone(),order.getCount(),order.getPicPath()};

		return JdbcUtil.executeQuery(saveSql, objects);
	}

	@Override
	public Order getById(int id) {
		Order order=null;
		Connection conn = null;
		// 3.得到Statement
		Statement state = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = JdbcUtil.getConnection();
			state = conn.createStatement();
			
			String getByIdSql = "SELECT* FROM `order` WHERE id = "+ id;
			rs = state.executeQuery(getByIdSql);
			// 5.处理结果集
			// 每一次循环就加载一条记录，一条记录对应一个对象
			if (rs.next()) {
				order = new Order();
				// 属性对象字段，属性类型对字段类型
				order.setId(rs.getInt(1));
				order.setOrderName(rs.getString(2));
				order.setPrice(rs.getDouble(3));
				order.setClientName(rs.getString(4));
				order.setAddress(rs.getString(5));
				order.setOrderTime(rs.getDate(6));
				order.setPhone(rs.getString(7));
				order.setCount(rs.getInt(8));
				order.setPicPath(rs.getString(9));
				

			}
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, state, conn);

		}
		return order;
	}

	@Override
	public int deleteById(int id) {
		int rows = -1;
		Connection conn = null;
		// 3.得到Statement
		Statement state = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = JdbcUtil.getConnection();
			state = conn.createStatement();
			//DELETE FROM `user` WHERE id = 1
			String deleteSql = "DELETE FROM `order` WHERE id = "+id;
			rows = state.executeUpdate(deleteSql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
			JdbcUtil.closeAll(rs, state, conn);
		}

		return rows;
	}
	private List<Order> getOnePageInfo(int currentPage,int pageSize) {
		List<Order> pages = new ArrayList<Order>();
		Connection conn = null;
		// 3.得到Statement
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			String pageSql = "SELECT * FROM `order`  LIMIT ?,?";
			ps = conn.prepareStatement(pageSql);
			ps.setObject(1, (currentPage-1)*pageSize);
			ps.setObject(2, pageSize);
			rs = ps.executeQuery();
			// 5.处理结果集
			// 每一次循环就加载一条记录，一条记录对应一个对象
			while (rs.next()) {
				Order order = new Order();
				// 属性对象字段，属性类型对字段类型
				order.setId(rs.getInt(1));
				order.setOrderName(rs.getString(2));
				order.setPrice(rs.getDouble(3));
				order.setClientName(rs.getString(4));
				order.setAddress(rs.getString(5));
				order.setOrderTime(rs.getDate(6));
				order.setPhone(rs.getString(7));
				order.setCount(rs.getInt(8));
				order.setPicPath(rs.getString(9));
				
				pages.add(order);
				
			}
		} catch ( SQLException e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}
		return pages;
	}
	
	private int getCount() {
		int count = 0;
		Connection conn = null;
		// 3.得到Statement
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			//列的别名
			String countSql = "SELECT COUNT(1) AS order_count FROM `order`";
			ps = conn.prepareStatement(countSql);
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt("order_count");
			}
		}  catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}
		return count;
	}

}
