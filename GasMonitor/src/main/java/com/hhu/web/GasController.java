package com.hhu.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.github.pagehelper.PageHelper;
import com.hhu.entity.FaultLog;
import com.hhu.entity.PageBean;
import com.hhu.entity.Station;
import com.hhu.entity.User;
import com.hhu.service.FaultLogService;
import com.hhu.service.HistoryDateService;
import com.hhu.service.StationService;
import com.hhu.service.UserService;
import com.hhu.websocket.MyWebSocketHandler;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/")
@SessionAttributes("loginUser")
public class GasController {

	// 设置APPID/AK/SK,人脸识别的接口
	public static final String APP_ID = "9798514";
	public static final String API_KEY = "tKRPR8ex2u9GnckPtGoqgB5C";
	public static final String SECRET_KEY = "4hce67LM55S50jFLmpdlynqPG4XKGCUY";

	// 设值语音识别的接口
	public static final String WARN_APP_ID = "9959843";
	public static final String WARN_APP_KEY = "3SSb7QzDwRFFtsnxpTBSdVBS";
	public static final String WARN_SECRET_KEY = "RY3GFLENNrkslKRUF5Ft8cIfa6U648FH";

	@Autowired
	private UserService userService;

	@Autowired
	private StationService stationService;

	@Autowired
	private HistoryDateService historyDateService;
	
	@Autowired
	private FaultLogService faultLogService;

	//客户端请求登陆界面，直接get即可
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLogin() {
		return "login";
	} 
	
	//用户登陆界面登陆验证成功将页面重定向到主页即可
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String requestLogin(String username, String password, Model model, HttpSession session) {
		User loginUser = userService.getUser(username, password);
		if(loginUser==null) {//登陆失败
			model.addAttribute("error", "您输入的账号或密码有误！");
			return "login";
		} else { //登陆成功
			model.addAttribute("loginUser", loginUser);
			return "redirect:index";
		}
	}
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String getIndex() {
		return "index";
	}
	
//	@RequestMapping(value = "/index", method = RequestMethod.POST)
//	public String doLogin(String username, String password, RedirectAttributes model, HttpSession session) {
//		User loginUser = userService.getUser(username, password);
//		System.out.println(loginUser);
//
//		// 首先从session中去找是否有登录的用户
//		if (session.getAttribute("loginUser") == null) {
//			System.out.println("session中没有元素");
//
//			if (loginUser == null) {
//				// 注意这里用的Model的类，这里addFlashAttribute是添加瞬间的信息，不会显示到
//				// url中，而且页面刷新后，这些信息将消失
//				System.out.println("数据库中没有查询到元素");
//				model.addFlashAttribute("error", "用户名或者密码错误！");
//				return "redirect:login";
//			} else {
//				System.out.println("数据库中查询到了用户");
//				session.setAttribute("loginUser", loginUser);
//				System.out.println((User) session.getAttribute("loginUser"));
//				return "index";
//			}
//		} else {
//			System.out.println("session中存在用户");
//			if (((User) session.getAttribute("loginUser")).equals(loginUser)) {
//				System.out.println("验证session中的用户和数据库中查询到的用户一致");
//				System.out.println(((User) session.getAttribute("loginUser")));
//				return "index";
//			} else {
//				System.out.println("两者不一致");
//				model.addFlashAttribute("error", "用户名或者密码错误！");
//				return "redirect:login";
//			}
//
//		}
//	}

	// 进入表单界面
	@RequestMapping("/index/tables")
	public String getTables(Model model) {
		int pn = 1;// 当前页码
		int r = 10;// 每页的记录数

		int p1 = r;
		int p2 = (pn - 1) * p1;

		// 得到总记录数
		int total = stationService.getStationNumber();
		System.out.println("***********" + total + "**********");

		// 查询第pn页的记录
		List<Station> stationList = stationService.getAllStation(p1, p2);

		// 查询的页码
		int pageNum = pn;
		// 每页显示的记录数
		int pageSize = r;
		// 总页数
		int pages = (total % r == 0) ? (total / pageSize) : (total / pageSize + 1);
		// 创建Page
		PageBean<Station> pageBean = new PageBean<>(stationList);

		pageBean.setList(stationList);
		pageBean.setPageNum(pageNum);
		pageBean.setTotal(total);
		pageBean.setPages(pages);
		pageBean.setPageSize(pageSize);
		System.out.println("***PageSize:" + pageSize + "**********");

		model.addAttribute("pageBean", pageBean);

		// //获取第一页的5条内容
		// PageHelper.startPage(1,5);
		// List<Station> sl = StationService.getAllStation2();
		// //强转成Page
		// Page<Station> lists = (Page<Station>)sl;
		//
		// System.out.println(lists.getPageNum());
		// System.out.println(lists.get(3));
		// System.out.println(lists.getTotal());
		// model.addAttribute("pageBean", pageBean);

		return "tables";
	}

