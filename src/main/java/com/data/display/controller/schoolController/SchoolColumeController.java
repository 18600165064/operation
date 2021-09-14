package com.data.display.controller.schoolController;


import com.alibaba.fastjson.JSON;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.school.SchoolColume;
import com.data.display.service.schoolService.SchoolColumeService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/shoolColume")
public class SchoolColumeController {

    @Autowired
    private SchoolColumeService schoolColumeService;

    /**
     * 列表页
     * @return
     */
    @RequestMapping("/schoolColumeMenu")
    public String schoolColumeMenu() {
        return "/school/schoolColumeMenu";
    }

    @RequestMapping("/addSchoolColume")
    public String addSchoolColume(Model model) {
        return "/school/addSchoolColumeMenu";
    }

    @RequestMapping("/editShoolColume")
    public String editShoolColume(Model model,String id) {
        model.addAttribute("id", id);
        return "/school/editShoolColumeMenu";
    }

    @RequestMapping("/getShoolColumeList")
    @ResponseBody
    public String getShoolColumeList(SchoolColume schoolColume) {
        List<SchoolColume> list = schoolColumeService.getShoolColumeList(schoolColume);
        return JSON.toJSONString(list);
    }

    @RequestMapping("/getColumeData")
    @ResponseBody
    public String getColumeData(DataTableDTO dataTableDTO, SchoolColume schoolColume) {
        Page<SchoolColume> list = schoolColumeService.getColumeData(dataTableDTO,schoolColume);
        DataTableResult dataTableResult = new DataTableResult(dataTableDTO.getDraw(),list.getTotal(),list.getTotal(),list.getResult());
        return JSON.toJSONString(dataTableResult);
    }

    @RequestMapping("/selectById")
    @ResponseBody
    public String selectById(String id) {
        SchoolColume schoolColume = schoolColumeService.selectById(id);
        return JSON.toJSONString(schoolColume);
    }

    @RequestMapping("/addSchoolColumeData")
    @ResponseBody
    public String addSchoolColumeData(SchoolColume schoolColume) {
        DataTableResult dataTableResult = schoolColumeService.addSchoolColumeData(schoolColume);
        return JSON.toJSONString(dataTableResult);
    }

    @RequestMapping("/editShoolColumeData")
    @ResponseBody
    public String editShoolColumeData(SchoolColume schoolColume) {
        DataTableResult dataTableResult = schoolColumeService.editShoolColumeData(schoolColume);
        return JSON.toJSONString(dataTableResult);
    }

}
