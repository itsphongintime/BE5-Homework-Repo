package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Category;
import entity.Product;
import entity.SQLConnection;

public class ProductDAO {
	private CategoryDAO categoryDAO;
	
	public ProductDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public ProductDAO(CategoryDAO categoryDAO) {
		super();
		this.categoryDAO = categoryDAO;
	}

	public List<Product> getLatestProducts () throws SQLException {
		Connection connection = SQLConnection.makeConnection();

		String sql = "select * from product where is_new = 1";
		
		Statement stmt = connection.createStatement();

		ResultSet resultSet = stmt.executeQuery(sql);
		
		List<Product> list = new ArrayList<Product>();
		
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			int price = resultSet.getInt("price");
			String imgName = resultSet.getString("img_name");
			boolean isNew = resultSet.getBoolean("is_new");
			int quantity = resultSet.getInt("quantity");
			String description = resultSet.getString("description");
			Category category = categoryDAO.getCategoryById(resultSet.getInt("category_id"));
			Product product = new Product(id, name, price, imgName, isNew, quantity, description, category);
			list.add(product);
		}
		
		return list;
	}
	
	public Product getProductById (int productId) throws SQLException {
		Connection connection = SQLConnection.makeConnection();

		String sql = "select * from product where product.id = ?";
		
		PreparedStatement preStmt = connection.prepareStatement(sql);
		
		preStmt.setInt(1, productId);

		ResultSet resultSet = preStmt.executeQuery();
		
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			int price = resultSet.getInt("price");
			String imgName = resultSet.getString("img_name");
			boolean isNew = resultSet.getBoolean("is_new");
			int quantity = resultSet.getInt("quantity");
			String description = resultSet.getString("description");
			Category category = categoryDAO.getCategoryById(resultSet.getInt("category_id"));
			Product product = new Product(id, name, price, imgName, isNew, quantity, description, category);
			return product;
		}
		
		return null;
	}
	
	public List<Product> getAllProducts () throws SQLException {
		Connection connection = SQLConnection.makeConnection();

		String sql = "select * from product";
		
		Statement stmt = connection.createStatement();

		ResultSet resultSet = stmt.executeQuery(sql);
		
		List<Product> list = new ArrayList<Product>();
		
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			int price = resultSet.getInt("price");
			String imgName = resultSet.getString("img_name");
			boolean isNew = resultSet.getBoolean("is_new");
			int quantity = resultSet.getInt("quantity");
			String description = resultSet.getString("description");
			Category category = categoryDAO.getCategoryById(resultSet.getInt("category_id"));
			Product product = new Product(id, name, price, imgName, isNew, quantity, description, category);
			list.add(product);
		}
		
		return list;
	}
	
	public List<Product> getProductByCategoryId (int categoryId) throws SQLException {
		Connection connection = SQLConnection.makeConnection();

		String sql = "select * from product where product.category_id = ?";
		
		PreparedStatement preStmt = connection.prepareStatement(sql);
		
		preStmt.setInt(1, categoryId);

		ResultSet resultSet = preStmt.executeQuery();
		
		List<Product> list = new ArrayList<Product>();
		
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			int price = resultSet.getInt("price");
			String imgName = resultSet.getString("img_name");
			boolean isNew = resultSet.getBoolean("is_new");
			int quantity = resultSet.getInt("quantity");
			String description = resultSet.getString("description");
			Category category = categoryDAO.getCategoryById(resultSet.getInt("category_id"));
			Product product = new Product(id, name, price, imgName, isNew, quantity, description, category);
			list.add(product);
		}
		
		return list;
	}
}
