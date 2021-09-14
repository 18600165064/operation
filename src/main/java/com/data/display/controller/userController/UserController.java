package com.data.display.controller.userController;

import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.alibaba.fastjson.JSON;
import com.data.display.mapper.userMapper.UserMapper;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.supplier.SupplierInfo;
import com.data.display.model.user.SysUser;
import com.data.display.service.userService.UserService;
import com.data.display.util.UserUtil;
import com.github.pagehelper.Page;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Resource
	private UserMapper userMapper;



	@RequestMapping("/test")
    public String test() {
        return "/user/test";
    }
	
	/**
	 * 用户列表页
	 * @return
	 */
	@RequestMapping("/userMenu")
    public String roleList(Model model,String id) {
		SysUser user = UserUtil.getUserMessage();
		model.addAttribute("id",user.getId().toString());
        return "/user/userMenu";
    }
	
	
	@RequestMapping("/addUser")
    public String addUser() {
        return "/user/addUser";
    }

	/**
	 * 跳转到角色列表
	 * @param attr
	 * @param userId
	 * @return
	 */
	@RequestMapping("/forwardRoleList")
    public ModelAndView forwardRoleList(RedirectAttributes attr,String userId) {
		 attr.addAttribute("userId", userId);
		return new ModelAndView("forward:/user/roleList");
    }
	
	/**
	 * 跳转到修改密码页
	 * @return
	 */
	@RequestMapping("/forwardUpdatePass")
    public ModelAndView forwardUpdatePass() {
		return new ModelAndView("forward:/editPassword");
    }
	
	/**
	 * 跳转到修改页
	 * @param userId
	 * @return
	 */
	@RequestMapping("/update")
    public String update(Model model,String userId) {
		model.addAttribute("userId", userId);
		return "/user/editUser";
    }

	/**
	 * 根据名字获取到相对应的所有用户
	 * @param dataTableDTO
	 * @param nickName
	 * @return
	 */
	@RequestMapping("/getDatasByNickname")
    @ResponseBody
    public String getDatasByNickname(DataTableDTO dataTableDTO,String nickName) {
        Page<Map<String, Object>> list = userService.getDatasByNickname(dataTableDTO,nickName);
        DataTableResult dataTableResult = new DataTableResult(dataTableDTO.getDraw(),list.getTotal(),list.getTotal(),list.getResult());
        return JSON.toJSONString(dataTableResult);
    }

	/**
	 * 用户列表信息
	 * @param dataTableDTO
	 * @param userName
	 * @return
	 */
	@RequestMapping("/getUserData")
    @ResponseBody
    public String getUserData(DataTableDTO dataTableDTO,String userName) {
        Page<Map<String, Object>> page = userService.getUserData(dataTableDTO,userName);
		DataTableResult dataTableResult = new DataTableResult(dataTableDTO.getDraw(), page.getTotal(), page.getTotal(), page.getResult());
        return JSON.toJSONString(dataTableResult);
    }
	
	/**
	 * 新增用户
	 * @param
	 * @return
	 */
	@RequestMapping("/addUserData")
    @ResponseBody
    public String addUserData(SupplierInfo supplierInfo,String types) {
		DataTableResult dataTableResult = userService.addUserData(supplierInfo,types);
        return JSON.toJSONString(dataTableResult);
    }
	
	/**
	 * 根据ID获取用户
	 * @param id
	 * @return
	 */
	@RequestMapping("/getUserById")
    @ResponseBody
	public String getUserById(String id){
		SysUser user = userMapper.findByUserId(id);
		return JSON.toJSONString(user);
	}
	
	/**
	 * 修改
	 * @param user
	 * @return
	 */
	@RequestMapping("/updateUser")
    @ResponseBody
	public String updateUser(SysUser user){
		DataTableResult dataTableResult = userService.updateUser(user);
		return JSON.toJSONString(dataTableResult);
	}


}
