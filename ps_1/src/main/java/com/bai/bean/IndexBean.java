package com.bai.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

import org.primefaces.context.RequestContext;

import com.bai.business.UserBusiness;
import com.bai.model.User;

@ManagedBean(eager = true)
public class IndexBean {

	public User user;

	UserBusiness userBusiness;

	@PostConstruct
	public void initialize() {
		user = new User();
		userBusiness = new UserBusiness();
	}

	public void register(ActionEvent event) {

		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage message = null;
		boolean registered = false;

		if (user.getName() != null && user.getPassword_hash() != null) {
			user.setSalt(user.getName().hashCode());
			userBusiness.addUser(user);
		}
		if (user != null) {
			registered = true;
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Zarejestrowano",
					user.getName() + " " + user.getName());

		}
		FacesContext.getCurrentInstance().addMessage(null, message);
		context.addCallbackParam("registered", registered);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
