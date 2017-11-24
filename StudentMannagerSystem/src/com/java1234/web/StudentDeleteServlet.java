package com.java1234.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java1234.dao.GradeDao;
import com.java1234.dao.StudentDao;
import com.java1234.util.DbUtil;
import com.java1234.util.ResponseUtil;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class GradeDeleteServlet
 */
@WebServlet("/StudentDeleteServlet")
public class StudentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DbUtil dbUtil = new DbUtil();
	StudentDao studentDao = new StudentDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String delIds = request.getParameter("delIds");
		
		Connection conn = null;
		
		try{
			conn = dbUtil.getConnection();
			JSONObject result = new JSONObject();
			int delNums = studentDao.studentDelete(conn, delIds);
			result.put("delNums", delNums);
			if(delNums>0){
				result.put("success", "true");
				result.put("delNums", delNums);
			}else{
				result.put("errorMsg", "É¾³ýÊ§°Ü");
			}
			ResponseUtil.write(response, result);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				dbUtil.closeConnection(conn);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
