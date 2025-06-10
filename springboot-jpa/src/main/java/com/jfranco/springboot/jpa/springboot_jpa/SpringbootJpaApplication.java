package com.jfranco.springboot.jpa.springboot_jpa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.jfranco.springboot.jpa.springboot_jpa.entities.Person;
import com.jfranco.springboot.jpa.springboot_jpa.repositories.PersonRepository;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner{

	@Autowired
	private PersonRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		create();
		list();
	}

	@Transactional
	public void create(){

		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el nombre: ");
		String name = scanner.next();
		System.out.println("Ingrese el apellido: ");
		String lastName = scanner.next();
		System.out.println("Ingrese el lenguaje de programación: ");
		String programmingLanguage = scanner.next();
		scanner.close();
		Person person = new Person(null, name, lastName, programmingLanguage);
		Person personNew = repository.save(person);		
		System.out.println(personNew);
		
		repository.findById(personNew.getId()).ifPresent(p -> System.out.println(p));
		repository.findById(personNew.getId()).ifPresent(System.out::println);
	}

	@Transactional(readOnly = true)
	public void findOne(){
		//Person person = repository.findById(2L).get();
		//System.out.println(person);
		
		/*Person person = null;
		Optional<Person> optionalPerson = repository.findById(10l);
		
		if(optionalPerson.isPresent()){
			person = optionalPerson.get();
		}
		
		System.out.println(person);*/

		//repository.findById(1L).ifPresent(person-> System.out.println(person));
		repository.findOne(1L).ifPresent(System.out::println);
		repository.findName("Francisca").ifPresent(System.out::println);

		repository.findOneLikeName("al").ifPresent(System.out::println);

		repository.findByName("Alicia").ifPresent(System.out::println);

		repository.findByNameContaining("Alicia").ifPresent(System.out::println);
		
	}

	@Transactional(readOnly = true)
	public void list(){
		
		//List<Person> persons = (List<Person>) repository.findAll();
		//List<Person> persons = (List<Person>) repository.findByProgrammingLanguage("Java");
		//List<Person> persons = (List<Person>) repository.buscarByProgrammingLanguage("Java", "Jonathan");
		List<Person> persons = (List<Person>) repository.findByProgrammingLanguageAndName("Java", "Jonathan");

		persons.stream().forEach(person -> System.out.println(person));

		List<Object[]> personsValues = repository.obtenerPersonData();

		System.out.println(personsValues.size());

		personsValues.stream().forEach(person->{
			System.out.println(person[0] + " es experto en "+ person[1]);
		});
	}



}
