package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OrderDao;
import dao.entity.Car;
import dao.entity.GoodChoise;
import dao.entity.Order;
import dao.entity.User;
import dao.impl.OrderDaoImpl;
import dto.Page;
import service.CarService;
import service.impl.CarServiceImpl;

public class OrderServlet extends BaseServlet{
	
	OrderDao od = new OrderDaoImpl();
	
	public void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CarService cs = new CarServiceImpl();
		
		int row=0;
		List<Order> orderList = new ArrayList<>();
		
		String clientName = request.getParameter("clientName");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		Date orderTime = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String orderTime1 = sf.format(orderTime);
		
		HttpSession session = request.getSession();
		session.setAttribute("clientName", clientName);
		session.setAttribute("address", address);
		session.setAttribute("phone", phone);
		session.setAttribute("orderTime", orderTime1);
		
		System.out.println(clientName);
		System.out.println(phone);
		System.out.println(orderTime);
		
		List<Car> listCar = cs.getByUid(1);
		
		for (Car car : listCar) {
			
			Order order = new Order();
			
			order.setOrderName(car.getCarName());
			order.setPrice(car.getPrice());
			order.setClientName(clientName);
			order.setAddress(address);
			order.setOrderTime(orderTime);
			order.setPhone(phone);
			order.setCount(car.getCount());
			order.setPicPath(car.getPicPath());
			
			row=od.save(order);
			
		}
		if (row>0) {
			//控制跳转
			System.out.println("添加订单成功！");
			
			this.page(request, response);
//			response.sendRedirect("myCart.jsp");
		} else {
			System.out.println("添加订单失败！");
			//response.sendRedirect("register.jsp");
		}
	}

	public void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currentPageStr = request.getParameter("ordercurrentPage");//前端传过来的当前页码
		if (currentPageStr==""||currentPageStr==null) {
			currentPageStr = "1";//如果没输入任何页码，默认的第一页
		}
		int cgcurrentPage = Integer.parseInt(currentPageStr);
		int cgpageSize = 30;//每页5条
		Page<Order> orderPage = od.getOnePage(cgcurrentPage, cgpageSize);
		
		List<Order> lists = orderPage.getLists();
		
		double totalPrice=0;
		for (Order order : lists) {
			totalPrice=totalPrice+order.getPrice()*order.getCount();
		}
		DecimalFormat df = new DecimalFormat("#.00");
		String totalPrice1 = df.format(totalPrice);
		
		HttpSession session = request.getSession();
		session.setAttribute("orderPage", orderPage);
		session.setAttribute("totalPrice", totalPrice1);
		response.sendRedirect("order.jsp");//每页
	}
	public void pageManager(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currentPageStr = request.getParameter("orderMancurrentPage");//前端传过来的当前页码
		if (currentPageStr==""||currentPageStr==null) {
			currentPageStr = "1";//如果没输入任何页码，默认的第一页
		}
		int orderMancurrentPage = Integer.parseInt(currentPageStr);
		int cgpageSize = 5;//每页5条
		Page<Order> orderManPage = od.getOnePage(orderMancurrentPage, cgpageSize);
		
		HttpSession session = request.getSession();
		session.setAttribute("orderManPage", orderManPage);
		response.sendRedirect("managerOrder.jsp");//每页
	}
		
	
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		if (idStr==""||idStr==null) {
			idStr="0";
		}
		int id = Integer.parseInt(idStr);
		int row = od.deleteById(id);
		HttpSession session = request.getSession();
		session.setAttribute("deleteSuccess","删除成功！");
		this.page(request, response);
}
	public void deleteMan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		if (idStr==""||idStr==null) {
			idStr="0";
		}
		int id = Integer.parseInt(idStr);
		int row = od.deleteById(id);
		HttpSession session = request.getSession();
		session.setAttribute("deleteSuccess","删除成功！");
		this.pageManager(request, response);
	}

}
