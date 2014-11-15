package controledeentregas.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controledeentregas.controller.ConnectionDatabase;
import controledeentregas.model.to.ProductsTO;

public class ProductsDAO {

	private Connection connection;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public ProductsDAO() {
		this.connection = ConnectionDatabase.createConnection();
	}
	
	public Integer checkRegistrationProductNewUpdate(ProductsTO productsTO) {
		
		String sql;
		ProductsTO resultProductTO = new ProductsTO();
		
		if (productsTO.getId() == null) {
			
			sql = "select id, ativo, codigo, descricao, valor from produtos where codigo = ? and ativo = false";
			
			try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, productsTO.getCod());
				resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {
					resultProductTO.setId(resultSet.getInt("id"));
					resultProductTO.setCod(resultSet.getString("codigo"));
					resultProductTO.setAtivo(resultSet.getBoolean("ativo"));
					resultProductTO.setDescription(resultSet.getString("descricao"));
					resultProductTO.setValue(resultSet.getFloat("valor"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} else {

			sql = "select id, ativo, codigo, descricao, valor from produtos where id = ? and ativo = true";

			try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, productsTO.getId());
				resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {
					resultProductTO.setId(resultSet.getInt("id"));
					resultProductTO.setCod(resultSet.getString("codigo"));
					resultProductTO.setAtivo(resultSet.getBoolean("ativo"));
					resultProductTO.setDescription(resultSet.getString("descricao"));
					resultProductTO.setValue(resultSet.getFloat("valor"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultProductTO.getId();		
	}

	
	public boolean save(ProductsTO productsTO) {
		
		String sql = "insert into produtos (ativo, codigo, descricao, valor) values (true, ?, ?, ?)";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, productsTO.getCod());
			preparedStatement.setString(2, productsTO.getDescription());
			preparedStatement.setFloat(3, productsTO.getValue());
			
			preparedStatement.execute();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	public boolean update(ProductsTO productsTO) {
		
		String sql = "update produtos set ativo = true, codigo = ?, descricao = ?,  valor = ? where id = ?";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, productsTO.getCod());
			preparedStatement.setString(2, productsTO.getDescription());
			preparedStatement.setFloat(3, productsTO.getValue());
			preparedStatement.setInt(4, productsTO.getId());
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	public boolean remove(ProductsTO productsTO){
		
		Integer result = null;
		String sql = "select idproduto from produtosxentregas where idproduto = ? limit 1";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, productsTO.getId());
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
			result = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		if (result == null) {
			
			sql = "delete from produtos where id = ?";
			
			try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, productsTO.getId());
				preparedStatement.execute();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		
		} else {
			
			sql = "update produtos set ativo = false where id = ?";
			
			try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, productsTO.getId());
				preparedStatement.execute();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
	}
	
	
	public List<ProductsTO> getAllActive() {

		List<ProductsTO> produtcs = new ArrayList<ProductsTO>();
		String sql = "select id, codigo, descricao, valor from produtos where ativo = true order by descricao";

		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ProductsTO productsTO = new ProductsTO();
				
				productsTO.setId(resultSet.getInt(1));
				productsTO.setCod(resultSet.getString(2));
				productsTO.setDescription(resultSet.getString(3));
				productsTO.setValue(resultSet.getFloat(4));
				produtcs.add(productsTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produtcs;
	}

}
