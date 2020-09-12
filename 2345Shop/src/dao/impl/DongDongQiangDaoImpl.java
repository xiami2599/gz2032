package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import utils.JdbcUtil;

import dao.DongDongQiangDao;
import dao.entity.DongDongQiang;
import dto.Page;

public class DongDongQiangDaoImpl implements DongDongQiangDao {

	@Override
	public Page<DongDongQiang> getOnePage(int currentPage, int pageSize) {
		int count = this.getCount();
		List<DongDongQiang> lists = this.getOnePageInfo(currentPage, pageSize);
		return new Page<DongDongQiang>(currentPage, count, pageSize, lists);
	}

	@Override
	public int save(DongDongQiang ddq) {
		String saveSql = "INSERT INTO `dongdongqiang` VALUE(?,?,?,?,?,?)";
		Object[] objects = {ddq.getId(),ddq.getTitle(),ddq.getPicpath(),ddq.getDescri(),ddq.getPrice(),ddq.getPririce()};

		return JdbcUtil.executeQuery(saveSql, objects);
	}

	@Override
	public DongDongQiang getById(int id) {
		DongDongQiang ddq=null;
		Connection conn = null;
		// 3.得到Statement
		Statement state = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = JdbcUtil.getConnection();
			state = conn.createStatement();
			
			String getByIdSql = "SELECT id,title,pic_path,descri,price,pririce FROM `dongdongqiang` WHERE id = "+ id;
			rs = state.executeQuery(getByIdSql);
			// 5.处理结果集
			// 每一次循环就加载一条记录，一条记录对应一个对象
			if (rs.next()) {
				 ddq= new DongDongQiang();
				// 属性对象字段，属性类型对字段类型
				ddq.setId(rs.getInt("id"));
				ddq.setTitle(rs.getString("title"));
				ddq.setPicpath(rs.getString("pic_path"));
				ddq.setDescri(rs.getString("descri"));
				ddq.setPrice(rs.getDouble("price"));
				ddq.setPririce(rs.getDouble("pririce"));
				

			}
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, state, conn);

		}
		return ddq;
	}

	@Override
	public int update(DongDongQiang ddq) {
		String sql = "UPDATE dongdongqiang SET title = ?,pic_path=?,descri=?,price =?,pririce=? WHERE  id = ? ";
		Object[] objects = {ddq.getTitle(),ddq.getPicpath(),ddq.getDescri(),ddq.getPrice(),ddq.getPririce(),ddq.getId()};
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
			String deleteSql = "DELETE FROM dongdongqiang WHERE id = "+id;
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

	private List<DongDongQiang> getOnePageInfo(int currentPage,int pageSize) {
		List<DongDongQiang> pages = new ArrayList<DongDongQiang>();
		Connection conn = null;
		// 3.得到Statement
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			String pageSql = "SELECT * FROM `dongdongqiang`  LIMIT ?,?";
			ps = conn.prepareStatement(pageSql);
			ps.setObject(1, (currentPage-1)*pageSize);
			ps.setObject(2, pageSize);
			rs = ps.executeQuery();
			// 5.处理结果集
			// 每一次循环就加载一条记录，一条记录对应一个对象
			while (rs.next()) {
				DongDongQiang hd = new DongDongQiang();
				// 属性对象字段，属性类型对字段类型
				hd.setId(rs.getInt("id"));
				hd.setPicpath(rs.getString("pic_path"));
				hd.setTitle(rs.getString("title"));
				hd.setDescri(rs.getString("descri"));
				hd.setPrice(rs.getDouble("price"));
				hd.setPririce(rs.getDouble("pririce"));
				
				pages.add(hd);
				
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
			String countSql = "SELECT COUNT(1) AS dongdongqiang_count FROM `dongdongqiang`";
			ps = conn.prepareStatement(countSql);
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt("dongdongqiang_count");
			}
		}  catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}
		return count;
	}
	
}
