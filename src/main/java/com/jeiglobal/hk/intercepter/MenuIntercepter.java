package com.jeiglobal.hk.intercepter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jeiglobal.hk.domain.common.AuthMemberInfo;
import com.jeiglobal.hk.domain.common.GlobalMenu;
import com.jeiglobal.hk.service.MenuService;

public class MenuIntercepter extends HandlerInterceptorAdapter{
	
	@Autowired
	private MenuService menuService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) authentication.getPrincipal();

		List<GlobalMenu> menuList = menuService.menuList(0,authMemberInfo.getJisaCD(),authMemberInfo.getEmpKeyLvCD(),authMemberInfo.getDepMngCD(),"1");
		
		List<GlobalMenu> leftMenuList = new ArrayList<GlobalMenu>();
		
		if(menuList == null){
			if(authentication.getAuthorities().contains("SUPERADMIN")){
				return true;
			}
			
			return false;
		}else{
			String menuCode = "";
			String menuFirstCode = "";
			String menuTwoCode = "";
			String menuThreeCode = "";
			String menuFourCode = "";
			
			AntPathMatcher ant = new AntPathMatcher();
			
			if("ROOT".equals(menuList.get(0).getmMenuName())){
				menuList.remove(0);
			}
			
			for (GlobalMenu globalMenu : menuList) {
				if(ant.match(globalMenu.getmAntPattern(),request.getRequestURI())){
					menuCode = globalMenu.getmMenuCode();
				}
			}
			
			int menuCodeCnt = menuCode.length();
			
			if(!menuCode.isEmpty()){
				if(menuCodeCnt == 1){
					menuFirstCode = menuCode.substring(0,1);
				}else if(menuCodeCnt == 3){
					menuFirstCode = menuCode.substring(0,1);
					menuTwoCode = menuCode.substring(0,3);
				}else if(menuCodeCnt == 5){
					menuFirstCode = menuCode.substring(0,1);
					menuTwoCode = menuCode.substring(0,3);
					menuThreeCode =  menuCode.substring(0,5);
				}else{
					menuFirstCode = menuCode.substring(0,1);
					menuTwoCode = menuCode.substring(0,3);
					menuThreeCode =  menuCode.substring(0,5);
					menuFourCode =  menuCode.substring(0,7);
				}	
				
				for (GlobalMenu globalMenu : menuList) {
					String menuCodeTemp = globalMenu.getmMenuCode();
					if(!menuCodeTemp.isEmpty() && menuCodeTemp.startsWith(menuFirstCode)){
						leftMenuList.add(globalMenu);
					}
				}
			}else{
				boolean t = false;
				for (GrantedAuthority globalMenu : authentication.getAuthorities()) {
					if("SUPERADMIN".equals(globalMenu.getAuthority())){
						t = true;
					}
				}
				if(t){
					System.out.println("문제 허용할 수 없는 URL....그러나 슈퍼관리자");
					return true;
				}else{
					System.out.println("문제 허용할 수 없는 URL....");
					return false;
				}
			}
			
			request.setAttribute("leftMenuList",leftMenuList);
			request.setAttribute("menuCode",menuCode);
			request.setAttribute("menuFirstCode",menuFirstCode);
			request.setAttribute("menuTwoCode",menuTwoCode);
			request.setAttribute("menuThreeCode",menuThreeCode);
			request.setAttribute("menuFourCode",menuFourCode);
			return true;
		}
	}
	
}
