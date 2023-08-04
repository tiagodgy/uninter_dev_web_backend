package br.com.springboot.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.springboot.model.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class ClienteDAO implements CRUD<Cliente, Long>{
	
	@PersistenceContext
	private EntityManager entityManager; 

	@Override
	public Cliente pesquisaPeloID(Long id) {
		return entityManager.find(Cliente.class, id);
	}

	@Override
	public List<Cliente> lista() {
		Query query = entityManager.createQuery("Select c from Cliente c");
		return (List<Cliente>) query.getResultList();
	}

	@Override
	public void insere(Cliente t) {
		entityManager.persist(t);
		
	}

	@Override
	public void atualiza(Cliente t) {
		entityManager.merge(t);
		
	}

	@Override
	public void remove(Cliente t) {
		entityManager.remove(t);
		
	}

}
