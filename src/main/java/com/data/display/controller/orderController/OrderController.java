package com.data.display.controller.orderController;

import com.alibaba.fastjson.JSON;
import com.data.display.mapper.orderMapper.OrderDetailMapper;
import com.data.display.mapper.orderMapper.OrderMapper;
import com.data.display.mapper.richMapper.InformationMapper;
import com.data.display.mapper.supplierMapper.SupplierInfoMapper;
import com.data.display.mapper.userMapper.UserAddressMapper;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.order.Order;
import com.data.display.model.order.OrderDetail;
import com.data.display.model.rich.Information;
import com.data.display.model.supplier.SupplierInfo;
import com.data.display.model.user.SysUser;
import com.data.display.service.orderService.OrderService;
import com.data.display.util.*;
import com.github.pagehelper.Page;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.*;

@Controller
@RequestMapping("/order")
public class OrderController {

    private static Logger _log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
	private OrderService orderService;

    @Resource
    private OrderDetailMapper orderDetailMapper;

    @Resource
    private UserAddressMapper userAddressMapper;

    @Resource
    private SupplierInfoMapper supplierInfoMapper;

    @Resource
    private InformationMapper informationMapper;

    @Autowired
    private DeliveryController deliveryController;

    @Resource
    private OrderMapper orderMapper;

    /**
     * 跳转到订单列表页
     *
     * @return
     */
	@RequestMapping("/orderMenu")
    public String roleList() {
        return "/order/orderMenu";
    }
	
	/**
	 * 审核退款
	 * @return
	 */
	@RequestMapping("/refundOrderMenu")
    public String refundOrderMenu(Model model,String status) {
		model.addAttribute("status", status);
        return "/order/refundOrderMenu";
    }
	
	@RequestMapping("/getOrderData")
    @ResponseBody
    public String getOrderData(DataTableDTO dataTableDTO,Order order) throws Exception {
        Page<Order> list = orderService.getOrderData(dataTableDTO,order);
        DataTableResult dataTableResult = new DataTableResult(dataTableDTO.getDraw(),list.getTotal(),list.getTotal(),list.getResult());
        return JSON.toJSONString(dataTableResult);
    }
	
	@RequestMapping("/getOtherData")
    @ResponseBody
    public String getOtherData(String id) {
        Map<String,Object> list = orderService.getOtherData(id);
        return JSON.toJSONString(list);
    }
	
	
	@RequestMapping("/getOrderByAfterStatus")
    @ResponseBody
    public String getOrderByAfterStatus(DataTableDTO dataTableDTO,Order order) {
        Page<Order> list = orderService.getOrderByAfterStatus(dataTableDTO,order);
        DataTableResult dataTableResult = new DataTableResult(dataTableDTO.getDraw(),list.getTotal(),list.getTotal(),list.getResult());
        return JSON.toJSONString(dataTableResult);
    }
	
	@RequestMapping("/getOrderByAfterStatus2")
    @ResponseBody
    public String getOrderByAfterStatus2(DataTableDTO dataTableDTO,Order order) {
        Page<Order> list = orderService.getOrderByAfterStatus2(dataTableDTO,order);
        DataTableResult dataTableResult = new DataTableResult(dataTableDTO.getDraw(),list.getTotal(),list.getTotal(),list.getResult());
        return JSON.toJSONString(dataTableResult);
    }
	
	@RequestMapping("/editOrderById")
    @ResponseBody
    public String editOrderById(Order order) {
		DataTableResult dataTableResult = orderService.editOrderById(order);
        return JSON.toJSONString(dataTableResult);
    }
	
	
	
	@RequestMapping("/selectById")
    @ResponseBody
    public String selectById(String id) {
		Order order = orderService.selectById(id);
        return JSON.toJSONString(order);
    }
	
	/**
	 * 退款
	 * @param order
	 * @param amount
	 * @return
	 */
	@RequestMapping("/submitRefund")
    @ResponseBody
    public String submitRefund(Order order,BigDecimal amount) {
		DataTableResult dataTableResult = orderService.submitRefund(order,amount);
        return JSON.toJSONString(dataTableResult);
    }

    /**
     * 查询退款金额
     */
    @RequestMapping("/selectRefundPrice")
    @ResponseBody
    public String selectRefundPrice(String id) {
        DataTableResult dataTableResult = orderService.selectRefundPrice(id);
        return JSON.toJSONString(dataTableResult);
    }


