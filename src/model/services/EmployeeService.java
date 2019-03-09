package model.services;

import java.util.List;
import java.util.function.Predicate;

import model.entitites.Employee;

public class EmployeeService {
	
	public double employeeSum(List<Employee> list, Predicate<Employee> criterio) {
		
		double sum = 0;
		
		for (Employee func : list) {
			if(criterio.test(func)) {
				sum += func.getSalary();
			}
		}
		return sum;
		
	}

}
