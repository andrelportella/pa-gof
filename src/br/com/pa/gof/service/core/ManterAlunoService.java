package br.com.pa.gof.service.core;

import java.util.List;

import br.com.pa.gof.common.dto.Aluno;
import br.com.pa.gof.common.dto.EntityDto;
import br.com.pa.gof.common.exception.BusinessException;
import br.com.pa.gof.common.exception.SystemException;
import br.com.pa.gof.dao.IAlunoDAO;
import br.com.pa.gof.dao.core.AlunoDAO;
import br.com.pa.gof.dao.factory.AlunoFactory;
import br.com.pa.gof.dao.factory.DaoFactory;
import br.com.pa.gof.service.IManterAlunoService;

public class ManterAlunoService implements IManterAlunoService {

	DaoFactory alunoFactory = new AlunoFactory();
	
	IAlunoDAO alunoDAO = alunoFactory.getAluno();
	
	@Override
	public List<EntityDto> obterTodos() throws BusinessException {
		List<EntityDto> lista = null;
		
		try {
			lista = alunoDAO.findAll();
		}catch (SystemException e) {
			throw new BusinessException("Erro ao acessar o banco de dados! Procure o administrador do sistema.");
		}
		
		return lista;
	}

	@Override
	public Aluno salvar(Aluno aluno) throws BusinessException {
		
		if(aluno == null) {
			throw new BusinessException("Dados do aluno não informado.");
		}else if(aluno.getNome() == null || aluno.getNome().trim().equals("")) {
			throw new BusinessException("Nome do aluno não informado.");
		}else if(aluno.getMatricula() == null || aluno.getMatricula().trim().equals("")) {
			throw new BusinessException("Matricula do aluno não informado.");
		}else if(aluno.getStatus() == null || aluno.getStatus().trim().equals("")) {
			throw new BusinessException("Status do aluno não insformado.");
		}
		
		try {
			aluno = (Aluno)alunoDAO.save(aluno);
		}catch (SystemException e) {
			throw new BusinessException("Erro ao acessar o banco de dados! Procure o administrador do sistema.");
		}
		
		return aluno;
	}
	
	   @Override
		public void deletar(EntityDto dto) throws BusinessException {
	    	if(dto  == null) {
					throw new BusinessException("Selecione um registro existente de aluno");
				}
		
			IAlunoDAO alunoDAO = new AlunoDAO();
			try {
				alunoDAO.delete(dto);
			} catch (SystemException e) {
				throw new BusinessException("Erro ao acessar a base de dados para excluir o aluno, contate o administrador do sistema");
			}

		}
	   
	    @Override
		public Aluno atualizar(Aluno aluno) throws BusinessException {
			if (aluno.getNome().isEmpty()) {
				throw new BusinessException("O campo nome precisa ser preenchido.");	
			}
			if (aluno.getMatricula().isEmpty()) {
				throw new BusinessException("O Campo matricula precisa ser preenchido.");	
			}
			try {
				aluno = (Aluno)alunoDAO.update(aluno);
			} catch (SystemException ex) {
				throw new BusinessException("Erro ao acessar a base de dados para atualizar o aluno, contate o administrador do sistema");	
			}
			System.out.println("Registro de aluno atualizado.");
			return aluno;

		}
	    
	    @Override
	    public Aluno obterPorId(Integer id) throws BusinessException {
	        if (id == null) {
	            throw new BusinessException("Por favor, informe um ID válido");
	        }
	        Aluno aluno = null;
			try {
	            aluno = (Aluno)alunoDAO.findById(id);
	        } catch (SystemException ex) {
	            throw new BusinessException("Erro ao acessar a base de dados para atualizar o aluno, contate o administrador do sistema");
	        }

	        return aluno;
	    }
}