    @RequestMapping("/exportRefund")
    public void exportRefund(HttpServletRequest request, HttpServletResponse response){
        try {
            List<Map<String,Object>> map = orderService.exportRefund();
            List<LinkedHashMap<String, Object>> linkedHashMaps1 = new ArrayList<>();
            for (int i = 0; i < map.size(); i++) {
                LinkedHashMap<String, Object> hshMap1 = new LinkedHashMap<>();
                hshMap1.putAll(map.get(i));
                linkedHashMaps1.add(hshMap1);
            }
            List data = new ArrayList();
            data.add(linkedHashMaps1);

            String fileName = "退款审核";
            String sheetName[] = {"退款审核"};
            List keys = new ArrayList();
            String siteKeys[] = {"order_no", "sub_order_no", "user_id", "amount"};
            keys.add(siteKeys);
            List columnName = new ArrayList();
            String siteColumnNames[] = {"主订单号","子订单号","用户ID","总金额"};
            columnName.add(siteColumnNames);
            ExportTest.downExcelMoreSheet(data, fileName, sheetName, keys, columnName, response);
        }catch (Exception e){
            _log.error("退款审核导出系统错误",JSON.toJSON(e));
        }
    }

	/**
	 * 导出
	 */
    @RequestMapping("/exportPrem")
	public void exportPrem(HttpServletRequest request, HttpServletResponse response,String spuid){
        try {
            SysUser user = UserUtil.getUserMessage();
            List<Object[]> objList = new ArrayList<>();
            List<Map<String,Object>> map = new ArrayList<>();
            SupplierInfo supplierInfo = supplierInfoMapper.selectByUserId(String.valueOf(user.getId()));
            if(supplierInfo != null){
                map = orderService.exportPrem(String.valueOf(supplierInfo.getS_id()),spuid);
            }else{
                map = orderService.exportPrem(null,spuid);
            }
            for (int i = 0; i < map.size(); i++) {
                Object[] obj = new Object[7];
                obj[0] = map.get(i).get("order_no");
                obj[1] = map.get(i).get("addr_name");
                obj[2] = map.get(i).get("addr_mobile");
                obj[3] = map.get(i).get("addr_detail");
                if (spuid.equals("ZY-24020206")){
                    obj[4] = "茅台酒";
                    obj[5] = map.get(i).get("num")+"箱";
                }if (spuid.equals("ZY-97431990")){
                    obj[4] = "日用品";
                    obj[5] = map.get(i).get("num")+"盒";
                }

                objList.add(obj);
            }
            Workbook workbook = ExportExeclUtil.copyExcel(objList);
            ExportExeclUtil.downloadFile(workbook, request, response);
        }catch (Exception e){
            _log.error("订单导出系统错误",JSON.toJSON(e));
        }
    }


    @RequestMapping(value="/uploadExcel",method= RequestMethod.POST)
    @ResponseBody
    public String addSku(HttpServletRequest request,HttpServletResponse response,@RequestParam("excelFile") MultipartFile[] image1) {
        DataTableResult result = new DataTableResult();
        result.setError("导入成功");
        result.setDraw(1);
        OSSClientUtil ossUtil = new OSSClientUtil();
        try {
        String fullPath = "";
        for (MultipartFile myfile : image1) {
            if(StringUtil.isNotBlank(myfile.getOriginalFilename())){
                String url = ossUtil.uploadImg2Oss(myfile,OSSClientUtil.orderFiledir);
                if(StringUtil.isNotBlank(url)){
                    fullPath = OSSClientUtil.ORDER_URL+url;
                    try {
                        result = excel2db(fullPath);
                    } catch (Exception e) {
                        e.printStackTrace();
                        result.setError("系统错误");
                    }
                }
            }
        }
        } catch (Exception e) {
            result.setError("系统错误");
        }finally {
            ossUtil.destory();
        }
        return JSON.toJSONString(result);
    }



