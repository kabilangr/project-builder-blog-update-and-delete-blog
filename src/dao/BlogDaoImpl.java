package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import model.*;
import utility.ConnectionManager;

public class BlogDaoImpl
{
	public void insertBlog(Blog blog) throws ClassNotFoundException, SQLException, IOException
	{
		Connection con=ConnectionManager.getConnection();
		PreparedStatement st=con.prepareStatement("insert into blog(id,title,dis,d)values(?,?,?,?)");
		st.setLong(1, blog.getBlogId());
		st.setString(2, blog.getBlogTitle());
		st.setString(3, blog.getBlogDescription());
		Date date=Date.valueOf(blog.getPostedOn());
		st.setDate(4, date);
		st.executeUpdate();
		System.out.println("Done");
		con.close();
	}
	public List<Blog> selectAllBlogs() throws SQLException, ClassNotFoundException, IOException
	{
		List<Blog> list=new ArrayList<Blog>();
		String sql="select * from blog order by postedon asc";
		Connection con=ConnectionManager.getConnection();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(sql);
		Blog b= new Blog();
		while(rs.next())
		{
			b.setBlogId(rs.getInt("id"));
			b.setBlogTitle(rs.getString("title"));
			b.setBlogDescription(rs.getString("dis"));
			java.sql.Date date = (java.sql.Date) rs.getDate("postedon");
			b.setPostedOn(date.toLocalDate());
			list.add(b);
		}
		con.close();
		return list;
	}
	public Blog selectBlog(int blogid) throws ClassNotFoundException, SQLException, IOException
	{
		List<Blog> list=new ArrayList<Blog>();
		list=selectAllBlogs();
		Blog c=new Blog();
		for(int i=0;i<list.size();i++)
		{
			if(blogid==list.get(i).getBlogId())
			{
				c=list.get(i);
				break;
			}
		}
		return c;
	}
	public boolean deleteBlog(int id) throws ClassNotFoundException, SQLException, IOException
	{
		List<Blog> list=new ArrayList<Blog>();
		list=selectAllBlogs();
int k=0;
		for(int i=0;i<list.size();i++)
		{
			if(id==list.get(i).getBlogId())
			{
				k=1;
				break;
			}
		}
		if(k==0)
		return false;
		else
		{
			Connection con=ConnectionManager.getConnection();
			PreparedStatement st=con.prepareStatement("delete from blog where id=(?)");
			st.setInt(1, id);
            st.executeUpdate();
            System.out.println("Done");
          return true;
		}
	}
	public boolean updateBlog(Blog blog) throws ClassNotFoundException, SQLException, IOException
	{
		List<Blog> list=new ArrayList<Blog>();
		list=selectAllBlogs();
int k=0;
		for(int i=0;i<list.size();i++)
		{
			if(blog.getBlogId()==list.get(i).getBlogId())
			{
				k=1;
				break;
			}
		}
		if(k==1)
		{
			
		Connection con=ConnectionManager.getConnection();
		PreparedStatement st=con.prepareStatement("update blog set title=(?) ,dis=(?) , postedon=(?) where id=(?)");
		st.setString(1, blog.getBlogTitle());
		st.setString(2, blog.getBlogDescription());
		Date date=Date.valueOf(blog.getPostedOn());
		st.setDate(3, date);
		st.setInt(4, blog.getBlogId());
		st.executeLargeUpdate();
		 System.out.println("Done");
		return true;
		}
		else
			return false;
		
	}
}