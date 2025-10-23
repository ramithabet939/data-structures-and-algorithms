
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

public class SalaryDistributor {

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        System.out.print("Input the number of salary groups: ");
        int groupCount = Integer.parseInt(inputScanner.nextLine().trim());
        
        List<String> fileContent;
        try {
            fileContent = Files.readAllLines(Paths.get("Employees.txt"));
        } catch (IOException e) {
            System.out.println("Error reading Employees.txt: " + e.getMessage());
            inputScanner.close();
            return;
        }
        
        double minSalary = Double.parseDouble(fileContent.get(0).trim());
        double maxSalary = Double.parseDouble(fileContent.get(1).trim());
        double groupSpan = (maxSalary - minSalary) / groupCount;
        
        List<StaffMember>[] groups = new ArrayList[groupCount];
        for (int i = 0; i < groupCount; i++) {
            groups[i] = new ArrayList<>();
        }
        Map<Integer, StaffMember> staffDirectory = new HashMap<>();
        
        for (int i = 2; i < fileContent.size(); i++) {
            String[] tokens = fileContent.get(i).trim().split("\\s+");
            if (tokens.length < 2) {
                continue;
            }
            int id = Integer.parseInt(tokens[0]);
            double salary = Double.parseDouble(tokens[1]);
            if (staffDirectory.containsKey(id)) {
                System.out.println("Duplicate ID " + id + " encountered. Ignored.");
                continue;
            }
            StaffMember person = new StaffMember(id, salary);
            int index = computeGroupIndex(salary, minSalary, groupSpan, groupCount);
            groups[index].add(person);
            staffDirectory.put(id, person);
        }
        
        System.out.println("Employees have been grouped by salary ranges.");
        IntStream.range(0, groupCount).forEach(i -> {
            double lowerBound = minSalary + i * groupSpan;
            double upperBound = minSalary + (i + 1) * groupSpan;
            System.out.println("Group " + i + " (" + lowerBound + " to " + upperBound + "): " + groups[i].size() + " employee(s).");
        });
        
        promptForNewEmployees(inputScanner, staffDirectory, groups, minSalary, groupSpan, groupCount);
        promptForSalaryQuery(inputScanner, staffDirectory);
        inputScanner.close();
    }

    private static int computeGroupIndex(double salary, double minSalary, double span, int groupCount) {
        int idx = (int) Math.floor((salary - minSalary) / span);
        if (idx < 0) {
            return 0;
        }
        if (idx >= groupCount) {
            return groupCount - 1;
        }
        return idx;
    }

    private static void promptForNewEmployees(Scanner scanner, Map<Integer, StaffMember> directory,
            List<StaffMember>[] groups, double minSalary, double span, int groupCount) {
        while (true) {
            System.out.print("Would you like to register a new employee? (y/n): ");
            String resp = scanner.nextLine().trim().toLowerCase();
            if (!resp.equals("y")) {
                break;
            }
            try {
                System.out.print("Enter employee ID: ");
                int newId = Integer.parseInt(scanner.nextLine().trim());
                System.out.print("Enter employee salary: ");
                double newSalary = Double.parseDouble(scanner.nextLine().trim());
                if (directory.containsKey(newId)) {
                    System.out.println("Employee ID " + newId + " is already registered.");
                } else {
                    StaffMember newEmployee = new StaffMember(newId, newSalary);
                    int groupIndex = computeGroupIndex(newSalary, minSalary, span, groupCount);
                    groups[groupIndex].add(newEmployee);
                    directory.put(newId, newEmployee);
                    System.out.println("Employee successfully added to group " + groupIndex + ".");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Numeric input error. Please try again.");
            }
        }
    }

    private static void promptForSalaryQuery(Scanner scanner, Map<Integer, StaffMember> directory) {
        while (true) {
            System.out.print("Do you want to lookup an employee's salary? (y/n): ");
            String answer = scanner.nextLine().trim().toLowerCase();
            if (!answer.equals("y")) {
                break;
            }
            try {
                System.out.print("Enter employee ID: ");
                int queryId = Integer.parseInt(scanner.nextLine().trim());
                StaffMember found = directory.get(queryId);
                if (found != null) {
                    System.out.println("Salary for employee ID " + queryId + ": " + found.getSalary());
                } else {
                    System.out.println("Employee with ID " + queryId + " not found.");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid ID format. Please enter a valid number.");
            }
        }
    }
}

class StaffMember {
    private int id;
    private double salary;

    public StaffMember(int id, double salary) {
        this.id = id;
        this.salary = salary;
    }
    
    public int getId() {
        return id;
    }
    
    public double getSalary() {
        return salary;
    }
    
    @Override
    public String toString() {
        return "StaffMember ID: " + id + ", Salary: " + salary;
    }
}
