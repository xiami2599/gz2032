package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

import servlet.BaseServlet;

import dao.entity.DongDongQiang;
import dto.Page;

import service.DongDongQiangService;
import service.impl.DongDongQiangServiceImpl;

public class DongDongQiangServlet extends BaseServlet{

	DongDongQiangService ddqService = new DongDongQiangServiceImpl();
	
	//首页咚咚抢分页
		public void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String currentPageStr = request.getParameter("currentPage");//前端传过来的当前页码
			if (currentPageStr==""||currentPageStr==null) {
				currentPageStr = "1";//如果没输入任何页码，默认的第一页
			}
			int currentPage = Integer.parseInt(currentPageStr);
			int pageSize = 4;
			Page<DongDongQiang> ddqPage = ddqService.getOnePage(currentPage, pageSize);
			HttpSession session = request.getSession();
			session.setAttribute("ddqPage", ddqPage);
			response.sendRedirect("index.jsp");//每页
		}
		//咚咚抢主页分页
		public void ddqPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String currentPageStr = request.getParameter("ddqcurrentPage1");//前端传过来的当前页码
			if (currentPageStr==""||currentPageStr==null) {
				currentPageStr = "1";//如果没输入任何页码，默认的第一页
			}
			int ddqcurrentPage1 = Integer.parseInt(currentPageStr);
			int ddqpageSize1 = 8;
			Page<DongDongQiang> ddqPage1 = ddqService.getOnePage(ddqcurrentPage1, ddqpageSize1);
			HttpSession session = request.getSession();
			session.setAttribute("ddqPage1", ddqPage1);
			response.sendRedirect("dongDong.jsp");//每页
		}
		//后台分页
		public void pageManager(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String currentPageStr = request.getParameter("manDDQcurrentPage");//前端传过来的当前页码
			if (currentPageStr==""||currentPageStr==null) {
				currentPageStr = "1";//如果没输入任何页码，默认的第一页
			}
			int manDDQcurrentPage = Integer.parseInt(currentPageStr);
			int manDDQpageSize = 6;//每页5条
			Page<DongDongQiang> managerddqPage = ddqService.getOnePage(manDDQcurrentPage, manDDQpageSize);
			HttpSession session = request.getSession();
			session.setAttribute("managerddqPage", managerddqPage);
			response.sendRedirect("managerDdQiang.jsp");//每页
		}
		
		public void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//String picPath = null;
			
			String title = null;
			String descri = null;
			double price=0.0;
			double pririce=0.0;
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
							if (inputName.equals("title")) {
								title = fileItem.getString("utf-8");
								
							}else if (inputName.equals("descri")) {
								descri = fileItem.getString("utf-8");
								
							}else if (inputName.equals("price")) {
								price = Double.parseDouble(fileItem.getString("utf-8"));
								
							}else if (inputName.equals("pririce")) {
								pririce = Double.parseDouble(fileItem.getString("utf-8"));
								
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
			DongDongQiang ddq = new DongDongQiang();
			ddq.setPicpath(onlyName);
			ddq.setTitle(title);
			ddq.setDescri(descri);
			ddq.setPrice(price);
			ddq.setPririce(pririce);
			
			//3.调用逻辑层API
			int rows = ddqService.save(ddq);
			//4.做控制跳转。
			if (rows>0) {
				System.out.println("添加成功！！");
				//response.sendRedirect("UserServlet?action=page");
				this.pageManager(request, response);
			} else {
				System.out.println("添加失败！！");
				this.pageManager(request, response);
			}
		}

		public void getById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String idStr = request.getParameter("id");
			DongDongQiang updateDdq = ddqService.getById(Integer.parseInt(idStr));
			HttpSession session = request.getSession() ;
			//在这里放在session中
			session.setAttribute("updateDdq", updateDdq);
			response.sendRedirect("updateDdQiang.jsp");
		}
		public void getByIdToDongInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String idStr = request.getParameter("id");
			DongDongQiang ddqInfo = ddqService.getById(Integer.parseInt(idStr));
			HttpSession session = request.getSession() ;
			//在这里放在session中
			session.setAttribute("ddqInfo", ddqInfo);
			response.sendRedirect("dongdonginfo.jsp");
		}

		public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//从客户端传过来产生的乱码处理
			request.setCharacterEncoding("utf-8");
			//从服务器端传到客户端产生的乱码处理
			response.setCharacterEncoding("utf-8");
			//响应头的乱码处理
			response.setContentType("text/html; charset=UTF-8");		
			PrintWriter out = response.getWriter();
			String manDDQcurrentPage = request.getParameter("manDDQcurrentPage");
			//1.收集数据
			String idStr = request.getParameter("id");
			//删除数据库记录前，先用删除服务器上的图片
			String serverUpLoadPath = this.getServletContext().getRealPath("upload");
			String picPath = request.getParameter("picpath");
			File file = new File(serverUpLoadPath, picPath);
			file.delete();
			
			//2.封装数据
			//3.调用逻辑层API
			//删除前提示：确定要删除该记录吗？
			int rows = ddqService.deleteById(Integer.parseInt(idStr));
			if (rows>0) {
				//在客户端中弹出删除成功消息框
//				System.out.println("删除成功！！");
				out.write("<script type=\"text/javascript\">");
					out.write("alert(\"删除成功！！\");");
					out.write("window.location.href=\"DongDongQiangServlet?action=pageManager&manDDQcurrentPage="+manDDQcurrentPage+"\"");
				out.write("</script>");
				
				//response.sendRedirect("PageServlet");
			} else {
				//在客户端中弹出删除成功消息框
				//System.out.println("删除失败！！");
				out.write("<script type=\"text/javascript\">");
					out.write("alert(\"删除失败！！\");");
					out.write("window.location.href=\"DongDongQiangServlet?action=pageManager&manDDQcurrentPage="+manDDQcurrentPage+"\"");
				out.write("</script>");
				//response.sendRedirect("PageServlet");
			}
			
	}

		
		public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String oldPicPath = null;
			String idStr = null;
			String descri = null;
			String title = null;
			double price = 0.0;
			double pririce = 0.0;
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
								
							}else if (inputName.equals("title")) {
								title = fileItem.getString("utf-8");
								
							}else if (inputName.equals("oldPicPath")) {
								oldPicPath = fileItem.getString("utf-8");
								System.out.println(oldPicPath);
							}else if (inputName.equals("descri")) {
								descri = fileItem.getString("utf-8");
								
							}else if (inputName.equals("price")) {
								price = Double.parseDouble(fileItem.getString("utf-8"));
								
							}else if (inputName.equals("pririce")) {
								pririce = Double.parseDouble(fileItem.getString("utf-8"));
								
							}
							
						} else {
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
			DongDongQiang ddq = new DongDongQiang();
			ddq.setId(Integer.parseInt(idStr));
			ddq.setTitle(title);
			ddq.setDescri(descri);
			ddq.setPrice(price);
			ddq.setPririce(pririce);
			
			ddq.setPicpath(onlyName);
			//3.调用逻辑层API
			int rows = ddqService.update(ddq);
			//4.做控制跳转。
			if (rows>0) {
				System.out.println("修改成功！！");
				//response.sendRedirect("UserServlet?action=page");
				this.pageManager(request, response);
			} else {
				System.out.println("修改失败！！");
				response.sendRedirect("updateddq.jsp");
			}
		}
	
}