	// 进行表单查询的操作，写一个接口,进行分页查询,pn表示查询第几页，r表示每页显示多少条记录
	@RequestMapping(value = "/getStation", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String tableDemoAjax(@RequestParam String aoData) throws IOException {

		/*
		 * 这里对aoData的各项参数给出相关说明，
		 * sEcho表示的请求服务端的次数
		 * iDisplayLength表示每页显示多少条记录
		 * iDisplayStart表示每页的起始索引
		 * iColumns表示的表格中的总列数
		 * sColumns表示
		 */
		
		
		// 初始分页起始点：iDisplayStart--displayStart
		System.out.println("|||||||请求成功|||||||||");

		//将JSON字符串转变为JSON对象
		JSONArray jsonarray = JSONArray.fromObject(aoData);
		
		System.out.println("返回的参数对象：" + jsonarray);
		System.out.println("返回的参数字符串：" + aoData);
		
		// 计算得出总的记录数
		int total = stationService.getStationNumber();

		String sEcho = null;// 操作次数的计数
		int iDisplayStart = 0; // 每页的起始索引，第一页是0，如果，每页显示10条记录，那第二页的起始索引就是10
		int iDisplayLength = 0; // 每页显示的行数 ，即记录数

		for (int i = 0; i < jsonarray.size(); i++) {
			JSONObject obj = (JSONObject) jsonarray.get(i);
			if (obj.get("name").equals("sEcho"))
				sEcho = obj.get("value").toString();

			if (obj.get("name").equals("iDisplayStart"))
				iDisplayStart = obj.getInt("value");

			if (obj.get("name").equals("iDisplayLength"))
				iDisplayLength = obj.getInt("value");

		}

		// 这个计算得出前端的当前页页码
		int page_num = (iDisplayStart / iDisplayLength) + 1;
		
		// 对这个语句后面紧跟的查询语句进行分页,这里是物理分页，效率要比逻辑分页高
		PageHelper.startPage(page_num, iDisplayLength);
		// 后台获取数据
		List<Station> lst = stationService.getAllStation2();

		// 封装数据
		JSONObject getObj = new JSONObject();
		getObj.put("sEcho", sEcho);
		getObj.put("iTotalRecords", total);// 实际的行数
		getObj.put("iTotalDisplayRecords", total);// 显示的行数,这个要和上面写的一样

		getObj.put("aaData", lst);// 要以JSON格式返回

		System.out.println(getObj.toString());

		return getObj.toString();
	}

	// 进入日志记录界面
	@RequestMapping("/index/log")
	public String getLogs(Model model) {
		return "log";
	}

	// 获取日志记录接口
	@RequestMapping(value = "/getLogRecords", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String getLogRecords(@RequestParam String aoData,@RequestParam String startTime,@RequestParam String endTime) {
		
		// 将参数转换：更改初始页面长度 （每页的行数）：pageLength-iDisplayLength；
		// 初始分页起始点：iDisplayStart--displayStart
		System.out.println("|||||||日志记录请求成功|||||||||");
		System.out.println("原始返回参数：" + startTime + " " + endTime);
		//这里传过的参数aoData其实就是页面表格插件封装的一些关于分页以及页面搜索的参数
		JSONArray jsonarray = JSONArray.fromObject(aoData);
		
		System.out.println("返回的参数：" + jsonarray);
		String search = null;
		
		
		
		int total = 0;

		String sEcho = null;// 操作次数的计数
		int iDisplayStart = 0; // 每页的起始索引，第一页是0，如果，每页显示10条记录，那第二页的起始索引就是10
		int iDisplayLength = 0; // 每页显示的行数 ，即记录数
 
		for (int i = 0; i < jsonarray.size(); i++) {
			JSONObject obj = (JSONObject) jsonarray.get(i);
			
			if (obj.get("name").equals("sEcho")) {
				sEcho = obj.get("value").toString();
			}
 
			if (obj.get("name").equals("iDisplayStart")) {
				iDisplayStart = obj.getInt("value");
			}

			if (obj.get("name").equals("iDisplayLength")) {
				iDisplayLength = obj.getInt("value");
			}
			
			if(obj.get("name").equals("sSearch")) {
				search = obj.get("value").toString();
			}

		}
		
		System.out.println("查询参数为：" + search);

		// 这个计算得出前端的当前页页码
		int page_num = (iDisplayStart / iDisplayLength) + 1;
		
		//传过开始日期和结束日期都是yyyy-MM-dd的形式，利用sdf进行反转即可
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp st = null;
		Timestamp et = null;
		
		try {
			st = new Timestamp(sdf.parse(startTime).getTime());
			et = new Timestamp(sdf.parse(endTime).getTime());
		} catch (ParseException e) {
			System.out.println("+++++++日期选择器参数为空，无法转换+++++++++");;
		}
		
		//用来存放查询记录
		List<FaultLog> lst = null;
		
		// 对这个语句后面紧跟的查询语句进行分页
		PageHelper.startPage(page_num, iDisplayLength);
		
		//首次日期选择器传过来的参数为空，那就查询所有的日志记录
		if(startTime.equals("")||endTime.equals("")) {
			//日期选择器为空的时候查询所有的记录
			lst = faultLogService.getAllLogsNoTime();
			//计算所有的记录数
			total = faultLogService.getLogNumber();
		} else {
			// 后台获取数据
			lst = faultLogService.getAllLogs(st, et);
			// 计算得出待查询条件的记录数
			total = faultLogService.getLogNumberWithSearch(st, et);
		}

		// 封装数据
		JSONObject getObj = new JSONObject();
		getObj.put("sEcho", sEcho);
		getObj.put("iTotalRecords", total);// 实际的行数
		getObj.put("iTotalDisplayRecords", total);// 显示的行数,这个要和上面写的一样

		getObj.put("aaData", lst);// 要以JSON格式返回

		System.out.println(getObj.toString());

		return getObj.toString();
	}

	// 进入制图界面
	@RequestMapping("/index/getChart")
	public String getStationChart() {
		return "stationChart";
	}

	@Autowired
	MyWebSocketHandler handler;

	// 获取历史数据
	// @RequestMapping(value="/getHisDates",produces={"application/json;charset=UTF-8"})
	// @ResponseBody
	@RequestMapping("/getHisDates")
	public void getHisDates(HttpServletResponse resp) {

		Station station = stationService.getLastStation();
		try {
			resp.getWriter().print(station.getfX());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("最新的一条数据=" + station);

		// System.out.println("||||查询站点数成功|||||");
		// //查询所有的历史节点数据
		// List<Station> lst = stationService.getAllStation2();
		// //List<String> categories = new ArrayList<String>();//x轴的参数
		// List<Long> categories = new ArrayList<Long>();
		// List<BigDecimal> lsto = new ArrayList<BigDecimal>();//纵横的坐标1
		// List<Float> lsto2 = new ArrayList<Float>();//纵横的坐标2
		// List<String> lsto3 = new ArrayList<String>();
		// List<Object> lst4 = new ArrayList<Object>();
		//
		// for(int i=0;i<lst.size();i++) {
		// categories.add(lst.get(i).getfVtm().getTime());
		// lsto.add(lst.get(i).getfWu0());
		// lsto2.add(lst.get(i).getfX());
		// lsto3.add(lst.get(i).getfCaption());
		// lst4.add(lst.get(i).getfVtm().getTime() + "," + lst.get(i).getfX());
		// }
		//
		// JSONObject jsonObj = new JSONObject();
		//
		// jsonObj.put("data", lsto2);
		// jsonObj.put("lst", lst);
		// jsonObj.put("caption", lsto3);
		// jsonObj.put("categories",categories);
		// jsonObj.put("lst4", lst4);
		//
		// System.out.println("返回的时间：" + jsonObj.get("categories").toString());
		// System.out.println("返回的查询数据" + lst.toString());
		// jsonObj.put("data2", lsto2);
		// System.out.println("返回给前台的数据为：" + jsonObj.toString());
		// return jsonObj.toString();

	}

	// 预警报警接口
	@RequestMapping("/warning")
	public void warning(HttpServletResponse resp) throws IOException {
		// 创建客户端
		AipSpeech client = new AipSpeech(WARN_APP_ID, WARN_APP_KEY, WARN_SECRET_KEY);

		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);

		// 设置可选参数
		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("spd", "5");
		options.put("pit", "5");
		options.put("per", "4");
		TtsResponse res = client.synthesis("出口压力低压报警", "zh", 1, options);
		System.out.println("错误代码(0表示语音合成成功)：" + res.getErrorCode());
		byte[] data = res.getData();
		// 将返回的字节数组返回给前端
		JSONObject json = new JSONObject();
		json.put("audio", data);
		resp.getWriter().write(json.toString());
	}

	// 进入人脸识别 界面
	@RequestMapping(value = "/face", method = RequestMethod.GET)
	public String face() {
		return "face";
	}

	// 人脸检测中转接口
	@RequestMapping(value = "/dete", method = RequestMethod.POST)
	public void detectFace(String image, HttpServletResponse resp) throws Exception {

		AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
		client.setConnectionTimeoutInMillis(2000);
		// 通过打开的连接传输数据的超时时间（单位：毫秒）
		client.setSocketTimeoutInMillis(60000);

		// 将自己需要返回的参数放进HashMap中，然后放进detect（）的第二个参数中，
		// 可选属性包括max_face_num和face_fields两部分
		HashMap<String, String> options = new HashMap<String, String>();
		// 注意，这里一定要设置一下参数，因为max_face_num最大处理人脸默认为1，不设置的话不能识别多人脸
		options.put("max_face_num", "10");
		// 将需要的参数放进HashMap中,如果是添加多个属性的话就用逗号分开,文档中可以查看具体的，
		// expression表示表情表情，0，不笑；1，微笑；2，大笑
		options.put("face_fields", "expression,gender,age,beauty,glasses");

		// //将图片转成二进制数组进行上传
		// byte[] pic = null;
		// File f = new File(image);
		// FileInputStream fis = new FileInputStream(f);
		// pic = new byte[fis.available()];
		// fis.read(pic);
		// fis.close();

		// 将base64图片的编码进行解码，转成二进制数组的形式，作为参数
		byte[] pic = Base64.decodeBase64(image);

		org.json.JSONObject response = client.detect(pic, options);

		// 将服务器那边的返回json数据返回给前台调用的ajax
		resp.getWriter().print(response);

		System.out.println((int) response.get("result_num"));

		System.out.println(response.toString());
	}

	// 进行用户人脸的注册
	@RequestMapping(value = "/addface", method = RequestMethod.POST)
	public void facesetAddUser(String image, HttpServletResponse resp) throws Exception {
		AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
		client.setConnectionTimeoutInMillis(2000);
		// 通过打开的连接传输数据的超时时间（单位：毫秒）
		client.setSocketTimeoutInMillis(60000);

		System.out.println("请求成功");

		byte[] imgData = Base64.decodeBase64(image);

		HashMap<String, String> options = new HashMap<String, String>();
		// 指定注册的时候用的是图片的二进数组的形式
		options.put("imgPath/imgData", "imgData");

		org.json.JSONObject res = client.addUser("uid1", "Weiguo Liu", Arrays.asList("group1"), imgData, options);

		resp.getWriter().print(res);

		System.out.println(res.toString(2));
	}

	// 进行用户认证
	@RequestMapping(value = "/verifyUser", method = RequestMethod.POST)
	public void verifyUser(String image, HttpServletResponse resp) throws Exception {
		AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
		client.setConnectionTimeoutInMillis(2000);
		// 通过打开的连接传输数据的超时时间（单位：毫秒）
		client.setSocketTimeoutInMillis(60000);

		System.out.println("正在请求认证=======");

		byte[] pic = Base64.decodeBase64(image);
		HashMap<String, Object> options = new HashMap<String, Object>(1);
		// 指定注册的时候用的是图片的二进数组的形式
		options.put("imgPath/imgData", "imgData");

		options.put("top_num", 5);
		// 从group1中查找用户
		org.json.JSONObject res = client.verifyUser("uid1", Arrays.asList("group1"), pic, options);
		resp.getWriter().print(res);

		System.out.println(res.toString(2));
//		float s = 0f;
//		try {
//			s = Float.parseFloat((res.get("result")).toString().replace("[", "").split(",")[0]);
//		} catch (Exception e) {
//			return "face";
//		}
//		System.out.print("带获取的参数：" + s);
//		if(s>80) {
//			System.out.println("大于80");
//			return "redirect:index";
//		} else {
//			System.out.println("小于80");
//			return "face";
//		}
	}

}
