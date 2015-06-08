package com.jeiglobal.hk.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeiglobal.hk.domain.common.GlobalMenu;
import com.jeiglobal.hk.service.MenuService;



@Controller
@RequestMapping(value="/adminManager")
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	
	@RequestMapping(value="/menuIndex")
	public String menuIndex(){
		return "adminManager/menuIndex";
	}
	
	@RequestMapping(value="/menuList")
	public String menuList(Model model,@RequestParam("mJisaCD") String mJisaCD
			,@RequestParam("mEmpKeyLvCD") String mEmpKeyLvCD,@RequestParam("mDepMngCD") String mDepMngCD){
		model.addAttribute("menuList",menuService.menuList(0,mJisaCD,mEmpKeyLvCD,mDepMngCD,"1"));
		return "adminManager/menuList";
	}
	
	@RequestMapping(value="/menuContent")
	public String content(Model model,@RequestParam("mJisaCD") String mJisaCD
			,@RequestParam("mEmpKeyLvCD") String mEmpKeyLvCD,@RequestParam("mDepMngCD") String mDepMngCD) {
		model.addAttribute("menuList",menuService.menuList(0,mJisaCD,mEmpKeyLvCD,mDepMngCD,"1"));
		return "adminManager/content";
	}
	
	@RequestMapping(value="/menuChange/{mIdx}",method=RequestMethod.GET)
	public String menuChangeList(Model model,@PathVariable long mIdx){
		model.addAttribute("menuList",menuService.changeList(mIdx));
		return "adminManager/changeList";
	}
	
	@RequestMapping(value="/menuSave.json",method=RequestMethod.POST,produces="application/json;charset=UTF-8;")
	@ResponseBody
	public String menuSave(GlobalMenu globalMenu){
		String msg = "";
		msg = menuService.create(globalMenu);		
		return msg;
	}
	
	@RequestMapping(value="/menuDelete/{mIdx}",method=RequestMethod.DELETE,produces="application/json;charset=UTF-8;")
	@ResponseBody
	public String menuDelete(@PathVariable long mIdx){
		String msg = "";
		msg = menuService.delete(mIdx);		
		return msg;
	}
	
	@RequestMapping(value="/menuContent/{mIdx}",method=RequestMethod.GET,produces="application/json;charset=UTF-8;")
	@ResponseBody
	public GlobalMenu menuReadOne(@PathVariable long mIdx){
		return menuService.readOne(mIdx);
	}
	
	@RequestMapping(value="/menuSave.json",method=RequestMethod.PUT,produces="application/json;charset=UTF-8;")
	@ResponseBody
	public String menuUpdate(GlobalMenu globalMenu){
		String msg = "";
		msg = menuService.update(globalMenu);	
		return msg;
	}
	
	@RequestMapping(value="/menuChange",method=RequestMethod.PUT,produces="application/json;charset=UTF-8;")
	@ResponseBody
	public String menuChange(String lan){
		String msg = "";
		msg = menuService.change(lan);	
		return msg;
	}
	
}
