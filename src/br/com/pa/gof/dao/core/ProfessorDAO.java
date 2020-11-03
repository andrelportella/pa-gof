package br.com.pa.gof.dao.core;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import br.com.pa.gof.common.dto.EntityDto;
import br.com.pa.gof.common.dto.Professor;
import br.com.pa.gof.common.exception.SystemException;
import br.com.pa.gof.dao.IProfessorDAO;
import br.com.pa.gof.dao.jdbc.JdbcConnection;

public class ProfessorDAO extends JdbcConnection implements IProfessorDAO {

	@Override
	public Professor findById(Integer id) {
		Professor professor = null;

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT PROF_CD_PROFESSOR ,PROF_NM_PROFESSOR ,PROF_DATANASC_PROFESSOR , PROF_NUMPREV_PROFESSOR, PROF_ST_PROFESSOR, PROF_DEPT_PROFESSOR FROM PROFESSOR WHERE PROF_CD_PROFESSOR = ?");

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			con = getConnection();
			statement = con.prepareStatement(sql.toString());
			statement.setInt(1, id);
			result = statement.executeQuery();

			if (result.next()) {
				professor = new Professor();
				professor.setId(new Integer(result.getInt(1)));
				professor.setNome(result.getString(2));
				professor.setDataNascimento(result.getDate(3));
				professor.setNumPrevSocial(result.getInt(4));
				professor.setStatus(result.getString(5));
				professor.setDepartamento(result.getString(6));
			}

		} catch (SQLException e) {
			throw SystemException.dataBase(e);
		} finally {
			closeConnection(result, statement, con);
		}

		return professor;
	}


	@Override
	public List<EntityDto> findAll() {
		List<EntityDto> lista = new ArrayList<EntityDto>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT PROF_CD_PROFESSOR ,PROF_NM_PROFESSOR ,PROF_DATANASC_PROFESSOR , PROF_NUMPREV_PROFESSOR, PROF_ST_PROFESSOR, PROF_DEPT_PROFESSOR FROM PUBLIC.PROFESSOR");

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			con = getConnection();
			statement = con.prepareStatement(sql.toString());
			result = statement.executeQuery();

			while (result.next()) {
				Professor professor = new Professor();
				professor.setId(new Integer(result.getInt(1)));
				professor.setNome(result.getString(2));
				professor.setDataNascimento(result.getDate(3));
				professor.setNumPrevSocial(result.getInt(4));
				professor.setStatus(result.getString(5));
				professor.setDepartamento(result.getString(6));
				lista.add(professor);
			}

		} catch (SQLException e) {
			throw SystemException.dataBase(e);
		} finally {
			closeConnection(result, statement, con);
		}

		return lista;
	}

	@Override
    public EntityDto save(EntityDto dto) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO PROFESSOR (PROF_CD_PROFESSOR ,PROF_NM_PROFESSOR ,PROF_DATANASC_PROFESSOR , PROF_NUMPREV_PROFESSOR, PROF_ST_PROFESSOR, PROF_DEPT_PROFESSOR)");
        sql.append(" VALUES ");
        sql.append("(DEFAULT ,? ,? ,?, ?, ?)");

        Connection con = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            Professor professor = (Professor)dto;

            con = getConnection();
            statement = con.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, professor.getNome());
            statement.setDate(2, new Date(professor.getDataNascimento().getTime()));
            statement.setInt(3, professor.getNumPrevSocial());
            statement.setString(4, professor.getStatus());
            statement.setString(5, professor.getDepartamento());
            statement.executeUpdate();

            result = statement.getGeneratedKeys();

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
		sql.append("UPDATE PROFESSOR SET PROF_NM_PROFESSOR=?, PROF_NR_MATRICULA=?, PROF_DATANASC_PROFESSOR=?, PROF_NUMPREV_PROFESSOR=?, PROF_ST_PROFESSOR=?, PROF_DEPT_PROFESSOR WHERE PROF_CD_PROFESSOR = ?");

		Connection con = null;
		PreparedStatement statement = null;

		try {
			Professor professor = (Professor) dto;

			con = getConnection();

			statement = con.prepareStatement(sql.toString());

            statement.setString(1, professor.getNome());
            statement.setDate(2, (Date) professor.getDataNascimento());
            statement.setInt(3, professor.getNumPrevSocial());
            statement.setString(4, professor.getStatus());
            statement.setString(5, professor.getDepartamento());
			statement.setInt(6, professor.getId());

			statement.executeUpdate();

		} catch (SQLException e) {
			throw SystemException.dataBase(e);
		} finally {
			closeConnection(null, statement, con);
		}

		return dto;
	}

	@Override
	public void delete(EntityDto dto) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM PUBLIC.PROFESSOR WHERE PROF_CD_PROFESSOR = ?");

		Connection con = null;
		PreparedStatement statement = null;

		try {
			Professor professor = (Professor) dto;

			con = getConnection();

			statement = con.prepareStatement(sql.toString());

			statement.setInt(1, professor.getId());

			statement.executeUpdate();

		} catch (SQLException e) {
			throw SystemException.dataBase(e);
		} finally {
			closeConnection(null, statement, con);
		}

	}

}
