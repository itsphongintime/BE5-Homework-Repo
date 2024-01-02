package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Category;
import entity.SQLConnection;

public class CategoryDAO {
	public List<Category> getAllCategories () throws SQLException {
		Connection connection = SQLConnection.makeConnection();

		String sql = "select * from category order by priority";
		
		Statement stmt = connection.createStatement();

		ResultSet resultSet = stmt.executeQuery(sql);
		
		List<Category> list = new ArrayList<Category>();
		
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			int priority = resultSet.getInt("priority");
			Category category = new Category(id, name, priority);
			list.add(category);
		}
		
		return list;
	}
	
	public Category getCategoryById (int categoryId) throws SQLException {
		Connection connection = SQLConnection.makeConnection();

		String sql = "select * from category where category.id = ? order by priority";
		
		PreparedStatement preStmt = connection.prepareStatement(sql);
		
		preStmt.setInt(1, categoryId);

		ResultSet resultSet = preStmt.executeQuery();
		
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			int priority = resultSet.getInt("priority");
			Category category = new Category(id, name, priority);
			return category;
		}
		
		return null;
	}
}
