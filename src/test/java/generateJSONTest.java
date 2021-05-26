import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class generateJSONTest {

    /**
     * 20203760	潮州中心支公司
     * 20203770	东莞中心支公司
     * 20203780	广州分公司
     * 20204630	广州市番禺支公司
     * 20203850	佛山市顺德支公司
     * 20203860	佛山中心支公司
     * 20203880	汕尾中心支公司
     * 20203890	云浮中心支公司
     * 20203900	珠海中心支公司
     * 20203910	阳江中心支公司
     * 20203920	韶关中心支公司
     * 20203930	清远中心支公司
     * 20203940	惠州中心支公司
     * 20203950	汕头中心支公司
     * 20203960	江门中心支公司
     * 20203970	中山中心支公司
     * 20203980	茂名中心支公司
     * 20203990	揭阳中心支公司
     * 20204000	湛江中心支公司
     * 20204010	梅州中心支公司
     * 20204020	河源中心支公司
     * 20204030	肇庆中心支公司
     *
     * 13080	项目申请-导入
     * 13090	投保录入-导入
     * 13100	尽调-导入
     * 13110	一级初审-导入
     * 13120	一级复审-导入
     * 13130	一级终审-导入
     */
    public static final String LoginUserId = "114290";

    public static final String basePath = "E:\\国寿-建工类项目\\真实用户和机构\\生产\\建工类系统人员架构各机构统计表\\";
    public static void main(String[] args) throws Exception {
        String filePath = basePath +"18阳江.xlsx";
        generateJson(filePath,9,"20203910");

        System.out.println("--------------------------------");
        String filePath2 = "E:\\国寿-建工类项目\\真实用户和机构\\第二版\\各机构系统使用人员\\19韶关.xlsx";
        generateJson(filePath2,9,"20203920");

//        System.out.println("--------------------------------");
//        String filePath3 = "E:\\国寿-建工类项目\\真实用户和机构\\第二版\\各机构系统使用人员\\20云浮.xlsx";
//        generateJson(filePath3,9,"20203890");
//
//        System.out.println("--------------------------------");
//        String filePath4 = "E:\\国寿-建工类项目\\真实用户和机构\\第二版\\各机构系统使用人员\\21汕尾.xlsx";
//        generateJson(filePath4,9,"20203880");
//
//        System.out.println("--------------------------------");
//        String filePath5 = "E:\\国寿-建工类项目\\真实用户和机构\\第二版\\各机构系统使用人员\\22番禺.xlsx";
//        generateJson(filePath5,9,"20204630");
    }

    public static void  generateJson(String filePath,int readNumer,String deptId) throws IOException, InvalidFormatException {
        Map<String,MyUser> userMap = new LinkedHashMap<>();
        File f =new File(filePath);
        Workbook workbook = WorkbookFactory.create(f);
        Sheet sheet = workbook.getSheetAt(0);
        // 行数。
        int rowNumbers = sheet.getLastRowNum() + 1;
        // Excel第一行。
        Row temp = sheet.getRow(0);
        if (temp == null) {
            return;
        }

        addRoles(readNumer,rowNumbers,sheet,deptId,userMap,"13080",2); //项目申请
        addRoles(readNumer,rowNumbers,sheet,deptId,userMap,"13090",5); //投保录入
        addRoles(readNumer,rowNumbers,sheet,deptId,userMap,"13100",8); //尽调
        addRoles(readNumer,rowNumbers,sheet,deptId,userMap,"13110",11); //一级初审
        addRoles(readNumer,rowNumbers,sheet,deptId,userMap,"13120",14); //一级复审
        addRoles(readNumer,rowNumbers,sheet,deptId,userMap,"13130",17); //一级终审
        System.out.println(JSONObject.toJSONString(userMap.values()));
    }

    public static void addRoles(int readNumer,int rowNumbers,Sheet sheet,String deptId, Map<String,MyUser> userMap,String roleId,int cellNumer){
        for (int rowNumer = readNumer-1; rowNumer < rowNumbers; rowNumer++) {
            Row row = sheet.getRow(rowNumer);
            if(row == null){
                continue;
            }
            //业务岗数据
            if (row.getCell(cellNumer) != null && row.getCell(cellNumer+1) != null && row.getCell(cellNumer+2) != null) {
                Cell cell = row.getCell(cellNumer+2);
                cell.setCellType(Cell.CELL_TYPE_STRING);

                Cell cellCard = row.getCell(cellNumer+1);
                cellCard.setCellType(Cell.CELL_TYPE_STRING);

                String phone = cell.getStringCellValue();
                MyUser myUser = new MyUser();
                myUser.setDeptId(deptId);
                myUser.setRoleIds(roleId);
                myUser.setUserName(row.getCell(cellNumer).toString());
                myUser.setIdCard(cellCard.getStringCellValue());
                myUser.setPhone(phone);
                if(phone.length() < 11){
                    continue;
                }
                if (StringUtils.isNotBlank(myUser.getPhone())) {
                    if(userMap.get(phone) != null){
                       MyUser existUser = userMap.get(phone);
                       existUser.setRoleIds(existUser.getRoleIds()+","+roleId);
                    }else {
                        userMap.put(phone, myUser);
                    }
                }
            }

        }
    }


}


