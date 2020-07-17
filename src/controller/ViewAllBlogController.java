package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import dao.BlogDaoImpl;
import model.Blog;


@WebServlet(urlPatterns = {"/allblogs"})
public class ViewAllBlogController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("View All Blogs");
		System.out.println("Getting all blog post");
		BlogDaoImpl blogDAO = new BlogDaoImpl();
		List<Blog> listBlog =null;
		try {
			listBlog =blogDAO.selectAllBlogs();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Blog bloglist:listBlog) {
			System.out.println(bloglist.getBlogId());
			System.out.println(bloglist.getBlogTitle());
			System.out.println(bloglist.getBlogDescription());
			System.out.println(bloglist.getPostedOn());
		}
		request.setAttribute("listBlog", listBlog);
		RequestDispatcher rd=this.getServletContext().getRequestDispatcher("/WEB-INF/views/blogView.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
