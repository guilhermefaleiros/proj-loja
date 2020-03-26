package com.guifaleiros.comercial.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.guifaleiros.comercial.dto.ClientDTO;
import com.guifaleiros.comercial.dto.NewClientDTO;
import com.guifaleiros.comercial.models.Address;
import com.guifaleiros.comercial.models.City;
import com.guifaleiros.comercial.models.Client;
import com.guifaleiros.comercial.models.enums.ETypeClient;
import com.guifaleiros.comercial.repositories.AddressRepository;
import com.guifaleiros.comercial.repositories.ClientRepository;
import com.guifaleiros.comercial.services.exceptions.DataIntegrityException;
import com.guifaleiros.comercial.services.exceptions.ObjectNotFoundException;



@Service
public class ClientService {
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	public Client find(Integer id){
		Optional<Client> response = this.clientRepository.findById(id);
		return response.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Client.class.getName()));
	}
	
	@Transactional
	public Client insert(Client obj) {
		obj.setId(null);
		obj = clientRepository.save(obj);
		addressRepository.saveAll(obj.getAddresses());
		return obj;
	}
	
	public Client update(Client obj) {
		Client newObj = find(obj.getId());
		updateData(newObj, obj);
		return clientRepository.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			clientRepository.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir, porque há entidades relacionadas");
		}
	}
	
	public List<Client> findAll() {
		return this.clientRepository.findAll();
	}
	
	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),
				orderBy);
		return clientRepository.findAll(pageRequest);
	}
	
	public Client fromDTO(ClientDTO dto) {
		return new Client(dto.getId(), dto.getName(), dto.getEmail(), null, null);
	}
	
	public Client fromDTO(NewClientDTO dto) {
		Client client = new Client(null, dto.getName(), dto.getEmail(),
				dto.getCpfOrCnpj(), ETypeClient.toEnum(dto.getType()));
		City city = new City(dto.getCityId(), null, null);
		Address address = new Address(null, dto.getLogradouro(), dto.getNumber(), dto.getComplement(),
				dto.getNeighborhood(), dto.getCep(), client, city);
		client.getAddresses().add(address);
		client.getPhones().add(dto.getPhone1());
		if(dto.getPhone2() != null) {
			client.getPhones().add(dto.getPhone2());
		}
		if(dto.getPhone3() != null) {
			client.getPhones().add(dto.getPhone3());
		}
		
		return client;
	}
	
	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
}
