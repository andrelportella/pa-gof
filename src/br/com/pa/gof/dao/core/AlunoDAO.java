package br.com.pa.gof.dao.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.pa.gof.common.dto.Aluno;
import br.com.pa.gof.common.dto.EntityDto;
import br.com.pa.gof.common.exception.SystemException;
import br.com.pa.gof.dao.IAlunoDAO;
import br.com.pa.gof.dao.jdbc.JdbcConnection;

public class AlunoDAO extends JdbcConnection implements IAlunoDAO{

	@Override
	public Aluno findById(Integer id) {
		Aluno aluno = null;
		
		StringBuilder sql = new StringBuilder();		
		sql.append("SELECT ALUN_CD_ALUNO ,ALUN_NM_ALUNO ,ALUN_NR_MATRICULA ,ALUN_ST_ALUNO FROM ALUNO WHERE ALUN_CD_ALUNO = ?");		
		
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
			con = getConnection();
			statement = con.prepareStatement(sql.toString());
			statement.setInt(1, id);
			result = statement.executeQuery();
			
			if(result.next()) {
				aluno = new Aluno();
				aluno.setId(new Integer(result.getInt(1)));
				aluno.setNome(result.getString(2));
				aluno.setMatricula(result.getString(3));
				aluno.setStatus(result.getString(4));				
			}
			
			
		} catch (SQLException e) {
			throw SystemException.dataBase(e);
		}finally {
			closeConnection(result, statement, con);
		}
		
		return aluno;
	}

	@Override
	public List<EntityDto> findAll() {
		List<EntityDto> lista = new ArrayList<EntityDto>();
		
		StringBuilder sql = new StringBuilder();		
		sql.append("SELECT ALUN_CD_ALUNO ,ALUN_NM_ALUNO ,ALUN_NR_MATRICULA ,ALUN_ST_ALUNO FROM ALUNO");
		
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
			con = getConnection();
			statement = con.prepareStatement(sql.toString());
			result = statement.executeQuery();
			
			while (result.next()) {
				Aluno aluno = new Aluno();
				aluno.setId(new Integer(result.getInt(1)));
				aluno.setNome(result.getString(2));
				aluno.setMatricula(result.getString(3));
				aluno.setStatus(result.getString(4));
				lista.add(aluno);				
			}
			
			
		} catch (SQLException e) {
			throw SystemException.dataBase(e);
		}finally {
			closeConnection(result, statement, con);
		}
		
		return lista;
	}

	@Override
	public EntityDto save(EntityDto dto) {
		StringBuilder sql = new StringBuilder();		
		sql.append("INSERT INTO ALUNO (ALUN_CD_ALUNO ,ALUN_NM_ALUNO ,ALUN_NR_MATRICULA ,ALUN_ST_ALUNO)");
		sql.append(" VALUES ");
		sql.append("(DEFAULT ,? ,? ,?)");
		
		Connection con = null;
		PreparedStatement statement = null;

		
		ResultSet result = null;
		try {			
			
			Aluno aluno = (Aluno) dto;
			
			con = getConnection();	
			statement = con.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, aluno.getNome());
			statement.setString(2, aluno.getMatricula());
			statement.setString(3, aluno.getStatus());
			
			statement.executeUpdate();
						
			result =  statement.getGeneratedKeys();
			
			if(result.next()) {
				dto.setId(result.getInt(1));
			}
			
			
		} catch (SQLException e) {
			throw SystemException.dataBase(e);
		}finally {
			closeConnection(result, statement, con);
		}
		
		return dto;
	}

	@Override
	public EntityDto update(EntityDto dto) {
		
		StringBuilder sql = new StringBuilder();		
		sql.append("UPDATE ALUNO SET ALUN_NM_ALUNO=?, ALUN_NR_MATRICULA=?, ALUN_ST_ALUNO=? WHERE ALUN_CD_ALUNO = ?");		
		
		Connection con = null;
		PreparedStatement statement = null;
				
		try {
			Aluno aluno = (Aluno)dto;
			
			con = getConnection();
			
			statement = con.prepareStatement(sql.toString());
			
			statement.setString(1, aluno.getNome());
			statement.setString(2, aluno.getMatricula());
			statement.setString(3, aluno.getStatus());
			statement.setInt(4, aluno.getId());
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			throw SystemException.dataBase(e);
		}finally {
			closeConnection(null, statement, con);
		}
		
		return dto;
	}

	@Override
	public void delete(EntityDto dto) {
		StringBuilder sql = new StringBuilder();		
		sql.append("DELETE FROM ALUNO WHERE ALUN_CD_ALUNO = ?");		
		
		Connection con = null;
		PreparedStatement statement = null;
				
		try {
			Aluno aluno = (Aluno)dto;
			
			con = getConnection();
			
			statement = con.prepareStatement(sql.toString());
			
			statement.setInt(1, aluno.getId());
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			throw SystemException.dataBase(e);
		}finally {
			closeConnection(null, statement, con);
		}
		
	}

	

}
