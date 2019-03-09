package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import model.entitites.Employee;
import model.services.EmployeeService;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		List<Employee> list = new ArrayList<>();

		System.out.print("Enter full file path: ");
		String path = sc.next();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			String line = br.readLine();
			while (line != null) {
				
				String[] fields = line.split(",");
				list.add(new Employee(fields[0],fields[1],Double.parseDouble(fields[2])));
				line = br.readLine();
			}
			
			System.out.print("Enter salary: ");
			double tempSalary = sc.nextDouble();
			
			System.out.println("Email of people whose salary is more than " + String.format("%.2f", tempSalary) + ":");
			
			List<String> names = list.stream().filter(e -> e.getSalary() >= tempSalary).map(e -> e.getEmail()).sorted().collect(Collectors.toList());
			
			names.forEach(System.out::println);
			
			System.out.print("Enter an email from the initial email group: ");
			char x = sc.next().charAt(0);
			
			EmployeeService es = new EmployeeService();
			
			double sum = es.employeeSum(list, e -> e.getName().charAt(0) == x);
			
			System.out.println("Sum of salary of people whose name starts with : " + "'"+ x + "' " + String.format("%.2f", sum));

		} catch (IOException e) {
			System.out.println("Error " + e.getMessage());
		}
		sc.close();
	}
}