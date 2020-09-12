package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import dao.entity.Car;
import dao.impl.UserDaoImpl;
import service.CarService;
import service.impl.CarServiceImpl;

public class CarServlet extends BaseServlet{
	
	CarService carService = new CarServiceImpl();
	public void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserDao user = new UserDaoImpl();
		String carName = request.getParameter("carName");
		System.out.println(carName);
		String picPath = request.getParameter("picPath");
		double price = Double.parseDouble(request.getParameter("price"));
		System.out.println(price);
		double totalPrice = Double.parseDouble(request.getParameter("TotalPrice"));
		int count =  Integer.parseInt(request.getParameter("count"));
		
		String IDSTR = request.getParameter("uid");
		
		int uid=0;
		if(IDSTR!=null) {
			 uid = Integer.parseInt(IDSTR);
		}
		
		
		Car car = new Car();
		car.setCarName(carName);
		car.setPicPath(picPath);
		car.setPrice(price);
		car.setTotalPrice(totalPrice);
		car.setCount(count);
		car.setUser(user.getById(uid));
		
		int row = carService.save(car);
		if (row>0) {
			//控制跳转
			System.out.println("添加购物车成功！");
			this.getByUid11(request, response);
//			response.sendRedirect("myCart.jsp");
		} else {
			System.out.println("添加购物车失败！");
			//response.sendRedirect("register.jsp");
		}
	}

	public void getByUid11(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		String idStr = request.getParameter("uid");
//		int uid = Integer.parseInt(idStr);
		
		
		List<Car> cars = carService.getByUid(1);
		HttpSession session = request.getSession();
		session.setAttribute("cars", cars);
		response.sendRedirect("myCart.jsp");
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idStr = request.getParameter("id");
		if (idStr==""||idStr==null) {
			idStr="0";
		}
		
		int id = Integer.parseInt(idStr);
		int row = carService.deleteById(id);
		HttpSession session = request.getSession();
		session.setAttribute("deleteSuccess","删除成功！");
		this.getByUid11(request, response);
//		response.sendRedirect("myCart.jsp");
		//转发
//		request.getRequestDispatcher("PageServlet?currentPage="+request.getParameter("currentPage")).forward(request, response);
		//response.sendRedirect("PageServlet");
	}
}
