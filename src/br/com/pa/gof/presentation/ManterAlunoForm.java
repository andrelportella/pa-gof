package br.com.pa.gof.presentation;

import java.util.List;

import br.com.pa.gof.common.dto.Aluno;
import br.com.pa.gof.common.dto.EntityDto;
import br.com.pa.gof.common.exception.BusinessException;
import br.com.pa.gof.service.IManterAlunoService;
import br.com.pa.gof.service.core.ManterAlunoService;

public class ManterAlunoForm {

	public static void main(String[] args) {
		IManterAlunoService service = new ManterAlunoService();
		
		try {
			
		Aluno aluno = new Aluno();
		aluno.setMatricula("202016");
		aluno.setNome("Antonio Nunes");
		aluno.setStatus(Aluno.ATIVO);
		
		service.salvar(aluno);
		
		List<EntityDto> lista = service.obterTodos();
		
		if(lista!=null) {
			for (EntityDto dto : lista) {
				Aluno alun = (Aluno)dto;
				System.out.println(alun.getMatricula()+" - "+alun.getNome());
			}
		}else {
			System.out.println("Nenhum registro foi encontrado!");	
		}
		}catch (BusinessException e) {
			System.out.println(e.getMessage());
		}

	}

}
