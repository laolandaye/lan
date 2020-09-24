package cn.itcast.controller.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.domain.Account;
import cn.itcast.exception.AccountException;
import cn.itcast.service.AccountService;

public class AccountServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");

		String action = request.getParameter("action");
		if ("pay".equals(action)) {
			doPay(request, response);
		}
		
		if ("findAll".equals(action)) {
			doFindAll(request,response);
		}
		
		if ("account".equals(action)) {
			doAccount(request,response);
		}
	}

	

	private void doAccount(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.sendRedirect(request.getContextPath()+"/account.jsp");
	}



	public void doPay(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.得到请求参数
		String accountIn = request.getParameter("accountin");
		String accountOut = request.getParameter("accountout");
		
		double money = Double.parseDouble(request.getParameter("money"));
		
		// 2.调用service,完成汇款操作
		AccountService service = new AccountService();
		
		service.account(accountIn, accountOut, money);
		
		response.sendRedirect("account?action=findAll");
		
	}
	
	
	
	private void doFindAll(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException {
		AccountService service = new AccountService();
		
		List<Account> list = service.findAll();
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("show.jsp").forward(request, response);
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
