package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.FengQiangDao;
import dao.entity.FengQiang;
import dto.Page;
import utils.JdbcUtil;

public class FengQiangDaoImpl implements FengQiangDao{

	@Override
	public Page<FengQiang> getOnePage(int currentPage, int pageSize) {
		int count = this.getCount();
		List<FengQiang> lists = this.getOnePageInfo(currentPage, pageSize);
		return new Page<FengQiang>(currentPage, count, pageSize, lists);
	}

	@Override
	public int save(FengQiang fq) {
		String saveSql = "INSERT INTO `fengqiang` VALUE(?,?,?,?,?,?,?,?)";
		Object[] objects = {fq.getId(),fq.getTitle(),fq.getPicpath(),fq.getDescri(),fq.getPrice(),fq.getPririce(),fq.getYouhui(),fq.getXiaoliang()};

		return JdbcUtil.executeQuery(saveSql, objects);
	}

	@Override
	public FengQiang getById(int id) {
		FengQiang fq=null;
		Connection conn = null;
		// 3.得到Statement
		Statement state = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = JdbcUtil.getConnection();
			state = conn.createStatement();
			
			String getByIdSql = "SELECT * FROM `fengqiang` WHERE id = "+ id;
			rs = state.executeQuery(getByIdSql);
			// 5.处理结果集
			// 每一次循环就加载一条记录，一条记录对应一个对象
			if (rs.next()) {
				 fq= new FengQiang();
				// 属性对象字段，属性类型对字段类型
				fq.setId(rs.getInt("id"));
				fq.setTitle(rs.getString("title"));
				fq.setPicpath(rs.getString("pic_path"));
				fq.setDescri(rs.getString("descri"));
				fq.setPrice(rs.getDouble("price"));
				fq.setPririce(rs.getDouble("pririce"));
				fq.setYouhui(rs.getInt("youhui"));
				fq.setXiaoliang(rs.getInt("xiaoliang"));
				

			}
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, state, conn);

		}
		return fq;
	}

	@Override
	public int update(FengQiang fq) {
		String sql = "UPDATE fengqiang SET title = ?,pic_path=?,descri=?,price =?,pririce=?,youhui=?,xiaoliang=? WHERE  id = ? ";
		Object[] objects = {fq.getTitle(),fq.getPicpath(),fq.getDescri(),fq.getPrice(),fq.getPririce(),fq.getYouhui(),fq.getXiaoliang(),fq.getId()};
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
			String deleteSql = "DELETE FROM fengqiang WHERE id = "+id;
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

	private List<FengQiang> getOnePageInfo(int currentPage,int pageSize) {
		List<FengQiang> pages = new ArrayList<FengQiang>();
		Connection conn = null;
		// 3.得到Statement
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			String pageSql = "SELECT * FROM `fengqiang`  LIMIT ?,?";
			ps = conn.prepareStatement(pageSql);
			ps.setObject(1, (currentPage-1)*pageSize);
			ps.setObject(2, pageSize);
			rs = ps.executeQuery();
			// 5.处理结果集
			// 每一次循环就加载一条记录，一条记录对应一个对象
			while (rs.next()) {
				FengQiang hd = new FengQiang();
				// 属性对象字段，属性类型对字段类型
				hd.setId(rs.getInt("id"));
				hd.setPicpath(rs.getString("pic_path"));
				hd.setTitle(rs.getString("title"));
				hd.setDescri(rs.getString("descri"));
				hd.setPrice(rs.getDouble("price"));
				hd.setPririce(rs.getDouble("pririce"));
				hd.setYouhui(rs.getInt("youhui"));
				hd.setXiaoliang(rs.getInt("xiaoliang"));
				
				
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
			String countSql = "SELECT COUNT(1) AS fengqiang_count FROM `fengqiang`";
			ps = conn.prepareStatement(countSql);
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt("fengqiang_count");
			}
		}  catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}
		return count;
	}

}
