package com.consistent.cuervo.portlet;

import com.consistent.cuervo.constants.UsuariosPortletKeys;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserGroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author bernardohernandez
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp", "javax.portlet.name=" + UsuariosPortletKeys.Usuarios,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class UsuariosPortlet extends MVCPortlet {

	private static final Log log = LogFactoryUtil.getLog(UsuariosPortlet.class);

	@Override
	public void render(RenderRequest arg0, RenderResponse arg1) throws IOException, PortletException {
		// TODO Auto-generated method stub
		
		super.render(arg0, arg1);
	}
	

	public void convenio(ActionRequest request, ActionResponse response) throws PortalException {
		log.info("Metodo convenio");
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		String destino = (String)themeDisplay.getSiteGroup().getExpandoBridge().getAttribute("convenios");
		String[] arryDest = destino.split(",");
		log.info(destino);
		log.info(arryDest.length);
		
		if (ParamUtil.getString(request, "Convenio") != null && !ParamUtil.getString(request, "Convenio").isEmpty()) {
			final String convenio = ParamUtil.getString(request, "Convenio");
			log.info("Tipo de grupo: " + convenio);
			try {
				final long companyId = PortalUtil.getDefaultCompanyId();

				List<User> users = UserLocalServiceUtil.getUsers(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
				for (User user : users) {
					if (user != null) {
						try {
							log.info("Nombre de usuario: " + user.getFullName());
							Long groupUserId = UserGroupLocalServiceUtil.getUserGroup(companyId, validConvenio(user.getExpandoBridge().getAttribute("Clave_Convenio").toString())).getUserGroupId();
							log.info(groupUserId);
							UserLocalServiceUtil.addUserGroupUser(groupUserId, user.getUserId());
						} catch (Exception e) {
							// TODO: handle exception
							log.info("No tiene convenio");
							e.getMessage();
							
						}
								
								
						
						 
					}
				}
			} catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
				// TODO: handle exception
			}
		} else {
			log.info("No tiene valor");
		}

	}

	private String validConvenio(String convenio) {
		switch (convenio) {
		case "1":
			return "EJECUTIVOS";
		case "2":
			return "NO SINDICALIZADOS";
		case "3":
			return "PROMOTORES";
		case "5":
			return "SINDICALIZADOS LA LAJA";
		case "6":
			return "SINDICALIZADOS EVENTUALES EDISA";
		case "7":
			return "SINDICALIZADOS PLANTA EDISA";
		case "8":
			return "SINDICALIZADOS LA ROJEÑA";
		case "9":
			return "JUBILADOS LA ROJEÑA";
		case "11":
			return "EJECUTIVOS B";
		case "12":
			return "NO SINDICALIZADOS B";
		case "13":
			return "SEGURIDAD CORPORATIVA";
		default:
			break;
		}

		return "";
	}
}