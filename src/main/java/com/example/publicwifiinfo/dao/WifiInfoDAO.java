package com.example.publicwifiinfo.dao;

import com.example.publicwifiinfo.api.ApiExplorer;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.sql.*;

public class WifiInfoDAO {
    private final String url = "jdbc:mysql://localhost:3306/public_wifi_info";
    private final String userId = "root";
    private final String password = "vlfkalem1.";
    private Connection connection;
    private PreparedStatement statement;

    public WifiInfoDAO() {

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
                        Float lat = object.getAsJsonObject().get("LAT").getAsFloat();
                        Float lnt = object.getAsJsonObject().get("LNT").getAsFloat();
                        String workDatetime = object.getAsJsonObject().get("WORK_DTTM").getAsString();

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
                        statement.setFloat(14, lat);
                        statement.setFloat(15, lnt);
                        statement.setString(16, workDatetime);
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

    public static void main(String[] args) throws IOException {
        WifiInfoDAO dao = new WifiInfoDAO();
        dao.saveApiData();
    }
}
