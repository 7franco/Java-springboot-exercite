package com.jfranco.springboot.jpa.springboot_jpa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.jfranco.springboot.jpa.springboot_jpa.dto.PersonDto;
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
		personalizedQueriesBetween();
	}

	@Transactional
	public void personalizedQueriesBetween(){
		System.out.println("==== Consulta personalizedQueriesBetween ======");
		List<Person> persons = repository.findAllBetweenId(2L,5L);
		persons.forEach(System.out::println);

		System.out.println("==== Consulta entre j y p ======");
		Scanner scanner = new Scanner(System.in);
		System.out.print("Ingrese el primer valor: ");
		String c1 =scanner.next();
		System.out.print("Ingrese el segundo valor: ");
		String c2 =scanner.next();
		scanner.close();
		persons = repository.findAllBetweenName(c1, c2);
		persons.forEach(System.out::println);

		System.out.println("==== Consulta findByIdBetween ======");
		persons = repository.findByIdBetween(2L,5L);
		persons.forEach(System.out::println);

		System.out.println("==== Consulta findByNameBetween ======");
		persons = repository.findByNameBetween("J","P");
		persons.forEach(System.out::println);
	}

	@Transactional
	public void personalizedQueriesConcatUpperAndLowerCase(){
		System.out.println("==== Consulta personalizedQueriesConcatUpperAndLowerCase ======");
		List<String> names = repository.getFullNameConcat();
		names.forEach(System.out::println);

		System.out.println("==== UPPER ======");
		List<String> namesUpper = repository.getFullNameConcatUpper();
		namesUpper.forEach(System.out::println);

		System.out.println("==== Lower ======");
		List<String> nameslower = repository.getFullNameConcatLower();
		nameslower.forEach(System.out::println);
	}

	@Transactional
	public void personalizedQueries(){
		System.out.println("==== Consulta con nombres de personas ======");
		List<String> names = repository.findALlNames();
		names.forEach(System.out::println);

		System.out.println("==== Consulta con nombres de personas  Distintos======");
		List<String> namesUnicos = repository.findALlNamesDistinct();
		namesUnicos.forEach(System.out::println);

		System.out.println("==== Consulta los Distintos lenguajes de programacion======");
		List<String> lenguajes = repository.findAllLanguageDistinct();
		lenguajes.forEach(System.out::println);


		System.out.println("==== Consulta el total de los lenguajes de programacion======");
		Long dato = repository.findAllLanguageDistinctCount();
		System.out.println("Total: "+ dato);
	} 

	@Transactional
	public void personalizeQueries2(){
		System.out.println("====================== Consulta con objeto persona ======================");
		List<Object[]> personsRegs = repository.findAllMixPerson();
		personsRegs.forEach(p ->{
			System.out.println("ProgrammingLanguage="+p[1] + ", person="+p[0]);
		});

		System.out.println("=============== Consulta que puebla ================");
		List<Person> persons = repository.findAllObjectPersonPersonalized();
		persons.forEach(p-> System.out.println(p));
		System.out.println("======== COnsulta que puebla y devuelve objeto dto de una clase personalizada ===================");
		List<PersonDto> personDtos = repository.findAllPersonDtoPersonalized();
		personDtos.forEach(p-> System.out.println(p));
	}

	@Transactional
	public void personalizeQueries(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("====================== Consulta solo el nombre ======================");
		System.err.println("Ingrese el id para el nombre");
		Long id =scanner.nextLong();
		scanner.close();
		String name = repository.getNameById(id);
		System.out.println(name);
		String fullName = repository.getFullNameById(id);
		System.out.println(fullName);

		System.out.println("================ Mostrando Persona Personalizado ================");
		System.out.println("Consulta por campo personalizador por el id");
		Object[] personReg = (Object[]) repository.obtenerPersonDataFullById(id);
		System.out.println("id= "+ personReg[0]+ ", nombre= "+ personReg[1]+ ", apellido= "+ personReg[2]+ ", lenguaje= "+ personReg[3]);

		System.out.println("================ Consultar campos personalizados lista ================");
		List<Object[]> regs = repository.obtenerPersonDataFullList();
		regs.forEach(r-> System.out.println("id= "+ r[0]+ ", nombre= "+ r[1]+ ", apellido= "+ r[2]+ ", lenguaje= "+ r[3]));
	}

	@Transactional
	public void delete2(){
		repository.findAll().forEach(System.out::println);
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el ID de la persona a eliminar");
		Long id = scanner.nextLong();
		
		Optional<Person> optionalPerson = repository.findById(id);
		//1
		optionalPerson.ifPresentOrElse(person-> repository.delete(person), ()->System.out.println("Lo sentimos no existe la persona con ese id!"));
		//2
		optionalPerson.ifPresentOrElse(repository::delete, ()->System.out.println("Lo sentimos no existe la persona con ese id!"));
		//3
		if(optionalPerson.isPresent()){
			Person p = optionalPerson.orElseThrow();
			System.out.println(p);
			System.out.println("Ingrese el lenguaje de programación: ");
			String programmingLanguage = scanner.next();
			p.setProgrammingLanguage(programmingLanguage);
			repository.delete(p);
			System.out.println(p);
		}else{
			System.out.println("El usuario no existe...!");
		}

		repository.findAll().forEach(System.out::println);
		scanner.close();
	}


	@Transactional
	public void delete(){
		repository.findAll().forEach(System.out::println);
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el ID de la persona a eliminar");
		Long id = scanner.nextLong();
		repository.deleteById(id);
		repository.findAll().forEach(System.out::println);
		scanner.close();
	}

	@Transactional
	public void update(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el ID de la persona a editar");
		Long id = scanner.nextLong();
		Optional<Person> optionalPerson = repository.findById(id);
		
		/*optionalPerson.ifPresent(p ->{
			System.out.println(p);
			System.out.println("Ingrese el lenguaje de programación: ");
			String programmingLanguage = scanner.next();
			p.setProgrammingLanguage(programmingLanguage);
			repository.save(p);
			System.out.println(p);
		});*/

		if(optionalPerson.isPresent()){
			Person p = optionalPerson.orElseThrow();
			System.out.println(p);
			System.out.println("Ingrese el lenguaje de programación: ");
			String programmingLanguage = scanner.next();
			p.setProgrammingLanguage(programmingLanguage);
			repository.save(p);
			System.out.println(p);
		}else{
			System.out.println("El usuario no existe...!");
		}

			


		scanner.close();
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
