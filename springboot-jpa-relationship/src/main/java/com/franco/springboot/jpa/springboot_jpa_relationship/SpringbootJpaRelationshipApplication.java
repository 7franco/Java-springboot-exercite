package com.franco.springboot.jpa.springboot_jpa_relationship;

import java.nio.file.OpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.franco.springboot.jpa.springboot_jpa_relationship.entities.Address;
import com.franco.springboot.jpa.springboot_jpa_relationship.entities.Client;
import com.franco.springboot.jpa.springboot_jpa_relationship.entities.Invoice;
import com.franco.springboot.jpa.springboot_jpa_relationship.repository.ClientRepository;
import com.franco.springboot.jpa.springboot_jpa_relationship.repository.InvoiceRepository;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner{

	@Autowired
	private ClientRepository clientRepository; 

	@Autowired
	private InvoiceRepository invoiceRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//manyToOne();
		//manyToOneFindByIdClient();
		//oneToManyFindId();
		// removeAddress();
		// removeOneToManyFindId();
		// oneToManyInvoiceBidireccionl();
	}

	@Transactional
	public void oneToManyInvoiceBidireccionl(){
		Client client = new Client("Fran", "Jonathan");
		
		Invoice invoice1 = new Invoice("Compra de casa", 5000L);
		Invoice invoice2 = new Invoice("Compra de oficina", 8000L);

		List<Invoice> invoices = new ArrayList<>();
		invoices.add(invoice1);
		invoices.add(invoice2);
		client.setInvoices(invoices);

		invoice1.setClient(client);
		invoice2.setClient(client);

		clientRepository.save(client);
		System.out.println(client);
	}

	@Transactional
	public void removeOneToManyFindId(){
		Optional<Client> optionalClient = clientRepository.findById(2L);
		optionalClient.ifPresent(client->{
			Address address1 = new Address("El vergel",1234);
			Address address2 = new Address("El cambio",4321);
		
			client.setAddresses(Arrays.asList(address1, address2));

	
			Client response = clientRepository.save(client);
			System.out.println(response);

			Optional<Client> optionalClient2 = clientRepository.findOne(2L);
			optionalClient2.ifPresent(c->{
				c.getAddresses().remove(address2);
				clientRepository.save(c);
				System.out.println(c);
		});
		});

		
	}

	@Transactional
	public void removeAddress(){
		Client client = new Client("Fran", "Moras");
		Address address1 = new Address("El vergel",1234);
		Address address2 = new Address("El cambio",4321);
		client.getAddresses().add(address1);
		client.getAddresses().add(address2);
		clientRepository.save(client);
		System.out.println(client);

		Optional<Client> optionalClient = clientRepository.findById(3L);
		optionalClient.ifPresent(cl->{
			cl.getAddresses().remove(address1);
			clientRepository.save(cl);
			System.out.println(cl);
		});

	}

	@Transactional
	public void oneToManyFindId(){
		Optional<Client> optionalClient = clientRepository.findById(2L);
		optionalClient.ifPresent(client->{
			Address address1 = new Address("El vergel",1234);
			Address address2 = new Address("El cambio",4321);
		
			client.setAddresses(Arrays.asList(address1, address2));

	
			Client response = clientRepository.save(client);
			System.out.println(response);
		});

		
	}

	@Transactional
	public void oneToMany(){
		Client client = new Client("Fran", "Moras");
		Address address1 = new Address("El vergel",1234);
		Address address2 = new Address("El cambio",4321);
		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		Client response = clientRepository.save(client);

		System.out.println(response);
	}

	@Transactional
	public void manyToOne(){
		Client client = new Client("John", "Doe");
		clientRepository.save(client);


		Invoice invoice = new Invoice("Compra de oficina", 2000L);
		invoice.setClient(client);

		Invoice invoiceDB = invoiceRepository.save(invoice);
		System.out.println(invoiceDB);
	}

	@Transactional
	public void manyToOneFindByIdClient(){
		Optional<Client> optionalClient = clientRepository.findById(1L);
		if(optionalClient.isPresent()){
			Client client = optionalClient.orElseThrow();
			Invoice invoice = new Invoice("Compra de oficina2", 2000L);
			invoice.setClient(client);

			Invoice invoiceDB = invoiceRepository.save(invoice);
			System.out.println(invoiceDB);
		}
	}
}
