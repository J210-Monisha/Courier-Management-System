package main;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.Scanner;

import dao.DatabaseManager;
import dao.DatabaseManagerImpl;
import entity.Applicant;
import entity.JobApplication;
import entity.JobListing;
import exception.InvalidEmailFormatException;
import exception.NegativeSalaryException;
import util.DBConnutil;

public class CareerHubMain {
    public static void main(String[] args) {
        Connection conn = DBConnutil.getConnection("db.properties");

        if (conn != null) {
            System.out.println("Database connection successful!");
        } else {
            System.out.println("Failed to connect to the database.");
            return;
        }
   
        DatabaseManager dbManager = new DatabaseManagerImpl();
		dbManager.getJobListings();
	
        Scanner scanner = new Scanner(System.in);
        DatabaseManager databaseManager = new DatabaseManagerImpl();
        System.out.println("\nApplicant Profile Creation");

        try {
            System.out.print("Enter First Name: ");
            String firstName = scanner.nextLine();

            System.out.print("Enter Last Name: ");
            String lastName = scanner.nextLine();

            System.out.print("Enter Email: ");
            String email = scanner.nextLine();

            System.out.print("Enter Phone: ");
            String phone = scanner.nextLine();

            System.out.print("Enter Resume Summary or File Path: ");
            String resume = scanner.nextLine();

            Applicant applicant = new Applicant();
            applicant.setFirstName(firstName);
            applicant.setLastName(lastName);
            applicant.setEmail(email);
            applicant.setPhone(phone);
            applicant.setResume(resume);

            dbManager.insertApplicant(applicant);

        } catch (Exception e) {
            System.err.println("Error occurred while creating profile: " + e.getMessage());
        } finally {
            scanner.close();
    }
   
        try {
            System.out.println("\nJob Application Submission");

            System.out.print("Enter Applicant ID: ");
            int applicantId = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter Job ID: ");
            int jobId = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter Cover Letter: ");
            String coverLetter = scanner.nextLine();

            JobApplication application = new JobApplication();
            application.setApplicantId(applicantId);
            application.setJobId(jobId);
            application.setCoverLetter(coverLetter);
            application.setApplicationDate(LocalDateTime.now());

            DatabaseManager.insertJobApplication(application);

        } catch (Exception e) {
            System.err.println("Error while submitting application: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
       
        System.out.println("\nCompany Job Posting");

        try {
            System.out.print("Enter Company ID: ");
            int companyId = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter Job Title: ");
            String title = scanner.nextLine();

            System.out.print("Enter Job Description: ");
            String description = scanner.nextLine();

            System.out.print("Enter Job Location: ");
            String location = scanner.nextLine();

            System.out.print("Enter Salary: ");
            double salary = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter Job Type (e.g., Full-Time, Part-Time): ");
            String jobType = scanner.nextLine();

            JobListing job = new JobListing();
            job.setCompanyId(companyId);
            job.setJobTitle(title);
            job.setJobDescription(description);
            job.setJobLocation(location);
            job.setSalary(salary);
            job.setJobType(jobType);
            job.setPostedDate(LocalDateTime.now());

            databaseManager.insertJobListing(job);

        } catch (NumberFormatException e) {
            System.err.println("Invalid number format.");
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
       
        System.out.println("\nSalary Range Job Search");

        try {
            System.out.print("Enter Minimum Salary: ");
            double minSalary = scanner.nextDouble();

            System.out.print("Enter Maximum Salary: ");
            double maxSalary = scanner.nextDouble();

            databaseManager.getJobsBySalaryRange(minSalary, maxSalary);

        } catch (Exception e) {
            System.err.println("Error fetching job listings: " + e.getMessage());
        }
}
}

