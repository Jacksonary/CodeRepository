package com.java1234.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java1234.dao.GradeDao;
import com.java1234.model.Grade;
import com.java1234.util.DbUtil;
import com.java1234.util.ResponseUtil;
import com.java1234.util.StringUtil;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class GradeSaveServlet
 */
@WebServlet("/GradeSaveServlet")
public class GradeSaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DbUtil dbUtil = new DbUtil();
	GradeDao gradeDao = new GradeDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GradeSaveServlet() {
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
	// TODO Auto-generated method stub
			request.setCharacterEncoding("utf-8");
			String gradeName = request.getParameter("gradeName");
			String gradeDesc = request.getParameter("gradeDesc");
			String id = request.getParameter("id");
			Grade grade = new Grade(gradeName, gradeDesc);
			
			
			if(StringUtil.isNotEmpty(id)){
				grade.setId(Integer.parseInt(id));
			}
			
			Connection conn = null;
			try{
				int saveNums = 0;
				conn = dbUtil.getConnection();
				JSONObject result = new JSONObject();
				if(StringUtil.isNotEmpty(id)){
					//如果id不为空,则说明进行的是编辑操作
					saveNums = gradeDao.gradeModeify(conn, grade);
				}else{
					//如果id不存在，则说明是添加新用户的操作
					saveNums = gradeDao.gradeAdd(conn, grade);
				}
				if(saveNums==1){
					result.put("success", "true");//如果返回结果为1，则说明操作（添加或者是编辑）成功
				}else{
					result.put("success", "true");//*****API上设计的就是这样
					result.put("errorMsg", "保存失败");
				}
				ResponseUtil.write(response, result);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}

	}

