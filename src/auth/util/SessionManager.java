package auth.util;

import java.util.Enumeration;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import auth.UserLoggableInterface;
import domain.Roles;

public class SessionManager {

	public static void logout()
	{
		HttpSession session = getSession();
		session.invalidate();
	}

	public static void setUser(UserLoggableInterface user)
	{
		HttpSession session = getSession();
		session.setAttribute("user", user);
	}

	public static UserLoggableInterface getUser()
	{
		if(!hasUser()){
			return null;
		}

		HttpSession session = getSession();
		return (UserLoggableInterface) session.getAttribute("user");
	}

	public static boolean isUserAdministrator() {

		UserLoggableInterface user = SessionManager.getUser();
		return user != null && user.getRole() == Roles.Administrator;
	}

	public static boolean isNotUserAdministrator()
	{
		UserLoggableInterface user = SessionManager.getUser();
		return user == null || user.getRole() != Roles.Administrator;
	}

	public static boolean isUserCustomer()
	{
		UserLoggableInterface user = SessionManager.getUser();
		return user != null && user.getRole() == Roles.Customer;
	}

	public static boolean hasUser()
	{
		HttpSession session = getSession();
		if(session == null || session.isNew()){
			return false;
		}

		Enumeration<String> keys = session.getAttributeNames();

		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			if(!key.equals("user")){
				continue;
			}

			return true;
		}

		return false;
	}

	private static HttpSession getSession()
	{
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}
}