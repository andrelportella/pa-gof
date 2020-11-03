package br.com.pa.gof.dao;

import java.util.List;

import br.com.pa.gof.common.dto.EntityDto;

public interface IGenericDAO {
	public EntityDto findById(Integer id);
	public List<EntityDto> findAll();
	public EntityDto save(EntityDto dto);
	public EntityDto update(EntityDto dto);
	public void delete(EntityDto dto);
}
