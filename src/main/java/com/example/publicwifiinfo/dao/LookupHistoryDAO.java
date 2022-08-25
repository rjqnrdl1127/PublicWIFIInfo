package com.example.publicwifiinfo.dao;

import com.example.publicwifiinfo.model.LookupHistoryVo;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public class LookupHistoryDAO {

    private final String url = "jdbc:mysql://localhost:3306/public_wifi_info";
    private final String userId = "root";
    private final String password = "vlfkalem1.";
    private Connection connection;
    private PreparedStatement statement;

    public LookupHistoryDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteLookupData(Long id) {
        try {
            connection = DriverManager.getConnection(url, userId, password);

            String query = "delete from lookup_history where id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<LookupHistoryVo> LookUplist() {
        ArrayList<LookupHistoryVo> list = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(url, userId, password);
            String query = "select * from lookup_history";
            statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                BigDecimal xCoordinate = resultSet.getBigDecimal("x_coordinate");
                BigDecimal yCoordinate = resultSet.getBigDecimal("y_coordinate");
                Timestamp inquiryDate = resultSet.getTimestamp("inquiry_date");
                LookupHistoryVo vo = new LookupHistoryVo();
                vo.setId(id);
                vo.setxCoordinate(xCoordinate);
                vo.setyCoordinate(yCoordinate);
                vo.setInquiryDate(inquiryDate);
                list.add(vo);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Collections.reverse(list);
        return list;
    }

    public void addLookUpData(LookupHistoryVo vo) {
        try {
            connection = DriverManager.getConnection(url, userId, password);
            Long id = vo.getId();
            BigDecimal xCoordinate = vo.getxCoordinate();
            BigDecimal yCoordinate = vo.getyCoordinate();
            Timestamp inquiryDate = vo.getInquiryDate();

            String query = "insert into lookup_history";
            query += " (x_coordinate, y_coordinate)";
            query += "  values(?, ?)";
            statement = connection.prepareStatement(query);
            statement.setBigDecimal(1, xCoordinate);
            statement.setBigDecimal(2, yCoordinate);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LookupHistoryDAO dao = new LookupHistoryDAO();
        ArrayList<LookupHistoryVo> list = dao.LookUplist();
        for (LookupHistoryVo vo : list) {
            System.out.println(vo.getId());
            System.out.println(vo.getxCoordinate());
            System.out.println(vo.getyCoordinate());
            System.out.println(vo.getInquiryDate());
        }
    }
}
