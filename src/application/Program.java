package application;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {


		Locale.setDefault(Locale.US);
		
		Scanner sc = new Scanner(System.in);
		
		
		System.out.print("Enter the full file path: ");
		String path = sc.nextLine();
		
		System.out.print("Enter salary: ");
		double userSalary = sc.nextDouble();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			
			List<Employee> list = new ArrayList<>();
			
			String line = br.readLine();
			while (line != null) {
				
				String[] fields = line.split(",");
				
				list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
				
				line = br.readLine();
			}
			
			Comparator<String> comp = (x,y) -> x.toUpperCase().compareTo(y.toUpperCase());
			
			List<String> orderEmail = list.stream()
					.filter(x -> x.getSalary() > userSalary)
					.map(x -> x.getEmail())
					.sorted(comp)
					.collect(Collectors.toList());
			
			System.out.printf("Email of people whose salary of more than %.2f \n", userSalary);
			orderEmail.forEach(System.out::println);
			
			
			double sumSalaryM = list.stream()
					.filter(x -> x.getName().charAt(0) == 'M')
					.map(x -> x.getSalary())
					.reduce(0.0, (x,y) -> x + y);	
			
			
			System.out.println("Sum of salary of people whose name starts with 'M': " + String.format("%.2f", sumSalaryM));
		}
		
		catch (IOException e){
			System.out.println("Error:" + e.getMessage());
		}
			
		sc.close();
	}

}
