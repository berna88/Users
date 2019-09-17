package com.consistent.cuervo.portlet;

import com.consistent.cuervo.constants.UsuariosPortletKeys;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

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
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + UsuariosPortletKeys.Usuarios,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class UsuariosPortlet extends MVCPortlet {
	
	private static final Log log = LogFactoryUtil.getLog(UsuariosPortlet.class);
	
	@Override
		public void render(RenderRequest arg0, RenderResponse arg1) throws IOException, PortletException {
			// TODO Auto-generated method stub
		List<Organization> organizations = OrganizationLocalServiceUtil.getOrganizations(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		arg0.setAttribute("Logos", organizations);
			super.render(arg0, arg1);
		}
	
	
	public void convenio(ActionRequest request, ActionResponse response) throws PortalException {
		log.info("Metodo convenio");
		List<Organization> organizations = OrganizationLocalServiceUtil.getOrganizations(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		for (Organization organization : organizations) {
			log.info("<-- inicio -->");
			log.info("Logo: "+organization.getLogoId());
			log.info(organization.getOrganizationId());
			log.info(organization.getName());
			List<User> users = UserLocalServiceUtil.getOrganizationUsers(organization.getOrganizationId());
			for (User user : users) {
				log.info("Nombre"+user.getFullName());
			}
			log.info("<-- Fin -->");
		}
		
		final long companyIds = PortalUtil.getDefaultCompanyId();
		Long organizationId = (long) 34328;
		Group group = GroupLocalServiceUtil.getOrganizationGroup(companyIds, organizationId);
		LayoutSet layoutSet = LayoutSetLocalServiceUtil.getLayoutSet(group.getGroupId(), true);
		if(layoutSet.isLogo()) {
			
			log.info("Live logo"+layoutSet.getLiveLogoId());
			log.info("Live logoId"+layoutSet.getLogoId());
		}else {
			log.info(
                    "private community does  not have logo");
		}
		
		List<User> users2 = UserLocalServiceUtil.getOrganizationUsers(organizationId);
		for (User user : users2) {
			log.info("user"+user.getFullName());
		}
		if(ParamUtil.getString(request, "Convenio")!=null && !ParamUtil.getString(request, "Convenio").isEmpty()) {
			final String convenio = ParamUtil.getString(request, "Convenio");
			log.info("Tipo de grupo: "+convenio);
			try {
				final String groupName = "Desarrolladores";
				final long companyId = PortalUtil.getDefaultCompanyId();
				
				System.out.println(UserGroupLocalServiceUtil.getUserGroup(companyId, groupName).getName());
				System.out.println(UserGroupLocalServiceUtil.getUserGroup(companyId, groupName).getGroupId());
				//List<Group> groups = GroupLocalServiceUtil.getGroups(0,GroupLocalServiceUtil.getGroupsCount());
				List<User> users = UserLocalServiceUtil.getUsers(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
				for (User user : users) {
					if(user!=null) {
							if(user.isActive()) {
								if(!user.isDefaultUser()) {
									System.out.println(user.getFullName());
								}
							}
							//UserLocalServiceUtil.addUserGroupUser(UserGroupLocalServiceUtil.getUserGroup(companyId, groupName).getUserGroupId(), user.getUserId());
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}else {
			log.info("No tiene valor");
		}
		
		
	}
	
	private String validConvenio(String convenio) {
		
		return "";
	}
}