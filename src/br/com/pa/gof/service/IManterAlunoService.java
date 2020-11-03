package br.com.pa.gof.service;

import java.util.List;

import br.com.pa.gof.common.dto.Aluno;
import br.com.pa.gof.common.dto.EntityDto;
import br.com.pa.gof.common.exception.BusinessException;

public interface IManterAlunoService {
	public List<EntityDto> obterTodos() throws BusinessException;
	public Aluno salvar(Aluno aluno) throws BusinessException;
	public void deletar(EntityDto dto) throws BusinessException;
	public Aluno atualizar(Aluno aluno) throws BusinessException;
	public Aluno obterPorId(Integer id) throws BusinessException;
}