    /**
     * 导入
     * @param fullPath
     * @return
     * @throws Exception
     */
    private DataTableResult excel2db(String fullPath) throws Exception {
        _log.debug("路径================="+fullPath);
        DataTableResult result = new DataTableResult();
        result.setError("导入成功");
        result.setDraw(1);
        URL url = new URL(fullPath);
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);
        //判断是否为excel类型文件
        String extString = fullPath.substring(fullPath.lastIndexOf("."));
        File excel = new File(fullPath);
        InputStream is = null;
        Workbook wb = null;
        Sheet sheet = null;
        Row row = null;
        Row rowHeader = null;
        try {
            is = connection.getInputStream();
            if (".xls".equals(extString)) {
                wb = new HSSFWorkbook(is);
            } else if (".xlsx".equals(extString)) {
                wb = new XSSFWorkbook(is);
            } else {
                wb = null;
            }
            if (wb != null) {
                // 获取第一个sheet
                sheet = wb.getSheetAt(0);
                // 获取最大行数
                int rownum = sheet.getPhysicalNumberOfRows();
                // 获取第一行
//                rowHeader = sheet.getRow(3);
                row = sheet.getRow(3);
                // 获取最大列数
                int colnum = row.getPhysicalNumberOfCells();
                for (int i = 1; i < rownum; i++) {
                    row = sheet.getRow(i);
                    if (row != null) {
                            Order order = new Order();
                            order.setOrder_no(String.valueOf(row.getCell(0)));
                            List<Order> orderList = orderService.selectByOthers(order);
                            for (int j = 0; j < orderList.size(); j++) {

                                if (StringUtil.isBlank(orderList.get(j).getTrans_id())){

                                switch (row.getCell(7).getCellType()) {
                                    case Cell.CELL_TYPE_BOOLEAN:
                                        orderList.get(j).setTrans_id(String.valueOf(row.getCell(7).getBooleanCellValue()));

                                    case Cell.CELL_TYPE_NUMERIC:
                                        DecimalFormat df = new DecimalFormat("0");
                                        System.out.println(df.format(row.getCell(7).getNumericCellValue()));
                                        orderList.get(j).setTrans_id(String.valueOf(df.format(row.getCell(7).getNumericCellValue())));
                                        break;
                                    case Cell.CELL_TYPE_STRING:
                                        orderList.get(j).setTrans_id(row.getCell(7).getStringCellValue());
                                        break;
                                    case Cell.CELL_TYPE_BLANK:
                                        break;
                                    case Cell.CELL_TYPE_ERROR:
                                        orderList.get(j).setTrans_id(String.valueOf(row.getCell(7).getErrorCellValue()));
                                        break;

                                }
                                orderList.get(j).setTrans_com(row.getCell(6).getStringCellValue());
                                orderList.get(j).setOrder_status("dsh");
                                orderList.get(j).setSend_time(new Date());
                                orderList.get(j).setUpdate_time(new Date());
                                if(StringUtil.isNotBlank(orderList.get(j).getTrans_id())){
                                    try{

                                        orderService.updateByPrimaryKey(orderList.get(j));

                                        OrderDetail orderDetail = new OrderDetail();
                                        orderDetail.setOrder_no(orderList.get(j).getOrder_no());
                                        List<OrderDetail> list = orderDetailMapper.selectByOthers(orderDetail);
                                        //发送消息
                                        Information information = new Information();
                                        information.setCreate_time(new Date());
                                        information.setJump_page(7);
                                        information.setStatus(0);
                                        information.setUser_id(orderList.get(j).getUser_id());
                                        information.setTitle("您的拼团商品已发货");
                                        information.setSub_title(""+list.get(0).getSku_name()+"商品的快递单号("+orderList.get(j).getTrans_id()+")"+orderList.get(j).getTrans_com()+"。您也赶快开团吧，一个团至少赚18-2600元！");
                                        information.setMessage(""+list.get(0).getSku_name()+"商品的快递单号("+orderList.get(j).getTrans_id()+")"+orderList.get(j).getTrans_com()+"。您也赶快开团吧，一个团至少赚18-2600元！");
                                        informationMapper.insertYmInformation(information);
                                    }catch (Exception e){
                                        _log.error("导入错误"+e);
                                    }
                                }
                              }
                            }
                    } else {
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            _log.error("导入错误"+e);
            result.setError("导入失败");
        } catch (IOException e) {
            e.printStackTrace();
            _log.error("导入错误"+e);
            result.setError("导入失败");
        }

        Thread thread = new Thread(new OrderHandleUtil(orderMapper));
        thread.start();

        return result;
    }

}
