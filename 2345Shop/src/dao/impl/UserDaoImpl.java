package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.UserDao;
import dao.entity.User;
import dto.Page;
import utils.JdbcUtil;

public class UserDaoImpl implements UserDao{
	
	@Override
	public Page<User> getOnePage(int currentPage, int pageSize) {
		int count = this.getCount();
		List<User> lists = this.getOnePageInfo(currentPage, pageSize);
		return new Page<User>(currentPage, count, pageSize, lists);
	}

	@Override
	public int save(User user) {
		String saveSql = "INSERT INTO `user` VALUE(?,?,?,?,?,?,?,?)";
		Object[] objects = {user.getId(),user.getLoginName(),user.getPassword(),user.getSex(),user.getBirthday(),user.getAddress(),user.getPhone(),user.getRegisterTime()};

		int row=0;
		List<User> list = this.findAll();
		
	
		for (User user2 : list) {
			
			if (user2.getLoginName().equals(user.getLoginName())) {
				
				return -1;
			} else {

				row=1;
			}
		}
		if(row>0) {
			
			JdbcUtil.executeQuery(saveSql, objects);
		}
		
		return row;
	}

	@Override
	public User getById(int id) {
		User fq=null;
		Connection conn = null;
		// 3.得到Statement
		Statement state = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = JdbcUtil.getConnection();
			state = conn.createStatement();
			
			String getByIdSql = "SELECT id,loginName,password,sex,birthday,address,phone,registerTime FROM `user` WHERE id = "+ id;
			rs = state.executeQuery(getByIdSql);
			// 5.处理结果集
			// 每一次循环就加载一条记录，一条记录对应一个对象
			if (rs.next()) {
				 fq= new User();
				// 属性对象字段，属性类型对字段类型
				fq.setId(rs.getInt("id"));
				fq.setLoginName(rs.getString("loginName"));
				fq.setPassword(rs.getString("password"));
				fq.setSex(rs.getString("sex"));
				fq.setBirthday(rs.getDate("birthday"));
				fq.setAddress(rs.getString("address"));
				fq.setPhone(rs.getString("phone"));
				fq.setRegisterTime(rs.getDate("registerTime"));
				

			}
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, state, conn);

		}
		return fq;
	}

	@Override
	public int update(User user) {
		String sql = "UPDATE user SET loginName = ?,password=?,sex=?,birthday =?,address=?,phone=?,registerTime=? WHERE  id = ? ";
		Object[] objects = {user.getLoginName(),user.getPassword(),user.getSex(),user.getBirthday(),user.getAddress(),user.getPhone(),user.getRegisterTime(),user.getId()};
		return JdbcUtil.executeQuery(sql, objects);
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
			String deleteSql = "DELETE FROM user WHERE id = "+id;
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

	private List<User> getOnePageInfo(int currentPage,int pageSize) {
		List<User> pages = new ArrayList<User>();
		Connection conn = null;
		// 3.得到Statement
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			String pageSql = "SELECT * FROM `user`  LIMIT ?,?";
			ps = conn.prepareStatement(pageSql);
			ps.setObject(1, (currentPage-1)*pageSize);
			ps.setObject(2, pageSize);
			rs = ps.executeQuery();
			// 5.处理结果集
			// 每一次循环就加载一条记录，一条记录对应一个对象
			while (rs.next()) {
				User fq = new User();
				// 属性对象字段，属性类型对字段类型
				fq.setId(rs.getInt("id"));
				fq.setLoginName(rs.getString("loginName"));
				fq.setPassword(rs.getString("password"));
				fq.setSex(rs.getString("sex"));
				fq.setBirthday(rs.getDate("birthday"));
				fq.setAddress(rs.getString("address"));
				fq.setPhone(rs.getString("phone"));
				fq.setRegisterTime(rs.getTimestamp("registerTime"));
				
				
				pages.add(fq);
				
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
			String countSql = "SELECT COUNT(1) AS user_count FROM `user`";
			ps = conn.prepareStatement(countSql);
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt("user_count");
			}
		}  catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}
		return count;
	}

	@Override
	public User login(String name, String password) {
		User user = null;
		
		Connection conn = null;
		// 3.得到Statement
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			
			conn = JdbcUtil.getConnection();
			String loginSql = "SELECT id,loginName,`password`,sex,birthday,address,phone,registerTime FROM `user` WHERE loginName=? AND `password`=? ";
			ps = conn.prepareStatement(loginSql);
			ps.setObject(1, name);
			ps.setObject(2, password);
			// 4.使用Statement对象发送sql语句到数据库中执行，返回结果集
			
			rs = ps.executeQuery();
			// 5.处理结果集
			// 每一次循环就加载一条记录，一条记录对应一个对象
			if (rs.next()) {
				user = new User();
				// 属性对象字段，属性类型对字段类型
				user.setId(rs.getInt("id"));
				user.setLoginName(rs.getString("loginName"));
				user.setPassword(rs.getString("password"));
				user.setSex(rs.getString("sex"));
				user.setBirthday(rs.getDate("birthday"));
				user.setAddress(rs.getString("address"));
				user.setPhone(rs.getString("phone"));
				user.setRegisterTime(rs.getTimestamp("registerTime"));

				
			}
		} catch ( SQLException e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);

		}
		return user;
	}

	@Override
	public List<User> findAll() {
		List<User> users = new ArrayList<User>();
		
		Connection conn = null;
		// 3.得到Statement
		Statement state = null;
		ResultSet rs = null;
		try {
			
			conn = JdbcUtil.getConnection();
			state = conn.createStatement();
			
			String allSql = "SELECT * FROM `user`";
			rs = state.executeQuery(allSql);
			// 5.处理结果集
			// 每一次循环就加载一条记录，一条记录对应一个对象
			while (rs.next()) {
				User user = new User();
				// 属性对象字段，属性类型对字段类型
				user.setId(rs.getInt("id"));
				user.setLoginName(rs.getString("loginName"));
				user.setPassword(rs.getString("password"));
				user.setSex(rs.getString("sex"));
				user.setBirthday(rs.getDate("birthday"));
				user.setAddress(rs.getString("address"));
				user.setPhone(rs.getString("phone"));
				user.setRegisterTime(rs.getTimestamp("registerTime"));
				users.add(user);
				// System.out.println(id +"\t"+name+"\t"+password);
			}
		} catch ( SQLException e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, state, conn);
		}

		return users;
	}


}
