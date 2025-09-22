package com.franco.springboot.jpa.springboot_jpa_relationship;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.franco.springboot.jpa.springboot_jpa_relationship.entities.Address;
import com.franco.springboot.jpa.springboot_jpa_relationship.entities.Client;
import com.franco.springboot.jpa.springboot_jpa_relationship.entities.ClientDetails;
import com.franco.springboot.jpa.springboot_jpa_relationship.entities.Invoice;
import com.franco.springboot.jpa.springboot_jpa_relationship.repository.ClientDetailsRepository;
import com.franco.springboot.jpa.springboot_jpa_relationship.repository.ClientRepository;
import com.franco.springboot.jpa.springboot_jpa_relationship.repository.InvoiceRepository;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner{

	@Autowired
	private ClientRepository clientRepository; 

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private ClientDetailsRepository clientDetailsRepositoy;
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
		// oneToManyInvoiceBidireccionlFindId();
		// removeInvoiceBidireccionlFindId();
		// removeInvoiceBidireccional();
		// oneToOne();
		// oneToOneBidereccionar();
		oneToOneFindById();
	}

	@Transactional
	public void oneToOneFindById(){
		ClientDetails clientDetail = new ClientDetails(true, 3000);
		clientDetailsRepositoy.save(clientDetail);

		ClientDetails clientDetail2 = new ClientDetails(true, 9000);
		clientDetailsRepositoy.save(clientDetail2);

		Optional<Client> clientOptional = clientRepository.findOne(2l);
		clientOptional.ifPresent(client->{
			client.setClientDetails(clientDetail2);
			clientRepository.save(client);
			System.out.println(client);
		});
		
	}

	@Transactional
	public void oneToOneBidereccionar(){
		ClientDetails clientDetail = new ClientDetails(true, 3000);
		clientDetailsRepositoy.save(clientDetail);

		Client client = new Client("Paola","Lopez");
		client.setClientDetails(clientDetail);
		clientRepository.save(client);
	
		System.out.println(client);
	}

	@Transactional
	public void oneToOne(){
		Client client = new Client("Paola","Lopez");
		clientRepository.save(client);

		ClientDetails clientDetail = new ClientDetails(true, 3000);
		// clientDetail.setClient(client);
		clientDetailsRepositoy.save(clientDetail);
		System.out.println(clientDetail);
	}


	@Transactional
	public void removeInvoiceBidireccional(){
		Optional<Client> optionalCliente = Optional.of(new Client("Fran", "Jonathan"));
		optionalCliente.ifPresent(client->{
			Invoice invoice1 = new Invoice("Compra de casa", 5000L);
			Invoice invoice2 = new Invoice("Compra de oficina", 8000L);
			client.addInvoice(invoice1).addInvoice(invoice2);
			clientRepository.save(client);
			System.out.println(client);
		});	
		
		Optional<Client> optionalClienteBd = clientRepository.findOne(3L);
		optionalClienteBd.ifPresent(clientDb->{
			Optional<Invoice> invoiceOptional = invoiceRepository.findById(2L);
			invoiceOptional.ifPresent(invoice->{
				clientDb.removeInvoice(invoice);
				clientRepository.save(clientDb);
				System.out.println(clientDb);
			});
		});
		
	}

	@Transactional
	public void removeInvoiceBidireccionlFindId(){
		Optional<Client> optionalCliente = clientRepository.findOne(1l);
		optionalCliente.ifPresent(client->{
			Invoice invoice1 = new Invoice("Compra de casa", 5000L);
			Invoice invoice2 = new Invoice("Compra de oficina", 8000L);
			client.addInvoice(invoice1).addInvoice(invoice2);
			clientRepository.save(client);
			System.out.println(client);
		});	
		
		Optional<Client> optionalClienteBd = clientRepository.findOne(1l);
		optionalClienteBd.ifPresent(client->{
			Optional<Invoice> invoiceOptional = invoiceRepository.findById(1l);
			invoiceOptional.ifPresent(invoice->{
				client.removeInvoice(invoice);
				clientRepository.save(client);
				System.out.println(client);
			});
		});
		
	}

	@Transactional
	public void oneToManyInvoiceBidireccionlFindId(){
		Optional<Client> optionalCliente = clientRepository.findOne(1l);
		optionalCliente.ifPresent(client->{
			Invoice invoice1 = new Invoice("Compra de casa", 5000L);
			Invoice invoice2 = new Invoice("Compra de oficina", 8000L);
			client.addInvoice(invoice1).addInvoice(invoice2);
			clientRepository.save(client);
			System.out.println(client);
		});		
		
	}


	@Transactional
	public void oneToManyInvoiceBidireccionl(){
		Client client = new Client("Fran", "Jonathan");
		
		Invoice invoice1 = new Invoice("Compra de casa", 5000L);
		Invoice invoice2 = new Invoice("Compra de oficina", 8000L);

		client.addInvoice(invoice1).addInvoice(invoice2);
		clientRepository.save(client);
		System.out.println(client);
	}

	@Transactional
	public void removeOneToManyFindId(){
		Optional<Client> optionalClient = clientRepository.findById(2L);
		optionalClient.ifPresent(client->{
			Address address1 = new Address("El vergel",1234);
			Address address2 = new Address("El cambio",4321);
		
			Set<Address> addresses = new HashSet<>();
			addresses.add(address1);
			addresses.add(address2);
			client.setAddresses(addresses);

	
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
		
			Set<Address> addresses = new HashSet<>();
			addresses.add(address1);
			addresses.add(address2);
			client.setAddresses(addresses);

	
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
