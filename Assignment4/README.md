# Assignment 4 – Salary Distributor

## Description
This Java program reads employee salary data from a text file (`Employees.txt`) and distributes employees into salary groups. The program also allows interactive registration of new employees and querying of salaries by employee ID.

The program performs the following tasks:  
1. Reads minimum and maximum salaries and employee salary data from `Employees.txt`.  
2. Divides employees into a specified number of salary groups.  
3. Displays the number of employees in each group.  
4. Allows registering new employees interactively while maintaining salary group classification.  
5. Allows querying the salary of any registered employee by their ID.  

## Files
- `SalaryDistributor.java` – main program containing the salary grouping, registration, and query logic.  
- `Employees.txt` – input file containing the salary data. First two lines are `minSalary` and `maxSalary`, followed by lines with `<ID> <Salary>` pairs.  

## Features
- Salary Grouping: Employees are grouped by salary range based on the total number of groups specified by the user.  
- Interactive Registration: Allows adding new employees dynamically, automatically placing them in the correct salary group.  
- Salary Query: Allows checking the salary of an employee by their unique ID.  
- Duplicate Handling: Automatically ignores duplicate employee IDs in the input file or during registration.  

## Classes

### SalaryDistributor
- Handles reading the employee file, distributing salaries into groups, registering new employees, and querying salaries.  
- Methods:
  - `computeGroupIndex(double salary, double minSalary, double span, int groupCount)` – computes which salary group an employee belongs to.
  - `promptForNewEmployees(...)` – interactively registers new employees.
  - `promptForSalaryQuery(...)` – interactively queries employee salaries by ID.

### StaffMember
- Represents an individual employee.
- Fields:
  - `id` – unique employee ID.
  - `salary` – salary value.
- Methods:
  - `getId()` – returns the employee ID.
  - `getSalary()` – returns the salary.
  - `toString()` – prints a readable representation of the staff member.

## Sample Output
Input the number of salary groups: 5
Employees have been grouped by salary ranges.
Group 0 (40000.0 to 52000.0): 25 employee(s)
Group 1 (52000.0 to 64000.0): 30 employee(s)
Group 2 (64000.0 to 76000.0): 28 employee(s)
Group 3 (76000.0 to 88000.0): 32 employee(s)
Group 4 (88000.0 to 100000.0): 35 employee(s)

Would you like to register a new employee? (y/n): y
Enter employee ID: 501
Enter employee salary: 75000
Employee successfully added to group 2.

Do you want to lookup an employee's salary? (y/n): y
Enter employee ID: 501
Salary for employee ID 501: 75000.0
