package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JdbcUtil {
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		Connection conn = null;
		Context cont = null;
		try {
			cont = new InitialContext();
			DataSource data = (DataSource) cont.lookup("java:comp/env/jndi/2345shop");
			conn = data.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void closeAll(ResultSet rs, Statement state, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (state != null) {
			try {
				state.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static int executeQuery(String sql,Object...objects) {
		int rows = -1;
		Connection conn = null;
		// 3.寰楀埌Statement
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			if (objects!=null) {
				for (int i = 0; i < objects.length; i++) {
					ps.setObject((i+1), objects[i]);
				}
			}
			rows = ps.executeUpdate();
		}  catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}

		return rows;
	}
}
