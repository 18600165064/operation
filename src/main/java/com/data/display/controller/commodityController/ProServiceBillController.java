package com.data.display.controller.commodityController;

import com.alibaba.fastjson.JSON;
import com.data.display.mapper.commodityMapper.ProServiceBillMapper;
import com.data.display.model.commodity.ProServiceBill;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.service.commodityService.ProServiceBillService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/proServiceBill")
public class ProServiceBillController {

    @Autowired
    private ProServiceBillService proServiceBillService;

    @Resource
    private ProServiceBillMapper proServiceBillMapper;

    @RequestMapping("/proServiceBillMenu")
    public String skuMenu() {
        return "/commodity/proServiceBillMenu";
    }


    @RequestMapping("/getProServiceBillData")
    @ResponseBody
    public String getProServiceBillData(DataTableDTO dataTableDTO, ProServiceBill proServiceBill) {
        Page<ProServiceBill> list = proServiceBillService.getProServiceBillData(dataTableDTO,proServiceBill);
        List<Map<String,Object>> mapList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ProServiceBill proServiceBill2 = new ProServiceBill();
            proServiceBill2.setOrder_no(list.get(i).getOrder_no());
            List<ProServiceBill> pList = proServiceBillMapper.getProServiceByOrderNo(list.get(i));
            Map<String,Object> map = new HashMap<>();
            for (int j = 0; j < pList.size(); j++) {
                String types = "-";
                if(pList.get(j).getType() == 0){
                    types = "+";
                }
                map.put("orderNo",pList.get(j).getOrder_no());
                map.put("userId",pList.get(j).getUser_id());
                map.put("createTime",pList.get(j).getCreate_time());
                if(pList.get(j).getStatus() == 1){
                    map.put("spce",types+pList.get(j).getAmount());
                }if(pList.get(j).getStatus() == 2){
                    map.put("jsfx",types+pList.get(j).getAmount());
                }if(pList.get(j).getStatus() == 3){
                    map.put("hxfx",types+pList.get(j).getAmount());
                }if(pList.get(j).getStatus() == 4){
                    map.put("fwf",types+pList.get(j).getAmount());
                }if(pList.get(j).getStatus() == 5){
                    map.put("yf",types+pList.get(j).getAmount());
                }if(pList.get(j).getStatus() == 6){
                    map.put("ghj",types+pList.get(j).getAmount());
                }if(pList.get(j).getStatus() == 7){
                    map.put("tk",types+pList.get(j).getAmount());
                }
            }
            mapList.add(map);
        }
        DataTableResult dataTableResult = new DataTableResult(dataTableDTO.getDraw(),list.getTotal(),list.getTotal(),list.getResult());
        dataTableResult.setData(mapList);
        return JSON.toJSONString(dataTableResult);
    }

}
