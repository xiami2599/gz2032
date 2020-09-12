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

import dao.entity.Car;
import dao.entity.GoodChoise;
import dto.Page;
import service.GoodChoiseService;
import service.impl.GoodChoiseServiceImpl;

public class GoodChoiseServlet extends BaseServlet{
	
	GoodChoiseService gcService = new GoodChoiseServiceImpl();
	
	//首页好货精选分页
		public void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String currentPageStr = request.getParameter("cgcurrentPage");//前端传过来的当前页码
			if (currentPageStr==""||currentPageStr==null) {
				currentPageStr = "1";//如果没输入任何页码，默认的第一页
			}
			int cgcurrentPage = Integer.parseInt(currentPageStr);
			int cgpageSize = 10;//每页5条
			Page<GoodChoise> cgPage = gcService.getOnePage(cgcurrentPage, cgpageSize);
			HttpSession session = request.getSession();
			session.setAttribute("cgPage", cgPage);
			response.sendRedirect("index.jsp");//每页
		}
		//好货精选主页分页
		public void cgPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String currentPageStr = request.getParameter("cgcurrentPage1");//前端传过来的当前页码
			if (currentPageStr==""||currentPageStr==null) {
				currentPageStr = "1";//如果没输入任何页码，默认的第一页
			}
			int cgcurrentPage1 = Integer.parseInt(currentPageStr);
			int cgpageSize1 = 15;//每页5条
			Page<GoodChoise> cgPage1 = gcService.getOnePage(cgcurrentPage1, cgpageSize1);
			HttpSession session = request.getSession();
			session.setAttribute("cgPage1", cgPage1);
			response.sendRedirect("goodChoice.jsp");//每页
		}
		//后台分页
		public void pageCgManager(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String currentPageStr = request.getParameter("managerCurrentPage");//前端传过来的当前页码
			if (currentPageStr==""||currentPageStr==null) {
				currentPageStr = "1";//如果没输入任何页码，默认的第一页
			}
			int manGCcurrentPage = Integer.parseInt(currentPageStr);
			int manGCpageSize = 6;//每页5条
			Page<GoodChoise> managerGCPage = gcService.getOnePage(manGCcurrentPage, manGCpageSize);
			HttpSession session = request.getSession();
			session.setAttribute("managerGCPage", managerGCPage);
			response.sendRedirect("managerGoodChoice.jsp");//每页
		}
		
		public void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String title = null;
			String descri = null;
			double price=0.0;
			double pririce=0.0;
			int youhui=0;
			int xiaoliang=0;
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
								
							}else if (inputName.equals("youhui")) {
								youhui = Integer.parseInt(fileItem.getString("utf-8"));
								
							}else if (inputName.equals("xiaoliang")) {
								xiaoliang =Integer.parseInt(fileItem.getString("utf-8"));
								
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
			GoodChoise fq = new GoodChoise();
			fq.setPicpath(onlyName);
			fq.setTitle(title);
			fq.setDescri(descri);
			fq.setPrice(price);
			fq.setPririce(pririce);
			fq.setYouhui(youhui);
			fq.setXiaoliang(xiaoliang);
			
			//3.调用逻辑层API
			int rows = gcService.save(fq);
			//4.做控制跳转。
			if (rows>0) {
				System.out.println("添加成功！！");
				//response.sendRedirect("UserServlet?action=page");
				this.pageCgManager(request, response);
			} else {
				System.out.println("添加失败！！");
				response.sendRedirect("addgoodchoise.jsp");
			}
		}

		public void getById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String idStr = request.getParameter("id");
			GoodChoise updateGoodChoise = gcService.getById(Integer.parseInt(idStr));
			HttpSession session = request.getSession() ;
			//在这里放在session中
			session.setAttribute("updateGoodChoise", updateGoodChoise);
			response.sendRedirect("updateGoodChoise.jsp");
		}
		public void getByIdToGoodChoiceInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String idStr = request.getParameter("id");
			GoodChoise GoodChoiceInfo = gcService.getById(Integer.parseInt(idStr));
			HttpSession session = request.getSession() ;
			//在这里放在session中
			session.setAttribute("GoodChoiceInfo", GoodChoiceInfo);
			response.sendRedirect("goodchoiceproducrinfo.jsp");
		}

		public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//从客户端传过来产生的乱码处理
			request.setCharacterEncoding("utf-8");
			//从服务器端传到客户端产生的乱码处理
			response.setCharacterEncoding("utf-8");
			//响应头的乱码处理
			response.setContentType("text/html; charset=UTF-8");		
			PrintWriter out = response.getWriter();
			String currentPage2 = request.getParameter("cgcurrentPage2");
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
					out.write("window.location.href=\"GoodChoiseServlet?action=pageCgManager&cgcurrentPage2="+currentPage2+"\"");
				out.write("</script>");
				
				//response.sendRedirect("PageServlet");
			} else {
				//在客户端中弹出删除成功消息框
				//System.out.println("删除失败！！");
				out.write("<script type=\"text/javascript\">");
					out.write("alert(\"删除失败！！\");");
					out.write("window.location.href=\"GoodChoiseServlet?action=pageCgManager&cgcurrentPage2="+currentPage2+"\"");
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
			int youhui=0;
			int xiaoliang=0;
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
								
							}else if (inputName.equals("youhui")) {
								youhui = Integer.parseInt(fileItem.getString("utf-8"));
								
							}else if (inputName.equals("xiaoliang")) {
								xiaoliang = Integer.parseInt(fileItem.getString("utf-8"));
								
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
			GoodChoise fq = new GoodChoise();
			fq.setId(Integer.parseInt(idStr));
			fq.setTitle(title);
			fq.setDescri(descri);
			fq.setPrice(price);
			fq.setPririce(pririce);
			fq.setYouhui(youhui);
			fq.setXiaoliang(xiaoliang);
			
			fq.setPicpath(onlyName);
			//3.调用逻辑层API
			int rows = gcService.update(fq);
			//4.做控制跳转。
			if (rows>0) {
				System.out.println("修改成功！！");
				//response.sendRedirect("UserServlet?action=page");
				this.pageCgManager(request, response);
			} else {
				System.out.println("修改失败！！");
				this.pageCgManager(request, response);
			}
		}

		public void souSuo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			String name = request.getParameter("souSuoName");
			
			if (name!=null&&name!="") {
				
				List<GoodChoise> souSuoGoods = gcService.souSuoGoods(name);
				
				System.out.println(name);
				for (GoodChoise goodChoise : souSuoGoods) {
					System.out.println(goodChoise.getTitle());
				}
				
				HttpSession session = request.getSession();
				session.setAttribute("souSuoGoods", souSuoGoods);
				
			}
			response.sendRedirect("souSuoGoods.jsp");
			
			
		}
		public void souSuoByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String min = request.getParameter("minPrice");
			String max = request.getParameter("maxPrice");
			
			double minPrice =0;
			double maxPrice =0;
//			if (min!=null && min!="") {
//				minPrice = Double.parseDouble(min);
//			}
			if ( min!=null && min!="") {
				
				minPrice = Double.parseDouble(min);
			}
			if (max!=null && max!="" ) {
				maxPrice = Double.parseDouble(max);
			}
			System.out.println("最小价格："+minPrice+"最大价格："+maxPrice);
			
			List<GoodChoise> souSuoGoodsByPrice = gcService.souSuoGoodsByPrice(minPrice, maxPrice);
		
			HttpSession session = request.getSession();
			session.setAttribute("souSuoGoodsByPrice", souSuoGoodsByPrice);
			
			response.sendRedirect("souSuoGoods.jsp");
		}
}
