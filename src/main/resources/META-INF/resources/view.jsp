<%@page import="java.io.Serializable"%>
<%@page import="com.liferay.portal.kernel.dao.orm.QueryUtil"%>
<%@page import="com.liferay.portal.kernel.model.User"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.service.RoleLocalServiceUtil"%>
<%@ include file="/init.jsp" %>
<%@page import="java.util.ArrayList" %>
<%@page import="javax.portlet.RenderRequest"%>
<%@page import="com.liferay.portal.kernel.model.User"%>
<%@page import="javax.portlet.PortletSession"%>
<%@page import="com.liferay.portal.kernel.theme.ThemeDisplay" %>
<%@page import="com.liferay.portal.kernel.model.UserGroup" %>
<%@page import="com.liferay.portal.kernel.service.UserGroupLocalServiceUtil" %>
<%@page import="com.liferay.portal.kernel.service.UserLocalServiceUtil" %>
<%@page import="com.liferay.portal.kernel.util.WebKeys" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<portlet:actionURL var="sendConvenio" name="convenio" />


<aui:form action="${sendConvenio}" method="POST">
	<aui:input name="Convenio" type="text" ></aui:input>
	<aui:button type="submit" value="Enviar"></aui:button>
</aui:form>
		
<%-- <%
ThemeDisplay td = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
String groupName = "Desarrolladores";

UserGroup ug = UserGroupLocalServiceUtil.getUserGroup(td.getCompanyId(), groupName); // get groupName from preference
List<User> listUsers = UserLocalServiceUtil.getUsers(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

String[] v;
	if(listUsers != null){
		for(User use : listUsers){
			v = (String[] )use.getExpandoBridge().getAttribute("Sucursal");
			String loc = (v.length>0)?v[0]:"";
%>
	<b>Name: </b><%=use.getLastName() + ", " + use.getFirstName() + "  "+ loc %><br />
	<%
		}
	}
	%> --%>