package com.example.publicwifiinfo.dao;

import com.example.publicwifiinfo.api.ApiExplorer;
import com.example.publicwifiinfo.model.WifiInfoVo;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class WifiInfoDAO {
    private final String url = "jdbc:mysql://localhost:3306/public_wifi_info";
    private final String userId = "root";
    private final String password = "vlfkalem1.";
    private Connection connection;
    private PreparedStatement statement;

    public WifiInfoDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<WifiInfoVo> wifiList(Double myX, Double myY) throws IOException {
        ArrayList<WifiInfoVo> list = new ArrayList<>();
        this.saveDistances(myX, myY);
        try {
            connection = DriverManager.getConnection(url, userId, password);

            String query = "select * from wifi_info order by distance";
            statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            int i = 0;

            while (resultSet.next()){
                if (i == 20) {
                    break;
                }
                Double distance = resultSet.getDouble("distance");
                String XSWifiManageNo = resultSet.getString("x_swifi_mgr_no");
                String XSWifiWRDOFC = resultSet.getString("x_swifi_wrdofc");
                String XSWifiMainNM = resultSet.getString("x_swifi_main_nm");
                String XSWifiADRES1 = resultSet.getString("x_swifi_adres1");
                String XSWifiADRES2 = resultSet.getString("x_swifi_adres2");
                String XSWifiInstallFloor = resultSet.getString("x_swifi_instl_floor");
                String XSWifiInstallType = resultSet.getString("x_swifi_instl_ty");
                String XSWifiInstallMBY = resultSet.getString("x_swifi_instl_mby");
                String XSWifiServiceSE = resultSet.getString("x_swifi_svc_se");
                String XSWifiCMCWR = resultSet.getString("x_swifi_cmcwr");
                String XSWifiCNSTCYear = resultSet.getString("x_swifi_cnstc_year");
                String XSWifiInoutDoor = resultSet.getString("x_swifi_inout_door");
                String XSWifiREMARS3 = resultSet.getString("x_swifi_remars3");
                Double lat = resultSet.getDouble("lat");
                Double lnt = resultSet.getDouble("lnt");
                Timestamp workDatetime = resultSet.getTimestamp("work_dttm");
                WifiInfoVo vo = new WifiInfoVo();
                vo.setDistance(distance);
                vo.setXSWifiManageNo(XSWifiManageNo);
                vo.setXSWifiWRDOFC(XSWifiWRDOFC);
                vo.setXSWifiMainNM(XSWifiMainNM);
                vo.setXSWifiADRES1(XSWifiADRES1);
                vo.setXSWifiADRES2(XSWifiADRES2);
                vo.setXSWifiInstallFloor(XSWifiInstallFloor);
                vo.setXSWifiInstallType(XSWifiInstallType);
                vo.setXSWifiInstallMBY(XSWifiInstallMBY);
                vo.setXSWifiServiceSE(XSWifiServiceSE);
                vo.setXSWifiCMCWR(XSWifiCMCWR);
                vo.setXSWifiCNSTCYear(XSWifiCNSTCYear);
                vo.setXSWifiInoutDoor(XSWifiInoutDoor);
                vo.setXSWifiREMARS3(XSWifiREMARS3);
                vo.setLat(lat);
                vo.setLnt(lnt);
                vo.setWorkDatetime(workDatetime);

                list.add(vo);
                i++;
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void saveDistances(Double myX, Double myY) throws IOException {
        try {
            connection = DriverManager.getConnection(url, userId, password);
            String query = "select x_swifi_mgr_no, lat, lnt from wifi_info";
            statement = connection.prepareStatement(query);
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                String key = set.getNString("x_swifi_mgr_no");
                Double lat = set.getDouble("lat");
                Double lnt = set.getDouble("lnt");
                query = "update wifi_info set distance = " + distance(myX, myY, lat, lnt) + " where x_swifi_mgr_no = '" + key + "'";
                statement = connection.prepareStatement(query);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int saveApiData() throws IOException {

        String stringJson;
        JsonParser parser = new JsonParser();
        JsonElement element;
        JsonObject tbl;
        JsonArray row;
        int totCount = 0;
        int i = 1;

        while(true) {
            stringJson = ApiExplorer.ApiJson(i, i + 999);

            if (stringJson.equals("{\"RESULT\":{\"CODE\":\"INFO-200\",\"MESSAGE\":\"해당하는 데이터가 없습니다.\"}}")) {
                break;
            } else {
                i += 1000;
                element = parser.parse(stringJson);
                tbl = element.getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject();
                row = (JsonArray) tbl.get("row");
                try {
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    connection = DriverManager.getConnection(url, userId, password);

                    for (JsonElement object : row) {
                        totCount++;
                        String XSWifiManageNo = object.getAsJsonObject().get("X_SWIFI_MGR_NO").getAsString();
                        String XSWifiWRDOFC = object.getAsJsonObject().get("X_SWIFI_WRDOFC").getAsString();
                        String XSWifiMainNM = object.getAsJsonObject().get("X_SWIFI_MAIN_NM").getAsString();
                        String XSWifiADRES1 = object.getAsJsonObject().get("X_SWIFI_ADRES1").getAsString();
                        String XSWifiADRES2 = object.getAsJsonObject().get("X_SWIFI_ADRES2").getAsString();
                        String XSWifiInstallFloor = object.getAsJsonObject().get("X_SWIFI_INSTL_FLOOR").getAsString();
                        String XSWifiInstallType = object.getAsJsonObject().get("X_SWIFI_INSTL_TY").getAsString();
                        String XSWifiInstallMBY = object.getAsJsonObject().get("X_SWIFI_INSTL_MBY").getAsString();
                        String XSWifiServiceSE = object.getAsJsonObject().get("X_SWIFI_SVC_SE").getAsString();
                        String XSWifiCMCWR = object.getAsJsonObject().get("X_SWIFI_CMCWR").getAsString();
                        String XSWifiCNSTCYear = object.getAsJsonObject().get("X_SWIFI_CNSTC_YEAR").getAsString();
                        String XSWifiInoutDoor = object.getAsJsonObject().get("X_SWIFI_INOUT_DOOR").getAsString();
                        String XSWifiREMARS3 = object.getAsJsonObject().get("X_SWIFI_REMARS3").getAsString();
                        Double lnt = object.getAsJsonObject().get("LNT").getAsDouble();
                        Double lat = object.getAsJsonObject().get("LAT").getAsDouble();
                        Timestamp workDatetime = Timestamp.valueOf(object.getAsJsonObject().get("WORK_DTTM").getAsString());

                        String query = "insert into wifi_info";
                        query += " (x_swifi_mgr_no, x_swifi_wrdofc, x_swifi_main_nm, x_swifi_adres1, x_swifi_adres2, x_swifi_instl_floor, x_swifi_instl_ty, x_swifi_instl_mby, x_swifi_svc_se, x_swifi_cmcwr, x_swifi_cnstc_year, x_swifi_inout_door, x_swifi_remars3, lat, lnt, work_dttm)";
                        query += " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                        statement = connection.prepareStatement(query);
                        statement.setString(1, XSWifiManageNo);
                        statement.setString(2, XSWifiWRDOFC);
                        statement.setString(3, XSWifiMainNM);
                        statement.setString(4, XSWifiADRES1);
                        statement.setString(5, XSWifiADRES2);
                        statement.setString(6, XSWifiInstallFloor);
                        statement.setString(7, XSWifiInstallType);
                        statement.setString(8, XSWifiInstallMBY);
                        statement.setString(9, XSWifiServiceSE);
                        statement.setString(10, XSWifiCMCWR);
                        statement.setString(11, XSWifiCNSTCYear);
                        statement.setString(12, XSWifiInoutDoor);
                        statement.setString(13, XSWifiREMARS3);
                        statement.setDouble(14, lnt);
                        statement.setDouble(15, lat);
                        statement.setTimestamp(16, workDatetime);
                        statement.executeUpdate();
                    }

                    statement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return totCount;
    }
    // 두 좌표 사이의 거리를 구하는 함수
    // dsitance(첫번쨰 좌표의 위도, 첫번째 좌표의 경도, 두번째 좌표의 위도, 두번째 좌표의 경도)
    private static double distance(double lat1, double lon1, double lat2, double lon2){
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))* Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1))*Math.cos(deg2rad(lat2))*Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60*1.1515*1609.344;

        return dist / 1000; //단위 Km
    }
    //10진수를 radian(라디안)으로 변환
    private static double deg2rad(double deg){
        return (deg * Math.PI/180.0);
    }
    //radian(라디안)을 10진수로 변환
    private static double rad2deg(double rad){
        return (rad * 180 / Math.PI);
    }

    public static void main(String[] args) throws IOException {
        WifiInfoDAO dao = new WifiInfoDAO();
        dao.saveApiData();
    }
}
