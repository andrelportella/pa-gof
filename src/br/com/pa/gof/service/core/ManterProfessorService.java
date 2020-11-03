package br.com.pa.gof.service.core;

import java.util.List;


import br.com.pa.gof.common.dto.EntityDto;
import br.com.pa.gof.common.dto.Professor;
import br.com.pa.gof.common.exception.BusinessException;
import br.com.pa.gof.common.exception.SystemException;
import br.com.pa.gof.dao.IProfessorDAO;
import br.com.pa.gof.dao.core.ProfessorDAO;
import br.com.pa.gof.service.IManterProfessorService;

public class ManterProfessorService implements IManterProfessorService {

	private IProfessorDAO professorDAO = new ProfessorDAO();

	public List<EntityDto> obterTodos() throws BusinessException {
		List<EntityDto> lista = null;

		try {
			lista = professorDAO.findAll();
		} catch (SystemException e) {
			throw new BusinessException("Erro ao acessar o banco de dados! Procure o administrador do sistema.");
		}

		return lista;
	}

	public Professor salvar(Professor professor) throws BusinessException {

		if (professor == null) {
			throw new BusinessException("Dados do professor não informado.");
		} else if (professor.getNome() == null || professor.getNome().trim().equals("")) {
			throw new BusinessException("Nome do professor não informado.");
		} else if (professor.getDataNascimento() == null || professor.getDataNascimento().equals("")) {
			throw new BusinessException("Data de Nascimento do professor não informado.");
		} else if (professor.getNumPrevSocial() == null || professor.getNumPrevSocial().equals("")) {
			throw new BusinessException("Status do professor não informado.");
		} else if (professor.getStatus() == null || professor.getStatus().trim().equals("")) {
			throw new BusinessException("Status do professor não informado.");
		} else if (professor.getDepartamento() == null || professor.getDepartamento().trim().equals("")) {
			throw new BusinessException("Status do professor não informado.");
		}

		try {
			professor = (Professor) professorDAO.save(professor);
		} catch (SystemException e) {
			throw new BusinessException("Erro ao acessar o banco de dados! Procure o administrador do sistema.");
		}

		return professor;
	}

	@Override
	public void deletar(EntityDto dto) throws BusinessException {
		if (dto == null) {
			throw new BusinessException("Selecione um registro existente de aluno");
		}

		IProfessorDAO professorDAO = new ProfessorDAO();
		try {
			professorDAO.delete(dto);
		} catch (SystemException e) {
			throw new BusinessException(
					"Erro ao acessar a base de dados para excluir o professor, contate o administrador do sistema");
		}

	}

	@Override
	public Professor atualizar(Professor professor) throws BusinessException {
		if (professor.getNome().isEmpty()) {
			throw new BusinessException("O campo nome precisa ser preenchido.");
		}
		if (professor.getDepartamento().isEmpty()) {
			throw new BusinessException("O Campo Departamento precisa ser preenchido.");
		}
		try {
			professor = (Professor) professorDAO.update(professor);
		} catch (SystemException ex) {
			throw new BusinessException(
					"Erro ao acessar a base de dados para atualizar o professor, contate o administrador do sistema");
		}
		System.out.println("Registro de professor atualizado.");

		return professor;

	}

	@Override
	public Professor obterPorId(Integer id) throws BusinessException {
		if (id == null) {
			throw new BusinessException("Por favor, informe um ID válido");
		}
		Professor professor = null;
		try {
			professor = (Professor) professorDAO.findById(id);
		} catch (SystemException ex) {
			throw new BusinessException(
					"Erro ao acessar a base de dados para atualizar o professor, contate o administrador do sistema");
		}

		return professor;
	}

}
