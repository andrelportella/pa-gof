package br.com.pa.gof.dao.factory;

import br.com.pa.gof.dao.IAlunoDAO;
import br.com.pa.gof.dao.IProfessorDAO;

public abstract class DaoFactory {
	
	public abstract IAlunoDAO getAluno();
	public abstract IProfessorDAO getProfessor();
}
