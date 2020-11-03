package br.com.pa.gof.service;

import java.util.List;

import br.com.pa.gof.common.dto.EntityDto;
import br.com.pa.gof.common.dto.Professor;
import br.com.pa.gof.common.exception.BusinessException;

public interface IManterProfessorService {
	public List<EntityDto> obterTodos() throws BusinessException;
	public Professor salvar(Professor professor) throws BusinessException;
	public void deletar(EntityDto dto) throws BusinessException;
	public Professor atualizar(Professor professor) throws BusinessException;
	public Professor obterPorId(Integer id) throws BusinessException;
	
}
