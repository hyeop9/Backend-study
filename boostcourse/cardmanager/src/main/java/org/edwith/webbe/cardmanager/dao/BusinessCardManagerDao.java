package org.edwith.webbe.cardmanager.dao;

import org.edwith.webbe.cardmanager.dto.BusinessCard;

import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusinessCardManagerDao {
	private static String dburl = "jdbc:mysql://localhost:3306/connectdb?serverTimezone=UTC";
	private static String dbUser = "hy";
	private static String dbpasswd = "hy0507";
	
    public List<BusinessCard> searchBusinessCard(String keyword){
    	List<BusinessCard> cards = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			String sql = "SELECT name, phone, companyName, createDate FROM businesscard WHERE name = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, keyword);
			rs = ps.executeQuery();

			if (rs.next()) {
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String companyName = rs.getString("companyName");
				Date createDate = rs.getDate("createDate");
				BusinessCard card = new BusinessCard(name, phone, companyName);
				card.setCreateDate(rs.getDate("createDate"));
				cards.add(card);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return cards;
    	
    }

    public BusinessCard addBusinessCard(BusinessCard businessCard){
    	BusinessCard card = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql = "INSERT INTO businesscard (name, phone, companyName) VALUES ( ?, ?, ? )";

		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, businessCard.getName());
			ps.setString(2, businessCard.getPhone());
			ps.setString(3, businessCard.getCompanyName());

			int result = ps.executeUpdate();
			
			if(result == 1) {
				BusinessCardManagerDao dao = new BusinessCardManagerDao();
				List<BusinessCard> cards = dao.searchBusinessCard(businessCard.getName());
				card = cards.get(0);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return card;
    }
}