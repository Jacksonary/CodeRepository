package com.java1234.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java1234.dao.GradeDao;
import com.java1234.model.Grade;
import com.java1234.model.PageBean;
import com.java1234.util.DbUtil;
import com.java1234.util.JsonUtil;
import com.java1234.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class GradeListServlet
 */
@WebServlet("/GradeListServlet")
public class GradeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DbUtil dbUtil = new DbUtil();
	GradeDao gradeDao = new GradeDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GradeListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		String gradeName = request.getParameter("gradeName");
		if(gradeName==null){
			gradeName = "";
		}
		Grade grade = new Grade();
		grade.setGradeName(gradeName);
		
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		
		Connection conn=null;
		try {
			conn=dbUtil.getConnection();
			JSONObject result=new JSONObject();
			//将返回的ResultSet转换成JSONArray
			JSONArray jsonArray=JsonUtil.formatRsToJsonArray(gradeDao.gradeList(conn, pageBean,grade));
			int total=gradeDao.gradeCount(conn,grade);
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeConnection(conn);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
