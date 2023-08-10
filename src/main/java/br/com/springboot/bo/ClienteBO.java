package br.com.springboot.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springboot.dao.CRUD;
import br.com.springboot.dao.ClienteDAO;
import br.com.springboot.model.Cliente;

@Service
public class ClienteBO implements CRUD<Cliente, Long>{
	@Autowired
	private ClienteDAO dao;

	@Override
	public Cliente pesquisaPeloID(Long id) {
		return dao.pesquisaPeloID(id);
	}

	@Override
	public List<Cliente> lista() {
		return dao.lista();
	}

	@Override
	public void insere(Cliente t) {
		dao.insere(t);
	}

	@Override
	public void atualiza(Cliente t) {
		dao.atualiza(t);
		
	}

	@Override
	public void remove(Cliente t) {
		dao.remove(t);
		
	}
	
	public void inativa(Cliente cliente) {
		cliente.setAtivo(false);
		dao.atualiza(cliente);
	}

	public void ativa(Cliente cliente) {
		cliente.setAtivo(true);
		dao.atualiza(cliente);
	}
}
