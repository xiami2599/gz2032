package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.entity.User;
import dto.Page;
import service.UserService;
import service.impl.UserServiceImpl;

public class UserServlet extends BaseServlet {

	UserService userService = new UserServiceImpl();
	public void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginName = null;
		String password = null;
		String sex = null;
		String birthdayStr = null;
		String address = null;
		String phone = null;
		String registerTimeStr = null;
		String onlyName = null;
		
		//判断是否以multipart/form-data方式提交
		boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
		if (isMultiPart) {
			//得到文件上传的对象
			FileItemFactory factory = new DiskFileItemFactory();
			//upload封装着表单的全部数据
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				//得到表单控件
				List<FileItem> items = upload.parseRequest(request);
				for (FileItem fileItem : items) {
					//判断是否是普通表单控件，还是文件上传控件
					boolean isFormFileld = fileItem.isFormField();
					if (isFormFileld) {//普通表单控件
						//得到普通表单控件的数据
						String inputName = fileItem.getFieldName();
						if (inputName.equals("loginName")) {
							loginName = fileItem.getString("utf-8");
							System.out.println(loginName);
						}else if (inputName.equals("password")) {
							password = fileItem.getString("utf-8");
							System.out.println(password);
						}else if (inputName.equals("registerTime")) {
							//registerTimeStr = fileItem.getString("utf-8");
							System.out.println(registerTimeStr);
						}else if (inputName.equals("sex")) {
							sex = fileItem.getString("utf-8");
							System.out.println(sex);
						}else if (inputName.equals("birthday")) {
							birthdayStr = fileItem.getString("utf-8");
							System.out.println(birthdayStr);
						}else if (inputName.equals("address")) {
							address = fileItem.getString("utf-8");
							System.out.println(address);
						}else if (inputName.equals("phone")) {
							phone = fileItem.getString("utf-8");
							System.out.println(phone);
						}
						
					} else {
						//文件上传控件 xxx.jpg  
						//得到文件名,能直接保存到数据库中吗？
						String fileName = fileItem.getName();
						//数据库中的文件名，和上传到服务器中的文件名应该是全球唯一
						//得到文件的扩展名  .jpg
						String extendsName =  fileName.substring(fileName.lastIndexOf("."), fileName.length());
						String uuid = UUID.randomUUID().toString();
						//重命名为全球唯一的文件名
						onlyName = uuid + extendsName;//保存到书库
						
						//得到服务器中的upload文件夹的路径： C:\\apache-tomcat-8.0.50\\webapps\\taobao5\\upload
						String serverUpLoadPath = this.getServletContext().getRealPath("upload");
						System.out.println(serverUpLoadPath);
						File file = new File(serverUpLoadPath, onlyName);
						try {
							fileItem.write(file);//文件上传
						} catch (Exception e) {
							
							e.printStackTrace();
						}
					}
				}
				
			} catch (FileUploadException e) {
				
				e.printStackTrace();
			}
			
		}
		//2.组织数据：封装数据到bean中
		User user = new User();
		user.setLoginName(loginName);
		user.setPassword(password);
		user.setSex(sex);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date birthday = null;
		Date registerTime = new Date();
		
		if(birthdayStr!=null) {
			
			try {
				birthday = df.parse(birthdayStr);//把字符串类型转换成日期类型
				//registerTime = df.parse(registerTimeStr);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		user.setBirthday(birthday);
		user.setAddress(address);
		user.setPhone(phone);
		user.setRegisterTime(registerTime);
		//3.调用逻辑层API
		int rows = userService.save(user);
		//4.做控制跳转。
		if (rows>0) {
			System.out.println("添加成功！！");
			HttpSession session = request.getSession() ;
			session.setAttribute("registersuccess", "恭喜你注册成功，快去登录吧！");
			
			response.sendRedirect("register.jsp");
			//this.userPage(request, response);
		} else {
			System.out.println("添加失败！！");
			HttpSession session = request.getSession() ;
			session.setAttribute("registersuccess", "用户名已存在，请重新输入！");
			
			response.sendRedirect("register.jsp");
		}
	}
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//从客户端传过来产生的乱码处理
				request.setCharacterEncoding("utf-8");
				//从服务器端传到客户端产生的乱码处理
				response.setCharacterEncoding("utf-8");
				//响应头的乱码处理
				response.setContentType("text/html; charset=UTF-8");		
				PrintWriter out = response.getWriter();
				String currentPage = request.getParameter("usercurrentPage");
				//1.收集数据
				String idStr = request.getParameter("id");
				
				//2.封装数据
				//3.调用逻辑层API
				//删除前提示：确定要删除该记录吗？
				int rows = userService.deleteById(Integer.parseInt(idStr));
				if (rows>0) {
					//在客户端中弹出删除成功消息框
//					System.out.println("删除成功！！");
					out.write("<script type=\"text/javascript\">");
						out.write("alert(\"删除成功！！\");");
						out.write("window.location.href=\"UserServlet?action=userPage&usercurrentPage="+currentPage+"\"");
					out.write("</script>");
					
					//response.sendRedirect("PageServlet");
				} else {
					//在客户端中弹出删除成功消息框
					//System.out.println("删除失败！！");
					out.write("<script type=\"text/javascript\">");
						out.write("alert(\"删除失败！！\");");
						out.write("window.location.href=\"UserServlet?action=userPage&usercurrentPage="+currentPage+"\"");
					out.write("</script>");
					//response.sendRedirect("PageServlet");
				}
				
	}
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oldPicPath = null;
		String idStr = null;
		String loginName = null;
		String password = null;
		String sex = null;
		String birthdayStr = null;
		String registerTimeStr = null;
		String address = null;
		String phone = null;
		String onlyName = null;
		
		//判断是否以multipart/form-data方式提交
		boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
		if (isMultiPart) {
			//得到文件上传的对象
			FileItemFactory factory = new DiskFileItemFactory();
			//upload封装着表单的全部数据
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				//得到表单控件
				List<FileItem> items = upload.parseRequest(request);
				for (FileItem fileItem : items) {
					//判断是否是普通表单控件，还是文件上传控件
					boolean isFormFileld = fileItem.isFormField();
					if (isFormFileld) {//普通表单控件
						//得到普通表单控件的数据
						String inputName = fileItem.getFieldName();
						
						if (inputName.equals("id")) {
							idStr = fileItem.getString("utf-8");
							System.out.println(idStr);
						}else if (inputName.equals("loginName")) {
							loginName = fileItem.getString("utf-8");
							System.out.println(loginName);
						}else if (inputName.equals("password")) {
							password = fileItem.getString("utf-8");
							System.out.println(password);
						}else if (inputName.equals("sex")) {
							sex = fileItem.getString("utf-8");
							System.out.println(sex);
						}else if (inputName.equals("birthday")) {
							birthdayStr = fileItem.getString("utf-8");
							System.out.println(birthdayStr);
						}else if (inputName.equals("address")) {
							address = fileItem.getString("utf-8");
							System.out.println(address);
						}else if (inputName.equals("phone")) {
							phone = fileItem.getString("utf-8");
							System.out.println(phone);
						}else if (inputName.equals("registerTime")) {
							registerTimeStr = fileItem.getString("utf-8");
							System.out.println(registerTimeStr);
						}
						
					} else {
//						2.连图片也修改。
//						1.把服务器中的图片删除
//						2.上传新的图片到服务器中
						//要么修改了图片
						
						//文件上传控件 xxx.jpg  
						//得到文件名,能直接保存到数据库中吗？
						String fileName = fileItem.getName();
						//得到文件名不为空时候，就是连图片一起修改
						if(!("".equals(fileName))) {
							//1.把服务器中的图片删除
							//得到服务器中的upload文件夹的路径： C:\\apache-tomcat-8.0.50\\webapps\\taobao5\\upload
							String serverUpLoadPath = this.getServletContext().getRealPath("upload");
							//得到旧图片路径
							File deleteFile = new File(serverUpLoadPath, oldPicPath);
							deleteFile.delete();
							
							//2.上传新的图片到服务器中
							//数据库中的文件名，和上传到服务器中的文件名应该是全球唯一
							//得到文件的扩展名  .jpg
							String extendsName =  fileName.substring(fileName.lastIndexOf("."), fileName.length());
							String uuid = UUID.randomUUID().toString();
							//重命名为全球唯一的文件名
							onlyName = uuid + extendsName;//保存到书库
							
							
							System.out.println(serverUpLoadPath);
							File file = new File(serverUpLoadPath, onlyName);
							try {
								fileItem.write(file);//文件上传
							} catch (Exception e) {
								e.printStackTrace();
							}
						}else {
							//不上传图片，图片路径就是原来的图片路径
							onlyName=oldPicPath;
						}
					}
				}
				
			} catch (FileUploadException e) {
				
				e.printStackTrace();
			}
			
		}
		//2.组织数据：封装数据到bean中
		User user = new User();
		user.setId(Integer.parseInt(idStr));
		user.setLoginName(loginName);
		user.setPassword(password);
		user.setSex(sex);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date birthday = null;
		try {
			
			if(birthdayStr!=null&&birthdayStr!="") {
				birthday = df.parse(birthdayStr);//把字符串类型转换成日期类型
			}
			//registerTime = df.parse(registerTimeStr);//把字符串类型转换成日期类型
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setBirthday(birthday);
//		user.setRegisterTime(registerTime);
		user.setAddress(address);
		user.setPhone(phone);
		//3.调用逻辑层API
		int rows = userService.update(user);
		//4.做控制跳转。
		if (rows>0) {
			System.out.println("恭喜你修改成功！！");
			HttpSession session = request.getSession() ;
			session.setAttribute("userUpdateSuccess", "恭喜你修改成功！");
			session.setAttribute("user", user);
			response.sendRedirect("userinfo.jsp");
			//this.userPage(request, response);
		} else {
			System.out.println("修改失败！！");
			response.sendRedirect("userinfo.jsp");
		}
	}
	public void updateMan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oldPicPath = null;
		String idStr = null;
		String loginName = null;
		String password = null;
		String sex = null;
		String birthdayStr = null;
		String registerTimeStr = null;
		String address = null;
		String phone = null;
		String onlyName = null;
		
		//判断是否以multipart/form-data方式提交
		boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
		if (isMultiPart) {
			//得到文件上传的对象
			FileItemFactory factory = new DiskFileItemFactory();
			//upload封装着表单的全部数据
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				//得到表单控件
				List<FileItem> items = upload.parseRequest(request);
				for (FileItem fileItem : items) {
					//判断是否是普通表单控件，还是文件上传控件
					boolean isFormFileld = fileItem.isFormField();
					if (isFormFileld) {//普通表单控件
						//得到普通表单控件的数据
						String inputName = fileItem.getFieldName();
						
						if (inputName.equals("id")) {
							idStr = fileItem.getString("utf-8");
							System.out.println(idStr);
						}else if (inputName.equals("loginName")) {
							loginName = fileItem.getString("utf-8");
							System.out.println(loginName);
						}else if (inputName.equals("password")) {
							password = fileItem.getString("utf-8");
							System.out.println(password);
						}else if (inputName.equals("sex")) {
							sex = fileItem.getString("utf-8");
							System.out.println(sex);
						}else if (inputName.equals("birthday")) {
							birthdayStr = fileItem.getString("utf-8");
							System.out.println(birthdayStr);
						}else if (inputName.equals("address")) {
							address = fileItem.getString("utf-8");
							System.out.println(address);
						}else if (inputName.equals("phone")) {
							phone = fileItem.getString("utf-8");
							System.out.println(phone);
						}else if (inputName.equals("registerTime")) {
							registerTimeStr = fileItem.getString("utf-8");
							System.out.println(registerTimeStr);
						}
						
					} else {
//						2.连图片也修改。
//						1.把服务器中的图片删除
//						2.上传新的图片到服务器中
						//要么修改了图片
						
						//文件上传控件 xxx.jpg  
						//得到文件名,能直接保存到数据库中吗？
						String fileName = fileItem.getName();
						//得到文件名不为空时候，就是连图片一起修改
						if(!("".equals(fileName))) {
							//1.把服务器中的图片删除
							//得到服务器中的upload文件夹的路径： C:\\apache-tomcat-8.0.50\\webapps\\taobao5\\upload
							String serverUpLoadPath = this.getServletContext().getRealPath("upload");
							//得到旧图片路径
							File deleteFile = new File(serverUpLoadPath, oldPicPath);
							deleteFile.delete();
							
							//2.上传新的图片到服务器中
							//数据库中的文件名，和上传到服务器中的文件名应该是全球唯一
							//得到文件的扩展名  .jpg
							String extendsName =  fileName.substring(fileName.lastIndexOf("."), fileName.length());
							String uuid = UUID.randomUUID().toString();
							//重命名为全球唯一的文件名
							onlyName = uuid + extendsName;//保存到书库
							
							
							System.out.println(serverUpLoadPath);
							File file = new File(serverUpLoadPath, onlyName);
							try {
								fileItem.write(file);//文件上传
							} catch (Exception e) {
								e.printStackTrace();
							}
						}else {
							//不上传图片，图片路径就是原来的图片路径
							onlyName=oldPicPath;
						}
					}
				}
				
			} catch (FileUploadException e) {
				
				e.printStackTrace();
			}
			
		}
		//2.组织数据：封装数据到bean中
		User user = new User();
		user.setId(Integer.parseInt(idStr));
		user.setLoginName(loginName);
		user.setPassword(password);
		user.setSex(sex);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date birthday = null;
		try {
			
			if(birthdayStr!=null&&birthdayStr!="") {
				birthday = df.parse(birthdayStr);//把字符串类型转换成日期类型
			}
			//registerTime = df.parse(registerTimeStr);//把字符串类型转换成日期类型
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setBirthday(birthday);
//		user.setRegisterTime(registerTime);
		user.setAddress(address);
		user.setPhone(phone);
		//3.调用逻辑层API
		int rows = userService.update(user);
		//4.做控制跳转。
		if (rows>0) {
			System.out.println("修改成功！！");
			HttpSession session = request.getSession() ;
//			session.setAttribute("userUpdateSuccess", "修改成功！");
//			response.sendRedirect("managerUser.jsp");
			this.userPage(request, response);
		} else {
			System.out.println("修改失败！！");
			this.userPage(request, response);
		}
	}
	public void userPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currentPageStr = request.getParameter("userCurrentPage");//前程传过来的当前页码
		if (currentPageStr==""||currentPageStr==null) {
			currentPageStr = "1";//如果没输入任何页码，默认的第一页
		}
		int usercurrentPage = Integer.parseInt(currentPageStr);
		int userpageSize = 5;//每页5条
		Page<User> userPage = userService.getOnePage(usercurrentPage, userpageSize);
		HttpSession session = request.getSession();
		session.setAttribute("userPage", userPage);
		response.sendRedirect("managerUser.jsp");//每页
	}
	public void getById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		User user = userService.getById(Integer.parseInt(idStr));
		HttpSession session = request.getSession() ;
		//在这里放在session中
		session.setAttribute("user", user);
		response.sendRedirect("userinfo.jsp");
	}
	public void getByIdManager(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		
		int id=0;
		if(idStr!=null) {
			
			id= Integer.parseInt(idStr);
		}
		User updateUser = userService.getById(id);
		HttpSession session = request.getSession() ;
		//在这里放在session中
		session.setAttribute("updateUser", updateUser);
		response.sendRedirect("updateUser.jsp");
	}
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String contextPath = request.getContextPath();
		System.out.println(contextPath);
		String method = request.getMethod();
		System.out.println(method);
		HttpSession session = request.getSession();
		
		//登录的控制部分，没有显示的
		request.setCharacterEncoding("UTF-8");
		String loginName = request.getParameter("loginName");
		String passWord = request.getParameter("password");
		System.out.println(loginName);
		System.out.println(passWord);
		User user = userService.login(loginName, passWord);
		if(user!=null){
			session.setAttribute("user", user);
			//request.getRequestDispatcher("PageServlet").forward(request, response);//类似于收到请求，我找index商量一下，但是只发送一次请求，所以商量过后带着我们一起的成果告诉你
			//response.sendRedirect("index.jsp");//重定向类似与你找我，但是我不知道让你去找index，当然他也不知道，但是是他回应你，他没有收到值
//			this.userPage(request, response);
			
			if(user.getLoginName().equals("admin")) {
				response.sendRedirect("mainManager.jsp");
			}else {
				
				response.sendRedirect("index.jsp");
			}
		}else{
			session.setAttribute("errot", "账号或密码错误！请重新输入！");
			response.sendRedirect("login_user.jsp");
		}
	}
}
