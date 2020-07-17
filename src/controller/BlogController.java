package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BlogDaoImpl;
import model.Blog;




@WebServlet(urlPatterns = {"/blog"})
public class BlogController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public BlogController() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				//insertBlog(request, response);
				break;
			case "/delete":
				//deleteBlog(request, response);
				break;
			case "/edit":
				//showEditForm(request, response);
				break;
			case "/update":
				//updateBlog(request, response);
				break;
			case "/list":
				listBlog(request, response);
				break;
			default:
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
				dispatcher.forward(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	private void listBlog(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException, ClassNotFoundException {
		BlogDaoImpl blogDAO = new BlogDaoImpl();
		List<Blog> listBlog = blogDAO.selectAllBlogs();
		request.setAttribute("listBlog", listBlog);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/blogListView.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/blogListView.jsp");
		dispatcher.forward(request, response);
	}
/*
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Blog existingTodo = blogDAO.selectBlog(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/blog-form.jsp");
		request.setAttribute("todo", existingTodo);
		dispatcher.forward(request, response);

	}

	private void insertBlog(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		
		String title = request.getParameter("title");
		String username = request.getParameter("username");
		String description = request.getParameter("description");
		
		/*DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-mm-dd");
		LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"),df);
		
		boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
		Blog newTodo = new Blog(title, username, description, LocalDate.now(), isDone);
		blogDAO.insertBlog(newTodo);
		response.sendRedirect("list");
	}

	private void updateBlog(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		String title = request.getParameter("title");
		String username = request.getParameter("username");
		String description = request.getParameter("description");
		//DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-mm-dd");
		LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"));
		
		boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
		Blog updateTodo = new Blog(id, title, username, description, targetDate, isDone);
		
		try {
			blogDAO.updateBlog(updateTodo);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		response.sendRedirect("list");
	}

	private void deleteBlog(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		blogDAO.deleteBlog(id);
		response.sendRedirect("list");
	}
}
*/
}
