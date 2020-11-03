package br.com.pa.gof.presentation;

import java.text.ParseException;
import java.util.List;

import br.com.pa.gof.common.dto.EntityDto;
import br.com.pa.gof.common.dto.Professor;
import br.com.pa.gof.dao.IProfessorDAO;
import br.com.pa.gof.dao.factory.DaoFactory;
import br.com.pa.gof.dao.factory.ProfessorFactory;

public class AppMain {

	public static void main(String[] args) throws ParseException {

		DaoFactory professorFactory = new ProfessorFactory();

		IProfessorDAO professor = professorFactory.getProfessor();

		List<EntityDto> lista = professor.findAll();

		try {

			if (lista != null) {
				for (EntityDto dto : lista) {
					Professor prof = (Professor) dto;
					System.out.println(prof.getId() + " - " + prof.getNome());
				}
			} else {
				System.out.println("Nenhum registro foi encontrado!");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
