package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.BaoYouDao;
import dao.entity.BaoYou;
import dao.entity.GoodChoise;
import dto.Page;
import utils.JdbcUtil;

public class BaoYouDaoImpl implements BaoYouDao {

	@Override
	public Page<BaoYou> getOnePage(int currentPage, int pageSize) {
		int count = this.getCount();
		List<BaoYou> lists = this.getOnePageInfo(currentPage, pageSize);
		return new Page<BaoYou>(currentPage, count, pageSize, lists);
	}

	@Override
	public int save(BaoYou fq) {
		String saveSql = "INSERT INTO `baoyou` VALUE(?,?,?,?,?,?,?,?)";
		Object[] objects = {fq.getId(),fq.getTitle(),fq.getPicpath(),fq.getPrice(),fq.getPrix(),fq.getDescribe(),fq.getDiscount(),fq.getSales()};

		return JdbcUtil.executeQuery(saveSql, objects);
	}

	@Override
	public BaoYou getById(int id) {
		BaoYou fq=null;
		Connection conn = null;
		// 3.得到Statement
		Statement state = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = JdbcUtil.getConnection();
			state = conn.createStatement();
			
			String getByIdSql = "SELECT * FROM `baoyou` WHERE id = "+ id;
			rs = state.executeQuery(getByIdSql);
			// 5.处理结果集
			// 每一次循环就加载一条记录，一条记录对应一个对象
			if (rs.next()) {
				 fq= new BaoYou();
				// 属性对象字段，属性类型对字段类型
				fq.setId(rs.getInt("id"));
				fq.setTitle(rs.getString("title"));
				fq.setPicpath(rs.getString("pic_path"));
				fq.setPrice(rs.getDouble("price"));
				fq.setPrix(rs.getDouble("prix"));
				fq.setDescribe(rs.getString("describe"));
				fq.setDiscount(rs.getInt("discount"));
				fq.setSales(rs.getInt("sales"));
				

			}
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, state, conn);

		}
		return fq;
	}

	@Override
	public int update(BaoYou fq) {
		String sql = "UPDATE baoyou SET title = ?,pic_path=?,price=?,prix =?,`describe`=?,discount=?,sales=? WHERE  id = ? ";
		Object[] objects = {fq.getTitle(),fq.getPicpath(),fq.getPrice(),fq.getPrix(),fq.getDescribe(),fq.getDiscount(),fq.getSales(),fq.getId()};
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
			String deleteSql = "DELETE FROM baoyou WHERE id = "+id;
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

	private List<BaoYou> getOnePageInfo(int currentPage,int pageSize) {
		List<BaoYou> pages = new ArrayList<BaoYou>();
		Connection conn = null;
		// 3.得到Statement
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			String pageSql = "SELECT * FROM `baoyou`  LIMIT ?,?";
			ps = conn.prepareStatement(pageSql);
			ps.setObject(1, (currentPage-1)*pageSize);
			ps.setObject(2, pageSize);
			rs = ps.executeQuery();
			// 5.处理结果集
			// 每一次循环就加载一条记录，一条记录对应一个对象
			while (rs.next()) {
				BaoYou hd = new BaoYou();
				// 属性对象字段，属性类型对字段类型
				hd.setId(rs.getInt("id"));
				hd.setPicpath(rs.getString("pic_path"));
				hd.setTitle(rs.getString("title"));
				hd.setPrice(rs.getDouble("price"));
				hd.setPrix(rs.getDouble("prix"));
				hd.setDescribe(rs.getString("describe"));
				hd.setDiscount(rs.getInt("discount"));
				hd.setSales(rs.getInt("sales"));
				
				
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
			String countSql = "SELECT COUNT(1) AS baoyou_count FROM `baoyou`";
			ps = conn.prepareStatement(countSql);
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt("baoyou_count");
			}
		}  catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}
		return count;
	}
}
