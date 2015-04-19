package com.zj.mvcapp.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zj.mvcapp.Dao.CriteriaCustomer;
import com.zj.mvcapp.Dao.CustomerDao;
import com.zj.mvcapp.Dao.impl.CustomerDaoImpl;
import com.zj.mvcapp.domain.Customer;

public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CustomerDao customerDao = new CustomerDaoImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//1. ��ȡ ServletPath�� /add.do
		String servletPath = request.getServletPath();
		
		//2. ȥ�� / �� .do, �õ������� add �������ַ���
		String methodName = servletPath.substring(1);
		methodName = methodName.substring(0, methodName.length() - 3);
		
		try {
			//3. ���÷����ȡ methodName ��Ӧ�ķ���
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class,
					HttpServletResponse.class);
			//4. ���÷�����ö�Ӧ�ķ���
			method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
	
	}

	@SuppressWarnings("unused")
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		customerDao.delete(idStr);
		response.sendRedirect("query.do");
	}

	@SuppressWarnings("unused")
	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		
		CriteriaCustomer cc = new CriteriaCustomer( id ,name, address, phone);
		//1. 调用 CustomerDAO 的 getAll() 得到 Customer 的集合
//		List<Customer> customers = customerDao.getAll();
		List<Customer> customers = customerDao.getForListWithCriteriaCustomer(cc);
		
		//2. 把Customer 的集合放入到 request 中
		request.setAttribute("customers", customers);
		
		//3. 转发页面到 index.jsp
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	@SuppressWarnings("unused")
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 获取表单参数: name, address, phone
		String idStr = request.getParameter("id");
		String name = request.getParameter("name");
		String ageStr = request.getParameter("age");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		
		int age = 0;
		//2. 检验 name 是否已经被占用:
		//2.1 调用 CustomerDAO 的 getCountWithName(String name) 获取 name 在数据库中是否存在
		//2.2 若返回值大于 0, 则响应 newCustomer.jsp 页面
		//2.2.1 要求在 newCustomer.jsp 页面显示一个错误:用户 name 已经被占用，请重新选择！
		
		//2.2.2 newCustomer.jsp 的表单可以回显.
		
		//3. 把表单封装为一个 Customer 对象的 Customer
		try {
			System.out.println("idStr:"+idStr);
			if (idStr == "") {
				request.setAttribute("message", " Id 号不能为空，请重新选择！");
				request.getRequestDispatcher("/addCustomer.jsp").forward(request, response);
				return;
			}
			long count = customerDao.getCountWithId(idStr);
			if (count > 0) {
				request.setAttribute("message", " Id 号 " + idStr + " 已经被占用，请重新选择！");
				request.getRequestDispatcher("/addCustomer.jsp").forward(request, response);
				return;
			}
			age = Integer.parseInt(ageStr);
			Customer customer = new Customer(idStr, name, age, address, phone);
				//4. 调用 CustomerDAO 的 save(Customer customer) 执行保存操作
			customerDao.save(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//5.重定向到 success.jsp 页面
		response.sendRedirect(request.getContextPath() + "/success.jsp");
	}

	@SuppressWarnings("unused")
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 获取请求参数: id, name, address, phone, oldName
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String ageStr = request.getParameter("age");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		
		//2. 对 id 进行验证
		//2.1 比较 idStr 和 oldId 是否相同，若相同，说明 idStr 可用, 若不相同...
		//2.2 若返回值大于 0, 则响应 updatecustomer.jsp 页面: 通过转发方式来响应 newCustomer.jsp
		//2.2.1 在 updateCustomer.jsp 页面显示一个错误消息: id 号 XX 已经被占用，请重新选择!
		
		Customer customer = new Customer(id, name, Integer.parseInt(ageStr), address, phone);
		customerDao.update(customer);
		response.sendRedirect("query.do");
	}
	
	@SuppressWarnings("unused")
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forwordPath = "/error.jsp";
		
		//1. 获取请求参数 id
		String idStr = request.getParameter("id");
		
		//2. 调用 CustomerDAO.getId(id) 获取和 id 对应的 customer 对象
		Customer customer = customerDao.get(idStr);
		try {
			if (customer != null) {
				forwordPath = "/UpdateCustomer.jsp";
				//3. 将 customer 放入 request 中
				request.setAttribute("customer", customer);
			}
		} catch (Exception e) {
		}	
		//4. 响应 updateCustomer.jsp , 转发
		request.getRequestDispatcher(forwordPath).forward(request, response);
	}
}
