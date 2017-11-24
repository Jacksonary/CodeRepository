package com.java1234.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java1234.dao.StudentDao;
import com.java1234.model.Grade;
import com.java1234.model.Student;
import com.java1234.util.DateToStringUtil;
import com.java1234.util.DbUtil;
import com.java1234.util.ResponseUtil;
import com.java1234.util.StringUtil;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class GradeSaveServlet
 */
@WebServlet("/StudentSaveServlet")
public class StudentSaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DbUtil dbUtil = new DbUtil();
	StudentDao studentDao = new StudentDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentSaveServlet() {
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
			String stuName = request.getParameter("stuName");
			String stuNo = request.getParameter("stuNo");
			String sex = request.getParameter("sex");
			String birthday = request.getParameter("birthday");
			String gardeId = request.getParameter("gradeId");
			String email = request.getParameter("email");
			String stuDesc = request.getParameter("stuDesc");
			String stuId = request.getParameter("stuId");
			
			System.out.println("前端传过来的数据如下：");
			System.out.println("stuName:" + stuName);
			System.out.println("stuNo:" + stuNo);
			System.out.println("sex:" + sex);
			System.out.println("birthday:" + birthday);
			System.out.println("gardeId:" + gardeId);
			System.out.println("email:" + email);
			System.out.println("stuDesc:" + stuDesc);
			System.out.println("stuId:" + stuId);
			
			
			Student student = null;
			try {
				student = new Student(stuNo, stuName, sex, DateToStringUtil.formatString(birthday,"yyyy-MM-dd"), email, stuDesc, Integer.parseInt(gardeId));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			if(StringUtil.isNotEmpty(stuId)){
				student.setStuId(Integer.parseInt(stuId));
			}
			
			Connection conn = null;
			try{
				int saveNums = 0;
				conn = dbUtil.getConnection();
				JSONObject result = new JSONObject();
				if(StringUtil.isNotEmpty(stuId)){
					//���id��Ϊ��,��˵�����е��Ǳ༭����
					saveNums = studentDao.studentModify(conn, student);
				}else{
					//���id�����ڣ���˵����������û��Ĳ���
					saveNums = studentDao.studentAdd(conn, student);
				}
				if(saveNums==1){
					result.put("success", "true");//������ؽ��Ϊ1����˵����������ӻ����Ǳ༭���ɹ�
				}else{
					result.put("success", "true");//*****API����Ƶľ�������
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

