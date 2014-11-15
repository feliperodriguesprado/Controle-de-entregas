package controledeentregas.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controledeentregas.controller.ConnectionDatabase;
import controledeentregas.model.to.SituationTO;

public class SituationDAO {

	private Connection connection;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public SituationDAO() {
		this.connection = ConnectionDatabase.createConnection();
	}
	
	public Integer checkRegistrationSituationNewUpdate(SituationTO situationTO) {
		
		String sql;
		SituationTO resultSituationTO = new SituationTO();
		
		if (situationTO.getId() == null) {
			
			sql = "select id, descricao, ativo from situacoes where descricao = ? and ativo = false";
			
			try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, situationTO.getDescription());
				resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {
					resultSituationTO.setId(resultSet.getInt("id"));
					resultSituationTO.setAtivo(resultSet.getBoolean("ativo"));
					resultSituationTO.setDescription(resultSet
							.getString("descricao"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} else {

			sql = "select id, descricao, ativo from situacoes where id = ? and ativo = true";

			try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, situationTO.getId());
				resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {
					resultSituationTO.setId(resultSet.getInt("id"));
					resultSituationTO.setAtivo(resultSet.getBoolean("ativo"));
					resultSituationTO.setDescription(resultSet.getString("descricao"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultSituationTO.getId();		
	}

	
	public boolean save(SituationTO situationTO) {
		
		String sql = "insert into situacoes (ativo, descricao) values (?, ?)";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBoolean(1, true);
			preparedStatement.setString(2, situationTO.getDescription());
			preparedStatement.execute();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	public boolean update(SituationTO situationTO) {
		
		String sql = "update situacoes set ativo = true, descricao = ? where id = ?";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, situationTO.getDescription());
			preparedStatement.setInt(2, situationTO.getId());
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	public boolean remove(SituationTO situationTO){
		
		Integer result = null;
		String sql = "select idsituacao from entregas where idsituacao = ? limit 1";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, situationTO.getId());
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
			result = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		if (result == null) {
			
			sql = "delete from situacoes where id = ?";
			
			try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, situationTO.getId());
				preparedStatement.execute();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		
		} else {
			
			sql = "update situacoes set ativo = false where id = ?";
			
			try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, situationTO.getId());
				preparedStatement.execute();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
	}
	
	
	public List<SituationTO> getAllActive() {

		List<SituationTO> situations = new ArrayList<SituationTO>();
		String sql = "select id, descricao from situacoes where ativo = true order by descricao";

		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				SituationTO situationsTO = new SituationTO();
				situationsTO.setId(resultSet.getInt(1));
				situationsTO.setDescription(resultSet.getString(2));
				situations.add(situationsTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return situations;
	}

}
