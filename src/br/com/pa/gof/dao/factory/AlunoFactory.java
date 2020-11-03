package br.com.pa.gof.dao.factory;

import br.com.pa.gof.dao.IAlunoDAO;
import br.com.pa.gof.dao.IProfessorDAO;
import br.com.pa.gof.dao.core.AlunoDAO;

public class AlunoFactory extends DaoFactory{

	@Override
	public IAlunoDAO getAluno() {
			return new AlunoDAO();
	}

	@Override
	public IProfessorDAO getProfessor() {
		return null;
	}
	

}
