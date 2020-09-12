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

import dao.entity.BaoYou;
import dto.Page;
import service.BaoYouService;
import service.impl.BaoYouServiceImpl;

public class BaoYouServlet extends BaseServlet{
	
	BaoYouService gcService = new BaoYouServiceImpl();
	
	
		//9.9包邮主页分页
		public void byPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String currentPageStr = request.getParameter("bycurrentPage");//前端传过来的当前页码
			if (currentPageStr==""||currentPageStr==null) {
				currentPageStr = "1";//如果没输入任何页码，默认的第一页
			}
			int bycurrentPage = Integer.parseInt(currentPageStr);
			int bypageSize = 15;//每页5条
			Page<BaoYou> byPage = gcService.getOnePage(bycurrentPage, bypageSize);
			HttpSession session = request.getSession();
			session.setAttribute("byPage", byPage);
			response.sendRedirect("9.9BaoYou.jsp");//每页
		}
		//后台分页
		public void pageManager(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String currentPageStr = request.getParameter("manBycurrentPage");//前端传过来的当前页码
			if (currentPageStr==""||currentPageStr==null) {
				currentPageStr = "1";//如果没输入任何页码，默认的第一页
			}
			int manBycurrentPage = Integer.parseInt(currentPageStr);
			int manpageBySize = 6;//每页5条
			Page<BaoYou> managerbyPage = gcService.getOnePage(manBycurrentPage, manpageBySize);
			HttpSession session = request.getSession();
			session.setAttribute("managerbyPage", managerbyPage);
			response.sendRedirect("managerBaoYou.jsp");//每页
		}
		
		public void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//String picPath = null;
			
			String title = null;
			String picpath = null;
			double price=0.0;
			double prix=0.0;
			String describe=null;
			int discount=0;
			int sales=0;
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
								
							}else if (inputName.equals("prix")) {
								prix = Double.parseDouble(fileItem.getString("utf-8"));
								
							}else if (inputName.equals("price")) {
								price = Double.parseDouble(fileItem.getString("utf-8"));
								
							}else if (inputName.equals("describe")) {
								describe = fileItem.getString("utf-8");
								
							}else if (inputName.equals("discount")) {
								discount = Integer.parseInt(fileItem.getString("utf-8"));
								
							}else if (inputName.equals("sales")) {
								sales =Integer.parseInt(fileItem.getString("utf-8"));
								
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
			BaoYou fq = new BaoYou();
			fq.setPicpath(onlyName);
			fq.setTitle(title);
			fq.setPrix(prix);
			fq.setPrice(price);
			fq.setDescribe(describe);
			fq.setDiscount(discount);
			fq.setSales(sales);
			
			//3.调用逻辑层API
			int rows = gcService.save(fq);
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
			BaoYou updateBaoYou = gcService.getById(Integer.parseInt(idStr));
			HttpSession session = request.getSession() ;
			//在这里放在session中
			session.setAttribute("updateBaoYou", updateBaoYou);
			response.sendRedirect("updateBaoYou.jsp");
		}
		public void getByIdToInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String idStr = request.getParameter("id");
			BaoYou byProductInfo = gcService.getById(Integer.parseInt(idStr));
			HttpSession session = request.getSession() ;
			//在这里放在session中
			session.setAttribute("byProductInfo", byProductInfo);
			response.sendRedirect("baoyouproductinfo.jsp");
		}

		public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//从客户端传过来产生的乱码处理
			request.setCharacterEncoding("utf-8");
			//从服务器端传到客户端产生的乱码处理
			response.setCharacterEncoding("utf-8");
			//响应头的乱码处理
			response.setContentType("text/html; charset=UTF-8");		
			PrintWriter out = response.getWriter();
			String manBycurrentPage = request.getParameter("manBycurrentPage");
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
			int rows = gcService.deleteById(Integer.parseInt(idStr));
			if (rows>0) {
				//在客户端中弹出删除成功消息框
//				System.out.println("删除成功！！");
				out.write("<script type=\"text/javascript\">");
					out.write("alert(\"删除成功！！\");");
					out.write("window.location.href=\"BaoYouServlet?action=pageManager&manBycurrentPage="+manBycurrentPage+"\"");
				out.write("</script>");
				
				//response.sendRedirect("PageServlet");
			} else {
				//在客户端中弹出删除成功消息框
				//System.out.println("删除失败！！");
				out.write("<script type=\"text/javascript\">");
					out.write("alert(\"删除失败！！\");");
					out.write("window.location.href=\"BaoYouServlet?action=pageManager&manBycurrentPage="+manBycurrentPage+"\"");
				out.write("</script>");
				//response.sendRedirect("PageServlet");
			}
			
	}

		
		public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String oldPicPath = null;
			String idStr = null;
			String describe = null;
			String title = null;
			double price = 0.0;
			double prix = 0.0;
			int discount=0;
			int sales=0;
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
							}else if (inputName.equals("describe")) {
								describe = fileItem.getString("utf-8");
								
							}else if (inputName.equals("price")) {
								price = Double.parseDouble(fileItem.getString("utf-8"));
								
							}else if (inputName.equals("prix")) {
								prix = Double.parseDouble(fileItem.getString("utf-8"));
								
							}else if (inputName.equals("discount")) {
								discount = Integer.parseInt(fileItem.getString("utf-8"));
								
							}else if (inputName.equals("sales")) {
								sales = Integer.parseInt(fileItem.getString("utf-8"));
								
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
			BaoYou fq = new BaoYou();
			fq.setId(Integer.parseInt(idStr));
			fq.setTitle(title);
			fq.setDescribe(describe);
			fq.setPrice(price);
			fq.setPrix(prix);
			fq.setDiscount(discount);
			fq.setSales(sales);
			
			fq.setPicpath(onlyName);
			//3.调用逻辑层API
			int rows = gcService.update(fq);
			//4.做控制跳转。
			if (rows>0) {
				System.out.println("修改成功！！");
				//response.sendRedirect("UserServlet?action=page");
				this.pageManager(request, response);
			} else {
				System.out.println("修改失败！！");
				response.sendRedirect("updatefq.jsp");
			}
		}


}
