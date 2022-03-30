package com.example.streampracticetask.practice;

import com.example.streampracticetask.model.*;
import com.example.streampracticetask.service.*;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class Practice {

    public static CountryService countryService;
    public static DepartmentService departmentService;
    public static EmployeeService employeeService;
    public static JobHistoryService jobHistoryService;
    public static JobService jobService;
    public static LocationService locationService;
    public static RegionService regionService;

    public Practice(CountryService countryService, DepartmentService departmentService,
                    EmployeeService employeeService, JobHistoryService jobHistoryService,
                    JobService jobService, LocationService locationService,
                    RegionService regionService) {

        Practice.countryService = countryService;
        Practice.departmentService = departmentService;
        Practice.employeeService = employeeService;
        Practice.jobHistoryService = jobHistoryService;
        Practice.jobService = jobService;
        Practice.locationService = locationService;
        Practice.regionService = regionService;

    }

    // You can use the services above for all the CRUD (create, read, update, delete) operations.
    // Above services have all the required methods.
    // Also, you can check all the methods in the ServiceImpl classes inside the service.impl package, they all have explanations.

    // Display all the employees
    public static List<Employee> getAllEmployees() {
        //TODO Implement the method
        return employeeService.readAll();
    }

    // Display all the countries
    public static List<Country> getAllCountries() {
        //TODO Implement the method
        return countryService.readAll();
    }

    // Display all the departments
    public static List<Department> getAllDepartments() {
        //TODO Implement the method
        return departmentService.readAll();
    }

    // Display all the jobs
    public static List<Job> getAllJobs() {
        //TODO Implement the method
        return jobService.readAll();
    }

    // Display all the locations
    public static List<Location> getAllLocations() {
        //TODO Implement the method
        return locationService.readAll();
    }

    // Display all the regions
    public static List<Region> getAllRegions() {
        //TODO Implement the method
        return regionService.readAll();
    }

    // Display all the job histories
    public static List<JobHistory> getAllJobHistories() {
        //TODO Implement the method
        return jobHistoryService.readAll();
    }

    // Display all the employees' first names
    public static List<String> getAllEmployeesFirstName() {
        //TODO Implement the method
        return employeeService.readAll().stream().map(Employee::getFirstName).collect(Collectors.toList());
    }

    // Display all the countries' names
    public static List<String> getAllCountryNames() {
        //TODO Implement the method
        return countryService.readAll().stream().map(Country::getCountryName).collect(Collectors.toList()) ;
    }

    // Display all the departments' managers' first names
    public static List<String> getAllDepartmentManagerFirstNames() {
        //TODO Implement the method
        return departmentService.readAll().stream()
                .map(Department::getManager)
                .map(Employee::getFirstName)
                .collect(Collectors.toList());
    }

    // Display all the departments where manager name of the department is 'Steven'
    public static List<Department> getAllDepartmentsWhichManagerFirstNameIsSteven() {
        //TODO Implement the method
        return departmentService.readAll().stream()
                .filter(department -> department.getManager().getFirstName().equals("Steven"))
                .collect(Collectors.toList());
    }

    // Display all the departments where postal code of the location of the department is '98199'
    public static List<Department> getAllDepartmentsWhereLocationPostalCodeIs98199() {
        //TODO Implement the method
        return departmentService.readAll().stream()
                .filter(department -> department.getLocation().getPostalCode().equals("98199"))
                .collect(Collectors.toList());
    }

    // Display the region of the IT department
    public static Region getRegionOfITDepartment() throws Exception {
        //TODO Implement the method
        return departmentService.readAll().stream()
                .filter(department -> department.getDepartmentName().equals("IT"))
                .findFirst().orElseThrow(() -> new Exception("No Department Found")).getLocation().getCountry().getRegion();

    }
        // Display the region of the IT department

    public static Region getRegionOfITDepartment1() throws Exception {

        return departmentService.readAll().stream()
                .filter(department -> department.getDepartmentName().equals("IT"))
                .findAny().get().getLocation().getCountry().getRegion();

    }

    // Display the region of the IT department

    public static Region getRegionOfITDepartment2() throws Exception {

        return departmentService.readAll().stream()
                .filter(department -> department.getDepartmentName().equals("IT"))
                .findAny().get().getLocation().getCountry().getRegion();

    }

    // Display all the departments where the region of department is 'Europe'
    public static List<Department> getAllDepartmentsWhereRegionOfCountryIsEurope() {
        //TODO Implement the method
        return departmentService.readAll().stream()
                .filter(department -> department.getLocation().getCountry().getRegion().getRegionName().equals("Europe"))
                .collect(Collectors.toList());
    }

    // Display if there is any employee with salary less than 1000. If there is none, the method should return true
    public static boolean checkIfThereIsNoSalaryLessThan1000() {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .noneMatch(employee -> employee.getSalary() < 1000);
    }


    // Display if there is any employee with salary less than 1000. If there is none, the method should return true
    public static boolean checkIfThereIsNoSalaryLessThan10001() {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .allMatch(employee -> employee.getSalary() >= 1000);
    }

    // Check if the salaries of all the employees in IT department are greater than 2000 (departmentName: IT)
    public static boolean checkIfThereIsAnySalaryGreaterThan2000InITDepartment() {
        //TODO Implement the method
        return getAllEmployees().stream()
                .filter(employee -> employee.getDepartment().getDepartmentName().equals("IT"))
                .anyMatch(employee -> employee.getSalary() > 2000);
    }

    // Display all the employees whose salary is less than 5000
    public static List<Employee> getAllEmployeesWithLessSalaryThan5000() {
        //TODO Implement the method
        return getAllEmployees().stream()
                .filter(employee -> employee.getSalary() < 5000)
                .collect(Collectors.toList());
    }

    // Display all the employees whose salary is between 6000 and 7000
    public static List<Employee> getAllEmployeesSalaryBetween() {
        //TODO Implement the method
        return getAllEmployees().stream()
                .filter(employee -> employee.getSalary() < 7000 && employee.getSalary() > 6000)
                .collect(Collectors.toList());
    }

    // Display the salary of the employee Grant Douglas (lastName: Grant, firstName: Douglas)
    public static Long getGrantDouglasSalary() throws Exception {
        //TODO Implement the method
        return getAllEmployees().stream()
                .filter(employee -> employee.getLastName().equals("Grant"))
                .filter(employee -> employee.getFirstName().equals("Douglas"))
                .findAny()
                .get().getSalary();
    }

    // Display the maximum salary an employee gets
    public static Long getMaxSalary() throws Exception {
        return employeeService.readAll().stream()
                .map(Employee::getSalary)
                .reduce((a, b) -> a > b ? a : b)
                .get();

    }
    // Display the maximum salary an employee gets
    public static Long getMaxSalary1() throws Exception {
        return employeeService.readAll().stream()
                .max(Comparator.comparing(Employee::getSalary))
                .get().getSalary();

    }
    // Display the maximum salary an employee gets
    public static Long getMaxSalary3() throws Exception {
        return employeeService.readAll().stream()
                .map(Employee::getSalary)
                .reduce(Long::max)
                .get();

    }

    // Display the maximum salary an employee gets
    public static Long getMaxSalary4() throws Exception {
        return employeeService.readAll().stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .findFirst().orElseThrow(()->new Exception("No Employee found")).getSalary();
    }

    // Display the employee(s) who gets the maximum salary
    public static List<Employee> getMaxSalaryEmployee() {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .max(Comparator.comparing(Employee::getSalary))
                .stream()
                .collect(Collectors.toList());
    }

    // Display the employee(s) who gets the maximum salary
    public static List<Employee> getMaxSalaryEmployee1() {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .filter(employee-> {
                    try {
                        return employee.getSalary().equals(getMaxSalary());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    // Display the max salary employee's job
    public static Job getMaxSalaryEmployeeJob() throws Exception {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(()-> new Exception("Taraklar pahali degil")).getJob();
    }

    // Display the max salary employee's job
    public static Job getMaxSalaryEmployeeJob1() throws Exception {
        //TODO Implement the method
        return getMaxSalaryEmployee().stream()
                .map((Employee::getJob))
                .findAny().orElseThrow(()-> new Exception("Taraklar pahali degil"));
    }

    // Display the max salary in Americas Region
    public static Long getMaxSalaryInAmericasRegion() throws Exception {
        //TODO Implement the method

        return employeeService.readAll().stream()
                .filter(employee -> employee.getDepartment().getLocation().getCountry().getRegion().getRegionName().equals("Americas"))
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(()-> new Exception("Hepsi Az Para Alir")).getSalary();
    }


    // Display the second maximum salary an employee gets
    public static Long getSecondMaxSalary() throws Exception {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .map(Employee::getSalary).collect(Collectors.toList()).get(1);
    }

    // Display the second maximum salary an employee gets
    public static Long getSecondMaxSalary1() throws Exception {
        return employeeService.readAll().stream()
                .map(Employee::getSalary)
                .reduce((a,b)-> {
                    try {
                        return a > b &&  a < getMaxSalary() ? a : b;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return -1L;
                }).orElseThrow(()->new Exception("Employee yok ki parasi olsun"));
    }


    // Display the employee(s) who gets the second maximum salary
    public static List<Employee> getSecondMaxSalaryEmployee() {
        //TODO Implement the method

        return employeeService.readAll().stream()
                .filter(employee -> {
                    try {
                        return employee.getSalary().equals(getSecondMaxSalary());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }return false;
                }).collect(Collectors.toList());


    }

    // Display the minimum salary an employee gets
    public static Long getMinSalary() throws Exception {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .min(Comparator.comparing(Employee::getSalary))
                .get().getSalary();
    }

    // Display the employee(s) who gets the minimum salary
    public static List<Employee> getMinSalaryEmployee() {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .filter(employee -> {
                    try {
                        return employee.getSalary().equals(getMinSalary());
                    } catch (Exception e) {
                        e.printStackTrace();
                    } return false;
                }).collect(Collectors.toList());
    }

    // Display the second minimum salary an employee gets
    public static Long getSecondMinSalary() throws Exception {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .map(Employee::getSalary)
                .reduce((a,b)-> {
                    try {
                        return a < b && a > getMinSalary() ? a : b;
                    } catch (Exception e) {
                        e.printStackTrace();
                    } return Long.MAX_VALUE;
                }).orElseThrow(()->new Exception("Not founded"));
    }

    // Display the employee(s) who gets the second minimum salary
    public static List<Employee> getSecondMinSalaryEmployee() {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .filter(employee -> {
                    try {
                        return employee.getSalary().equals(getSecondMinSalary());
                    } catch (Exception e) {
                        e.printStackTrace();
                    } return false;
                }).collect(Collectors.toList());
    }

    // Display the average salary of the employees
    public static Double getAverageSalary() {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
    }


    public static Double getAverageSalary1() {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .mapToDouble(Employee::getSalary)
                .average().orElse(0d);
    }


    // Display all the employees who are making more than average salary
    public static List<Employee> getAllEmployeesAboveAverage() {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .filter(employee -> employee.getSalary() > getAverageSalary())
                .collect(Collectors.toList());
    }

    // Display all the employees who are making less than average salary
    public static List<Employee> getAllEmployeesBelowAverage() {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .filter(employee -> employee.getSalary() < getAverageSalary())
                .collect(Collectors.toList());
    }

    // Display all the employees separated based on their department id number
    public static Map<Long, List<Employee>> getAllEmployeesForEachDepartment() {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .collect(Collectors.groupingBy(employee -> employee.getDepartment().getId()));
    }

    // Display the total number of the departments
    public static Long getTotalDepartmentsNumber() {
        //TODO Implement the method
        return departmentService.readAll().stream().count();
    }


    // Display the total number of the departments
    public static Long getTotalDepartmentsNumber1() {
        //TODO Implement the method
        return (long)getAllDepartments().size();
    }
    // Display the employee whose first name is 'Alyssa' and manager's first name is 'Eleni' and department name is 'Sales'
    public static Employee getEmployeeWhoseFirstNameIsAlyssaAndManagersFirstNameIsEleniAndDepartmentNameIsSales() throws Exception {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .filter(employee->employee.getFirstName().equals("Alyssa"))
                .filter(employee -> employee.getManager().getFirstName().equals("Eleni"))
                .filter(employee -> employee.getDepartment().getDepartmentName().equals("Sales"))
                .findAny().get();
    }

    // Display all the job histories in ascending order by start date
    public static List<JobHistory> getAllJobHistoriesInAscendingOrder() {
        //TODO Implement the method
        return jobHistoryService.readAll().stream()
                .sorted(Comparator.comparing(JobHistory :: getStartDate))
                .collect(Collectors.toList());
    }

    // Display all the job histories in descending order by start date
    public static List<JobHistory> getAllJobHistoriesInDescendingOrder() {
        //TODO Implement the method
        return jobHistoryService.readAll().stream()
                .sorted(Comparator.comparing(JobHistory::getStartDate).reversed())
                .collect(Collectors.toList());
    }

    // Display all the job histories where the start date is after 01.01.2005
    public static List<JobHistory> getAllJobHistoriesStartDateAfterFirstDayOfJanuary2005() {
        //TODO Implement the method
        return jobHistoryService.readAll().stream()
                .filter(history-> history.getStartDate().isAfter(LocalDate.of(2005,01,01)))
                .collect(Collectors.toList());
    }

    // Display all the job histories where the end date is 31.12.2007 and the job title of job is 'Programmer'
    public static List<JobHistory> getAllJobHistoriesEndDateIsLastDayOfDecember2007AndJobTitleIsProgrammer() {
        //TODO Implement the method
        return jobHistoryService.readAll().stream()
                .filter(history->history.getEndDate().equals(LocalDate.of(2007,12,31)))
                .filter(title->title.getJob().getJobTitle().equals("Programmer"))
                .collect(Collectors.toList());
    }

    // Display the employee whose job history start date is 01.01.2007 and job history end date is 31.12.2007 and department's name is 'Shipping'
    public static Employee getEmployeeOfJobHistoryWhoseStartDateIsFirstDayOfJanuary2007AndEndDateIsLastDayOfDecember2007AndDepartmentNameIsShipping() throws Exception {
        //TODO Implement the method
        return jobHistoryService.readAll().stream()
                .filter(history-> history.getStartDate().equals(LocalDate.of(2007,01,01)))
                .filter(history-> history.getEndDate().equals(LocalDate.of(2007,12,31)))
                .filter(history->history.getDepartment().getDepartmentName().equals("Shipping"))
                .map(JobHistory::getEmployee)
                .findAny().get();

    }

    // Display the employee whose job history start date is 01.01.2007 and job history end date is 31.12.2007 and department's name is 'Shipping'
    public static Employee getEmployeeOfJobHistoryWhoseStartDateIsFirstDayOfJanuary2007AndEndDateIsLastDayOfDecember2007AndDepartmentNameIsShipping1() throws Exception {
        //TODO Implement the method
        return jobHistoryService.readAll().stream()
                .filter(history-> history.getStartDate().equals(LocalDate.of(2007,01,01)))
                .filter(history-> history.getEndDate().equals(LocalDate.of(2007,12,31)))
                .filter(history->history.getDepartment().getDepartmentName().equals("Shipping"))
                .findAny().get().getEmployee();

    }

    // Display all the employees whose first name starts with 'A'
    public static List<Employee> getAllEmployeesFirstNameStartsWithA() {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .filter(employee -> employee.getFirstName().startsWith("A"))
                .collect(Collectors.toList());
    }

    // Display all the employees whose job id contains 'IT'
    public static List<Employee> getAllEmployeesJobIdContainsIT() {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .filter(employee -> employee.getJob().getId().contains("IT"))
                .collect(Collectors.toList());
    }

    // Display the number of employees whose job title is programmer and department name is 'IT'
    public static Long getNumberOfEmployeesWhoseJobTitleIsProgrammerAndDepartmentNameIsIT() {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .filter(employee -> employee.getJob().getJobTitle().equals("Programmer"))
                .filter(employee -> employee.getDepartment().getDepartmentName().equals("IT"))
                .count();
    }

    // Display all the employees whose department id is 50, 80, or 100
    public static List<Employee> getAllEmployeesDepartmentIdIs50or80or100() {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .filter(employee -> employee.getDepartment().getId().equals(50L)
                                    || employee.getDepartment().getId().equals(80L)
                                    || employee.getDepartment().getId().equals(100L))
                .collect(Collectors.toList());
    }

    // Display the initials of all the employees
    // Note: You can assume that there is no middle name
    public static List<String> getAllEmployeesInitials() {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .map(employee -> {
                    String firstInitial = employee.getFirstName().substring(0,1);
                    String secondInitial = employee.getLastName().substring(0,1);
                    return firstInitial + secondInitial;
                }).collect(Collectors.toList());
    }



    // Display the full names of all the employees
    public static List<String> getAllEmployeesFullNames() {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .map(employee -> {
                    String firstName = employee.getFirstName();
                    String lastName = employee.getLastName();
                    String fullName = firstName + " " + lastName;
                    return fullName;
                }).collect(Collectors.toList());
    }

    // Display the length of the longest full name(s)
    public static Integer getLongestNameLength() throws Exception {
        //TODO Implement the method
        return getAllEmployeesFullNames().stream()
                .reduce((a,b)-> a.length() > b.length() ? a: b)
                .orElseThrow(()->new Exception("u go wrong")).length();
    }

    // Display the employee(s) with the longest full name(s)
    public static List<Employee> getLongestNamedEmployee() {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .filter(employee-> {
                    try {
                        return employee.getFirstName().length() + employee.getLastName().length() == getLongestNameLength()-1;
                    } catch (Exception e) {
                        e.printStackTrace();
                    } return false;
                }).collect(Collectors.toList());
    }

    // Display all the employees whose department id is 90, 60, 100, 120, or 130
    public static List<Employee> getAllEmployeesDepartmentIdIs90or60or100or120or130() {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .filter(employee -> employee.getDepartment().getId().equals(60L) || employee.getDepartment().getId().equals(90L)
                        || employee.getDepartment().getId().equals(100L) || employee.getDepartment().getId().equals(120L) || employee.getDepartment().getId().equals(130L))
                .collect(Collectors.toList());
    }

    // Display all the employees whose department id is NOT 90, 60, 100, 120, or 130
    public static List<Employee> getAllEmployeesDepartmentIdIsNot90or60or100or120or130() {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .filter(employee -> !getAllEmployeesDepartmentIdIs90or60or100or120or130().contains(employee))
                .collect(Collectors.toList());
    }

}
