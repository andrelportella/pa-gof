package br.com.pa.gof.dao.factory;

import br.com.pa.gof.dao.IAlunoDAO;
import br.com.pa.gof.dao.IProfessorDAO;
import br.com.pa.gof.dao.core.ProfessorDAO;

public class ProfessorFactory extends DaoFactory {

	@Override
	public IAlunoDAO getAluno() {
		return null;
	}

	@Override
	public IProfessorDAO getProfessor() {
			return new ProfessorDAO();
	}

}
