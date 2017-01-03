package com.bai.ps.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bai.ps.business.UserBusiness;
import com.bai.ps.dao.UnregisteredUserDao;
import com.bai.ps.model.UnregisteredUser;
import com.bai.ps.model.User;
import com.bai.ps.model.UserPasswordMask;

/**
 * Servlet implementation class Login_ps4
 */
public class Login_ps4 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login_ps4() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		UserBusiness userBusiness = new UserBusiness();
		UnregisteredUserDao ub = new UnregisteredUserDao();

		char[] passwordMask = null;

		if (action.equals("Dalej")) {

			String login = request.getParameter("login");
			User user = userBusiness.getUserByName(login);
			if (user != null) {
				UserPasswordMask userPasswordMask = userBusiness.getUserPasswordMaskByUserId(user);
				passwordMask = userPasswordMask.getMask().toCharArray();
			} else {
				UnregisteredUser user2 = ub.getUserByName(login);
				if (user2 != null) {
					passwordMask = user2.getPassword_hash().toCharArray();
				} else {
					User newUnregistered = new User();
					newUnregistered.setName(login);
					ub.addUnregisteredUser(newUnregistered);
					passwordMask = ub.getUserByName(login).getPassword_hash().toCharArray();
				}
			}

			request.setAttribute("passwordMask", passwordMask);
			request.setAttribute("login", login);
			request.getRequestDispatcher("/loginMask_ps4.jsp").forward(request, response);

		} else if (action.equals("Zaloguj")) {

			StringBuilder result = new StringBuilder("");
			String login = request.getParameter("login");
			User user = userBusiness.getUserByName(login);

			if (user != null) {
				UserPasswordMask userPasswordMask = userBusiness.getUserPasswordMaskByUserId(user);
				passwordMask = userPasswordMask.getMask().toCharArray();

				for (int i = 0; i < passwordMask.length; i++) {
					if (passwordMask[i] == '1') {
						String passChar = request.getParameter("pass[" + i + "]");
						if (passChar != null && passChar.length() == 1) {
							result.append(passChar);
						}
					}
				}
				if (userBusiness.loginPwdMask(userPasswordMask, result.toString())) {
					User userLoged = userBusiness.login(user);

					if (userLoged != null) {
						userBusiness.changeUserMask(userPasswordMask);
						session.setAttribute("userObject", userLoged);
						session.setAttribute("user", userLoged.getName());
						session.setMaxInactiveInterval(300);
						response.sendRedirect("/BAI");
					} else {
						user = userBusiness.getUserByName(login);
						if (user.isAccountLocked()) {
							request.setAttribute("lock", "Konto zablokowane");
						} else {
							request.setAttribute("down", "Kolejna proba mozliwa:" + user.getAccountLoginBlocked());
						}
						request.getRequestDispatcher("/login_ps4.jsp").forward(request, response);
					}
				} else {
					user.setPassword_hash("");
					User userLoged = userBusiness.login(user);

					user = userBusiness.getUserByName(login);
					if (user.isAccountLocked()) {
						request.setAttribute("lock", "Konto zablokowane");
					} else {
						request.setAttribute("down", "Kolejna proba mozliwa:" + user.getAccountLoginBlocked());
					}
					request.getRequestDispatcher("/login_ps4.jsp").forward(request, response);
				}
			} else {
				user = new User();
				user.setName(login);
				user.setPassword_hash("");
				user = userBusiness.login(user);
				
				UnregisteredUser user2 = ub.getUserByName(login);
				if (user2 != null) {
					if (user2.isAccountLocked()) {
						request.setAttribute("lock", "Konto zablokowane");
					} else {
						request.setAttribute("down", "Kolejna proba mozliwa:" + user2.getAccountLoginBlocked());
					}
				}
				request.getRequestDispatcher("/login_ps4.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
