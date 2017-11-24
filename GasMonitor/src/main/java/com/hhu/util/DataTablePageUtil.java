package com.hhu.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class DataTablePageUtil<T> {

	 /*------------------DT自动请求的参数(Sent parameters) begin--------------------*/
    /*
     * 绘制计数器。这个是用来确保Ajax从服务器返回的是对应的（Ajax是异步的，因此返回的顺序是不确定的）。 要求在服务器接收到此参数后再返回
     */
    private int draw; // 第几次请求
    /*
     * 第一条数据的起始位置，比如0代表第一条数据
     */
    private int start = 0;// 起止位置
    /*
     * 告诉服务器每页显示的条数，这个数字会等于返回的 data集合的记录数，可能会大于因为服务器可能没有那么多数据。
     * 这个也可能是-1，代表需要返回全部数据(尽管这个和服务器处理的理念有点违背)
     */
    private int length = 100; // 数据长度

    /*
     * 全局的搜索条件，条件会应用到每一列（ searchable需要设置为 true ）
     */
    private String search;

    /*
     * 如果为 true代表全局搜索的值是作为正则表达式处理，为 false则不是。
     * 注意：通常在服务器模式下对于大数据不执行这样的正则表达式，但这都是自己决定的
     */
    private boolean is_search;

    /*
     * 告诉后台那些列是需要排序的。 i是一个数组索引，对应的是 columns配置的数组，从0开始
     */
    private int[] order;

    /*
     * 告诉后台列排序的方式， desc 降序 asc升序
     */
    private String order_dir;

    /*
     * columns 绑定的数据源，由 columns.dataOption 定义。
     */
    private String columns_data;

    /*
     * columns 的名字，由 columns.nameOption 定义。
     */
    private String columns_name;

    /*
     * 标记列是否能被搜索,为true代表可以，否则不可以，这个是由 columns.searchableOption 控制
     */
    private String columns_searchable;

    /*
     * 标记列是否能排序,为 true代表可以，否则不可以，这个是由 columns.orderableOption 控制
     */
    private boolean is_orderable;

    /*
     * 标记具体列的搜索条件
     */
    private String columns_search_value;

    /*
     * 特定列的搜索条件是否视为正则表达式， 如果为 true代表搜索的值是作为正则表达式处理，为 false则不是。
     * 注意：通常在服务器模式下对于大数据不执行这样的正则表达式，但这都是自己决定的
     */
    private boolean is_search_regex;

    /*------------------DT自动请求的参数(Sent parameters) end--------------------*/

    /*------------------服务器需要返回的数据(Returned data) begin--------------------*/

    /*
     * 必要。上面提到了，Datatables发送的draw是多少那么服务器就返回多少。
     * 这里注意，作者出于安全的考虑，强烈要求把这个转换为整形，即数字后再返回，而不是纯粹的接受然后返回，这是 为了防止跨站脚本（XSS）攻击。
     */
    // private int draw;

    /*
     * 必要。即没有过滤的记录数（数据库里总共记录数）
     */
    private int recordsTotal;

    /*
     * 必要。过滤后的记录数（如果有接收到前台的过滤条件，则返回的是过滤后的记录数）
     */
    private int recordsFiltered;

    /*
     * 必要。表中中需要显示的数据。这是一个对象数组，也可以只是数组， 区别在于 纯数组前台就不需要用 columns绑定数据，会自动按照顺序去显示
     * ，而对象数组则需要使用 columns绑定数据才能正常显示。 注意这个 data的名称可以由 ajaxOption 的
     * ajax.dataSrcOption 控制
     */
    private List<T> data;

    /*
     * 可选。你可以定义一个错误来描述服务器出了问题后的友好提示
     */
    private String error;

    /*-------------可选参数-----------------*/

    /*
     * 自动绑定到 tr节点上
     */
    private String dt_rowId;

    /*
     * 自动把这个类名添加到 tr
     */
    private String dt_rowClass;

    /*
     * 使用 jQuery.data() 方法把数据绑定到row中，方便之后用来检索（比如加入一个点击事件）
     */
    private Object dt_rowData;

    /*
     * 自动绑定数据到 tr上，使用 jQuery.attr() 方法，对象的键用作属性，值用作属性的值。 注意这个 需要 Datatables
     * 1.10.5+的版本才支持
     */
    private Object dt_rowAttr;

    /*-------------可选参数-----------------*/
    /*------------------服务器需要返回的数据(Returned data) end--------------------*/


    /*
     * 当前页码
     */
    private int page_num = 1;

    /*
     * 每页数据
     */
    private int page_size = 100;


    public DataTablePageUtil() {

    }

    public DataTablePageUtil(HttpServletRequest request) {
        //开始的数据行数
        String start = request.getParameter("start");
        //每页的数据数
        String length = request.getParameter("length");
        //DT传递的draw:
        String draw = request.getParameter("draw");

        this.setStart(Integer.parseInt(start));
        this.setLength(Integer.parseInt(length));
        this.setDraw(Integer.parseInt(draw));
        //计算页码
        this.page_num = (Integer.parseInt(start) / Integer.parseInt(length)) + 1;

    }

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public boolean isIs_search() {
		return is_search;
	}

	public void setIs_search(boolean is_search) {
		this.is_search = is_search;
	}

	public int[] getOrder() {
		return order;
	}

	public void setOrder(int[] order) {
		this.order = order;
	}

	public String getOrder_dir() {
		return order_dir;
	}

	public void setOrder_dir(String order_dir) {
		this.order_dir = order_dir;
	}

	public String getColumns_data() {
		return columns_data;
	}

	public void setColumns_data(String columns_data) {
		this.columns_data = columns_data;
	}

	public String getColumns_name() {
		return columns_name;
	}

	public void setColumns_name(String columns_name) {
		this.columns_name = columns_name;
	}

	public String getColumns_searchable() {
		return columns_searchable;
	}

	public void setColumns_searchable(String columns_searchable) {
		this.columns_searchable = columns_searchable;
	}

	public boolean isIs_orderable() {
		return is_orderable;
	}

	public void setIs_orderable(boolean is_orderable) {
		this.is_orderable = is_orderable;
	}

	public String getColumns_search_value() {
		return columns_search_value;
	}

	public void setColumns_search_value(String columns_search_value) {
		this.columns_search_value = columns_search_value;
	}

	public boolean isIs_search_regex() {
		return is_search_regex;
	}

	public void setIs_search_regex(boolean is_search_regex) {
		this.is_search_regex = is_search_regex;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getDt_rowId() {
		return dt_rowId;
	}

	public void setDt_rowId(String dt_rowId) {
		this.dt_rowId = dt_rowId;
	}

	public String getDt_rowClass() {
		return dt_rowClass;
	}

	public void setDt_rowClass(String dt_rowClass) {
		this.dt_rowClass = dt_rowClass;
	}

	public Object getDt_rowData() {
		return dt_rowData;
	}

	public void setDt_rowData(Object dt_rowData) {
		this.dt_rowData = dt_rowData;
	}

	public Object getDt_rowAttr() {
		return dt_rowAttr;
	}

	public void setDt_rowAttr(Object dt_rowAttr) {
		this.dt_rowAttr = dt_rowAttr;
	}

	public int getPage_num() {
		return page_num;
	}

	public void setPage_num(int page_num) {
		this.page_num = page_num;
	}

	public int getPage_size() {
		return page_size;
	}

	public void setPage_size(int page_size) {
		this.page_size = page_size;
	}
    
    
	
}
