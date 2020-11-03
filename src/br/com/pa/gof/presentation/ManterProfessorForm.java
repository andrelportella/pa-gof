package br.com.pa.gof.presentation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.com.pa.gof.common.dto.EntityDto;
import br.com.pa.gof.common.dto.Professor;
import br.com.pa.gof.common.exception.BusinessException;
import br.com.pa.gof.service.IManterProfessorService;
import br.com.pa.gof.service.core.ManterProfessorService;

public class ManterProfessorForm {
	
	public static void main(String[] args) throws ParseException {
		IManterProfessorService service = new ManterProfessorService();
		
		try {
			
		Professor professor = new Professor();
		
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
		Date data = formato.parse("11/08/1981");
		
		professor.setNome("Oberdan");
		professor.setDataNascimento(data);
		professor.setNumPrevSocial(42531);
		professor.setStatus(Professor.ATIVO);
		professor.setDepartamento("TI");
		
		service.salvar(professor);

		
		List<EntityDto> lista = service.obterTodos();
		
		if(lista!=null) {
			for (EntityDto dto : lista) {
				Professor prof = (Professor)dto;
				System.out.println(prof.getId()+" - "+prof.getNome());
			}
		}else {
			System.out.println("Nenhum registro foi encontrado!");	
		}
		}catch (BusinessException e) {
			System.out.println(e.getMessage());
		}

	}

}
