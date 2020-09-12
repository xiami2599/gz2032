package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.CarDao;
import dao.UserDao;
import dao.entity.Car;
import utils.JdbcUtil;

public class CarDaoImpl implements CarDao{

	UserDao ud = new UserDaoImpl();
	@Override
	public int save(Car car) {
		String saveSql = "INSERT INTO `car` VALUE(?,?,?,?,?,?,?)";
		Object[] objects = {car.getId(),car.getCarName(),car.getPicPath(),car.getPrice(),car.getTotalPrice(),car.getCount(),car.getUser().getId()};

		return JdbcUtil.executeQuery(saveSql, objects);
	}

	@Override
	public List<Car> getByUid(int uid) {
		
		
		List<Car> cars = new ArrayList<Car>();
		
		Connection conn = null;
		// 3.得到Statement
		Statement state = null;
		ResultSet rs = null;
		try {
			
			conn = JdbcUtil.getConnection();
			state = conn.createStatement();
			
			String Sql = "SELECT*FROM car ";
			rs = state.executeQuery(Sql);
			// 5.处理结果集
			// 每一次循环就加载一条记录，一条记录对应一个对象
			while (rs.next()) {
				Car car = new Car();
				// 属性对象字段，属性类型对字段类型
				car.setId(rs.getInt("id"));
				car.setCarName(rs.getString("carname"));
				car.setPicPath(rs.getString("pic_path"));
				car.setPrice(rs.getDouble("price"));
				car.setTotalPrice(rs.getDouble("totalprice"));
				car.setCount(rs.getInt("count"));
				car.setUser(ud.getById(rs.getInt("uid")));
				cars.add(car);
				// System.out.println(id +"\t"+name+"\t"+password);
			}
		} catch ( SQLException e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, state, conn);
		}

		return cars;
	}

	@Override
	public Car getByid(int id) {
		Car car=null;
		Connection conn = null;
		// 3.得到Statement
		Statement state = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = JdbcUtil.getConnection();
			state = conn.createStatement();
			
			String getByIdSql = "SELECT * FROM `car` WHERE id = "+ id;
			rs = state.executeQuery(getByIdSql);
			// 5.处理结果集
			// 每一次循环就加载一条记录，一条记录对应一个对象
			if (rs.next()) {
				 car= new Car();
				// 属性对象字段，属性类型对字段类型
				 car.setId(rs.getInt("id"));
					car.setCarName(rs.getString("carname"));
					car.setPicPath(rs.getString("pic_path"));
					car.setPrice(rs.getDouble("price"));
					car.setTotalPrice(rs.getDouble("totalprice"));
					car.setCount(rs.getInt("count"));
					car.setUser(ud.getById(rs.getInt("uid")));
				

			}
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, state, conn);

		}
		return car;
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
			String deleteSql = "DELETE FROM car WHERE id = "+id;
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
	
	

}
