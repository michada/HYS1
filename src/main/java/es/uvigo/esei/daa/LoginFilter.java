package es.uvigo.esei.daa;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uvigo.esei.daa.dao.DAOException;
import es.uvigo.esei.daa.dao.UsersDAO;

@WebFilter(urlPatterns = { "/*" })
public class LoginFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		final HttpServletResponse httpResponse = (HttpServletResponse) response;

		try {
			if (isLogoutPath(httpRequest)) {
				removeTokenCookie(httpResponse);
				redirectToIndex(httpRequest, httpResponse);
			} else if (isPostLoginPath(httpRequest)) {
				if (checkLogin(httpRequest, httpResponse)) {
					redirectToShowEvents(httpRequest, httpResponse);
				}
				else{
					redirectToFailLogin(httpRequest, httpResponse);
				}
			} else if (isIndexPath(httpRequest) || checkToken(httpRequest)
					|| isPathInWhiteList(httpRequest)) {
				chain.doFilter(request, response);
			} else {
				redirectToIndex(httpRequest, httpResponse);
			}
		} catch (IllegalArgumentException iae) {
			httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
		} catch (DAOException e) {
			httpResponse
					.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	private boolean isPathInWhiteList(HttpServletRequest httpRequest) {
		return isCssPath(httpRequest) || isFontAwesomePath(httpRequest)
				|| isFontPath(httpRequest) || isImgPath(httpRequest)
				|| isImportsPath(httpRequest) || isJsPath(httpRequest)
				|| isGetLoginPath(httpRequest) || isPostLoginPath(httpRequest)
				|| isShowEventsPath(httpRequest) || isRestPath(httpRequest);
	}

	private boolean isLogoutPath(HttpServletRequest request) {
		return request.getServletPath().equals("/logout");
	}

	private boolean isIndexPath(HttpServletRequest request) {
		return request.getServletPath().equals("/index.jsp");
	}

	private boolean isRestPath(HttpServletRequest request) {
		return request.getServletPath().startsWith("/rest");
	}

	private void redirectToIndex(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.sendRedirect(request.getContextPath());
	}

	private void redirectToShowEvents(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.sendRedirect(request.getContextPath() + "/showEvents.jsp");
	}
	
	private void redirectToFailLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.sendRedirect(request.getContextPath() + "/login.jsp?fail=true");
	}

	private void continueWithRedirect(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String redirectPath = request.getRequestURI();
		if (request.getQueryString() != null)
			redirectPath += request.getQueryString();

		response.sendRedirect(redirectPath);
	}

	private void removeTokenCookie(HttpServletResponse response) {
		final Cookie cookie = new Cookie("token", "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

	private boolean checkLogin(HttpServletRequest request,
			HttpServletResponse response) throws DAOException {
		final String login = request.getParameter("login");
		final String password = request.getParameter("password");

		if (login != null && password != null) {
			final String token = new UsersDAO().checkLogin(login, password);

			if (token == null) {
				return false;
			} else {
				response.addCookie(new Cookie("token", token));

				return true;
			}
		} else {
			return false;
		}
	}

	private boolean checkToken(HttpServletRequest request) throws DAOException,
			IllegalArgumentException {
		final Cookie[] cookies = Optional.ofNullable(request.getCookies())
				.orElse(new Cookie[0]);

		for (Cookie cookie : cookies) {
			if ("token".equals(cookie.getName())) {
				final String token = new UsersDAO().checkToken(cookie
						.getValue());

				return token != null;
			}
		}

		return false;
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	private boolean isCssPath(HttpServletRequest request) {
		return request.getServletPath().startsWith("/css");
	}

	private boolean isFontAwesomePath(HttpServletRequest request) {
		return request.getServletPath().startsWith("/font-awesome");
	}

	private boolean isFontPath(HttpServletRequest request) {
		return request.getServletPath().startsWith("/fonts");
	}

	private boolean isImgPath(HttpServletRequest request) {
		return request.getServletPath().startsWith("/img");
	}

	private boolean isImportsPath(HttpServletRequest request) {
		return request.getServletPath().startsWith("/imports");
	}

	private boolean isJsPath(HttpServletRequest request) {
		return request.getServletPath().startsWith("/js");
	}

	private boolean isGetLoginPath(HttpServletRequest request) {
		return request.getServletPath().equals("/login.jsp") && request.getMethod()=="GET";
	}
	
	private boolean isPostLoginPath(HttpServletRequest request) {
		return request.getServletPath().equals("/login.jsp") && request.getMethod()=="POST";
	}

	private boolean isShowEventsPath(HttpServletRequest request) {
		return request.getServletPath().equals("/showEvents.jsp");
	}
}
