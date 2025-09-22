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
import com.franco.springboot.jpa.springboot_jpa_relationship.entities.Course;
import com.franco.springboot.jpa.springboot_jpa_relationship.entities.Invoice;
import com.franco.springboot.jpa.springboot_jpa_relationship.entities.Student;
import com.franco.springboot.jpa.springboot_jpa_relationship.repository.ClientDetailsRepository;
import com.franco.springboot.jpa.springboot_jpa_relationship.repository.ClientRepository;
import com.franco.springboot.jpa.springboot_jpa_relationship.repository.CourseRepository;
import com.franco.springboot.jpa.springboot_jpa_relationship.repository.InvoiceRepository;
import com.franco.springboot.jpa.springboot_jpa_relationship.repository.StudentRepository;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner{

	@Autowired
	private ClientRepository clientRepository; 

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private ClientDetailsRepository clientDetailsRepositoy;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseRepository courseRepository;

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
		// oneToOneFindById();
		// oneToOneBidereccionar2();
		// oneToOneBidereccionar2FindById();
		// manyToMany();
		// manyToManyFind();
		// removeManyToManyFind();
		manyToManyRemove();
	}

	@Transactional
	public void manyToManyRemove(){
		Student student1 =  new Student("Krismary", "Lopez");
		Student student2 =  new Student("Alejandro", "Cede;o");

		Course course1 = new Course("Curso de Java master", "Andres");
		Course course2 = new Course("Curso de Spring Boot", "Andres");
		
		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(Set.of(student1, student2));
		
		System.out.println(Set.of(student1, student2));

		Optional<Student> studentOptionalDB = studentRepository.findOneWithCourses(3L);
		
		if(studentOptionalDB.isPresent()){
			Student studentDb = studentOptionalDB.orElseThrow();
			Optional<Course> courseOptionalDB = courseRepository.findById(3L);
			if(courseOptionalDB.isPresent()){
				Course courseDB = courseOptionalDB.orElseThrow();
				studentDb.getCourses().remove(courseDB);
				
				studentRepository.save(studentDb);
				System.out.println(studentDb);
			}
		}
		
	}

	@Transactional
	public void removeManyToManyFind(){
		Optional<Student> studentOptional1 =  studentRepository.findById(1L);
		Optional<Student> studentOptional2 =  studentRepository.findById(2L);
		Student student1 = studentOptional1.get();
		Student student2 = studentOptional2.get();
		
		Optional<Course> courseOptional1 = courseRepository.findById(1L);
		Optional<Course> courseOptional2 = courseRepository.findById(2L);

		Course course1 = courseOptional1.orElseThrow();
		Course course2 = courseOptional2.orElseThrow();
		
		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(Set.of(student1, student2));
		
		System.out.println(Set.of(student1, student2));

		Optional<Student> studentOptionalDB = studentRepository.findOneWithCourses(1L);
		
		if(studentOptionalDB.isPresent()){
			Student studentDb = studentOptionalDB.orElseThrow();
			Optional<Course> courseOptionalDB = courseRepository.findById(2L);
			if(courseOptionalDB.isPresent()){
				Course courseDB = courseOptionalDB.orElseThrow();
				studentDb.getCourses().remove(courseDB);
				
				studentRepository.save(studentDb);
				System.out.println(studentDb);
			}
		}
		
	}

	@Transactional
	public void manyToManyFind(){
		Optional<Student> studentOptional1 =  studentRepository.findById(1L);
		Optional<Student> studentOptional2 =  studentRepository.findById(2L);
		Student student1 = studentOptional1.get();
		Student student2 = studentOptional2.get();
		
		Optional<Course> courseOptional1 = courseRepository.findById(1L);
		Optional<Course> courseOptional2 = courseRepository.findById(2L);

		Course course1 = courseOptional1.orElseThrow();
		Course course2 = courseOptional2.orElseThrow();
		
		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(Set.of(student1, student2));
		
		System.out.println(Set.of(student1, student2));
		
	}

	@Transactional
	public void manyToMany(){
		Student student1 =  new Student("Krismary", "Lopez");
		Student student2 =  new Student("Alejandro", "Cede;o");

		Course course1 = new Course("Curso de Java master", "Andres");
		Course course2 = new Course("Curso de Spring Boot", "Andres");
		
		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(Set.of(student1, student2));
		
		System.out.println(Set.of(student1, student2));
		
	}

	@Transactional
	public void oneToOneBidereccionar2FindById(){
		Optional<Client> clientOptional = clientRepository.findOne(2L);
		clientOptional.ifPresent(client->{
			ClientDetails clientDetail = new ClientDetails(true, 3000);
			client.setClientDetails(clientDetail);	
			clientRepository.save(client);
			System.out.println(client);
		});
	}

	@Transactional
	public void oneToOneBidereccionar2(){
		Client client = new Client("Paola","Lopez");
		ClientDetails clientDetail = new ClientDetails(true, 3000);
		client.setClientDetails(clientDetail);
		
		clientRepository.save(client);
		System.out.println(client);
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
