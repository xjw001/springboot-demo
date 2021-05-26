import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class generateJSON {

    private static Map<String,String> deptDataMap = new HashMap();

    private static int total = 0;
    static {
        deptDataMap.put("1","20203780|广州");
        deptDataMap.put("2","20203970|中山");
        deptDataMap.put("3","20203860|佛山");
        deptDataMap.put("4","20203770|东莞");
        deptDataMap.put("5","20203960|江门");
        deptDataMap.put("6","20204030|肇庆");
        deptDataMap.put("7","20203940|惠州");
        deptDataMap.put("8","20203900|珠海");
        deptDataMap.put("9","20203980|茂名");
        deptDataMap.put("10","20203850|顺德");
        deptDataMap.put("11","20203950|汕头");
        deptDataMap.put("12","20204000|湛江");
        deptDataMap.put("13","20203990|揭阳");
        deptDataMap.put("14","20203760|潮州");
        deptDataMap.put("15","20204010|梅州");
        deptDataMap.put("16","20204020|河源");
        deptDataMap.put("17","20203930|清远");
        deptDataMap.put("18","20203910|阳江");
        deptDataMap.put("19","20203920|韶关");
        deptDataMap.put("20","20203890|云浮");
        deptDataMap.put("21","20203880|汕尾");
        deptDataMap.put("22","20203780|番禺");
    }
    public static final String LoginUserId = "114260"; //省公司管理员
    public static void main(String[] args) throws Exception {
        String filePath = "E:\\国寿-建工类项目\\真实用户和机构\\生产\\建工类系统人员架构各机构统计表\\";
        for (int i = 1; i < 22; i++) {
            String key = String.valueOf(i);
            String array[] = deptDataMap.get(key).split("\\|");
            String deptName = array[1];
            String deptId = ProdDeptData.prodeptMap.get(deptName);
            String fullPath = filePath+i+deptName+".xlsx";
            generateJsonProd(deptName,deptId,fullPath,array[0]);
        }
        System.out.println("所有用户:" + total);
    }

    public static void  generateJsonProd(String deptName,String deptId,String filePath,String defaultDeptId) throws Exception {
        File f =new File(filePath);
      //  Workbook workbook = WorkbookFactory.create(f);
        XSSFWorkbook workbook=new XSSFWorkbook(new FileInputStream(f));
        //Sheet sheet = workbook.getSheetAt(1);
        Map<String,MyUser> userMap = new LinkedHashMap<>();
        addRolesProd(workbook.getSheetAt(1),userMap,"12820",defaultDeptId); //项目申请
        addRolesProd(workbook.getSheetAt(2),userMap,"12830",defaultDeptId); //尽调
        XSSFSheet sheetAt3 = workbook.getSheetAt(3);
        addRoles(4,sheetAt3,defaultDeptId,userMap,"12930",1); //投保录入1-3
        addRoles(4,sheetAt3,defaultDeptId,userMap,"12840",4); //一级初审4-6
        addRoles(4,sheetAt3,defaultDeptId,userMap,"12850,12910,12940",7); //一级复审7-9
        //addRoles(4,sheetAt3,defaultDeptId,userMap,"12910",7); //信息确认7-9
        //addRoles(4,sheetAt3,defaultDeptId,userMap,"12940",7); //投保确认7-9
        addRoles(4,sheetAt3,defaultDeptId,userMap,"12870",10); //一级终审10-12

        System.out.println("["+deptName+"]总条数:"+userMap.size()+","+JSONObject.toJSONString(userMap.values()));
        total +=userMap.size();
    }

    public static void addRolesProd(Sheet sheet,Map<String,MyUser> userMap,String roleId,String defaultDeptId) throws Exception {
        Row temp = sheet.getRow(0);
        if (temp == null) {
            return;
        }
        // 总行数。
        int rowNumbers = sheet.getLastRowNum() + 1;
        //第几行开始读
        int readNumer = 3;
        //从第几列开始读（从0开始）
        int cellNumer = 2;
        for (int rowNumer = readNumer; rowNumer < rowNumbers; rowNumer++) {
            Row row = sheet.getRow(rowNumer);
            if(row == null){
                continue;
            }
            //业务岗数据
            if (row.getCell(cellNumer) != null && row.getCell(cellNumer+1) != null && row.getCell(cellNumer+2) != null) {
                Cell cell = row.getCell(cellNumer+2);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                String phone = cell.getStringCellValue();
                Cell cellCard = row.getCell(cellNumer+1);
                cellCard.setCellType(Cell.CELL_TYPE_STRING);
                MyUser myUser = new MyUser();
                String deptName =row.getCell(cellNumer-1)!= null ? row.getCell(cellNumer-1).toString() :"";
                if("/".equals(deptName) || StringUtils.isBlank(deptName)){
                    myUser.setDeptId(defaultDeptId);
                }else{
                    if(ProdDeptData.prodeptMap.get(deptName) == null){
                        throw new Exception("部门代码未找到:"+deptName);
                    }
                    myUser.setDeptId(ProdDeptData.prodeptMap.get(deptName));
                }
                myUser.setRoleIds(roleId);
                myUser.setUserName(row.getCell(cellNumer).toString()); //用户名
                myUser.setIdCard(cellCard.getStringCellValue());  //身份证
                myUser.setPhone(phone); //电话号码
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

    public static void  generateJson(String deptName,String filePath,int readNumer,String deptId) throws IOException, InvalidFormatException {
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

        addRoles(readNumer,sheet,deptId,userMap,"12820",2); //项目申请
        addRoles(readNumer,sheet,deptId,userMap,"12930",5); //投保录入
        addRoles(readNumer,sheet,deptId,userMap,"12830",8); //尽调
        addRoles(readNumer,sheet,deptId,userMap,"12840",11); //一级初审
        addRoles(readNumer,sheet,deptId,userMap,"12850",14); //一级复审
        addRoles(readNumer,sheet,deptId,userMap,"12870",17); //一级终审
        System.out.println("["+deptName+"]总条数:"+userMap.size()+","+JSONObject.toJSONString(userMap.values()));
        total +=userMap.size();
    }

    /**
     *
     * @param readNumer 从第几行开始读
     * @param sheet sheet页
     * @param deptId
     * @param userMap
     * @param roleId
     * @param cellNumer
     */
    public static void addRoles(int readNumer,Sheet sheet,String deptId, Map<String,MyUser> userMap,String roleId,int cellNumer){
        // 行数。
        int rowNumbers = sheet.getLastRowNum() + 1;
        for (int rowNumer = readNumer-1; rowNumer < rowNumbers; rowNumer++) {
            Row row = sheet.getRow(rowNumer);
            if(row == null){
                continue;
            }
            //业务岗数据
            if (row.getCell(cellNumer) != null && row.getCell(cellNumer+1) != null && row.getCell(cellNumer+2) != null) {
                Cell cell = row.getCell(cellNumer+2);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                String phone = cell.getStringCellValue();

                Cell cellCard = row.getCell(cellNumer+1);
                cellCard.setCellType(Cell.CELL_TYPE_STRING);

                MyUser myUser = new MyUser();
                myUser.setDeptId(deptId);
                myUser.setRoleIds(roleId);
                myUser.setUserName(row.getCell(cellNumer).toString()); //用户名
                myUser.setIdCard(cellCard.getStringCellValue());  //身份证
                myUser.setPhone(phone); //电话号码
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

class MyUser{

    private String loginUser = generateJSON.LoginUserId;

    private String deptId;

    private String roleIds;

    private String userName;

    private String idCard;

    private String phone;

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
