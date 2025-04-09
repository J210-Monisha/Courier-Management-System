package dao;
import entity.Applicant;
import entity.Company;
import entity.JobApplication;
import entity.JobListing;
import exception.ApplicationDeadlinePassedException;
import exception.InvalidEmailFormatException;
import exception.NegativeSalaryException;
import util.DBConnutil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class DatabaseManagerImpl implements DatabaseManager {

    @Override
    public void insertCompany(Company company) {
        String query = "INSERT INTO Companies (CompanyName, Location) VALUES (?, ?)";
        try (Connection conn = DBConnutil.getConnection("db.properties");
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, company.getCompanyName());
            stmt.setString(2, company.getLocation());
            stmt.executeUpdate();

            System.out.println("✅ Company inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertJobListing(JobListing job) {
        String query = "INSERT INTO JobListings (CompanyID, JobTitle, JobDescription, JobLocation, Salary, JobType, PostedDate) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnutil.getConnection("db.properties");
             PreparedStatement stmt = conn.prepareStatement(query)) {

            if (job.getSalary() < 0) {
                throw new NegativeSalaryException("Salary cannot be negative: " + job.getSalary());
            }

            stmt.setInt(1, job.getCompanyId());
            stmt.setString(2, job.getJobTitle());
            stmt.setString(3, job.getJobDescription());
            stmt.setString(4, job.getJobLocation());
            stmt.setDouble(5, job.getSalary());
            stmt.setString(6, job.getJobType());
            stmt.setTimestamp(7, Timestamp.valueOf(job.getPostedDate()));

            stmt.executeUpdate();
            System.out.println("Job listing inserted successfully.");
        } catch (NegativeSalaryException e) {
            System.err.println("Salary error: " + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertApplicant(Applicant applicant) {
        String query = "INSERT INTO applicant (FirstName, LastName, Email, Phone, Resume) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnutil.getConnection("db.properties");
             PreparedStatement stmt = conn.prepareStatement(query)) {

            if (!isValidEmail(applicant.getEmail())) {
                throw new InvalidEmailFormatException("Invalid email format: " + applicant.getEmail());
            }

            stmt.setString(1, applicant.getFirstName());
            stmt.setString(2, applicant.getLastName());
            stmt.setString(3, applicant.getEmail());
            stmt.setString(4, applicant.getPhone());
            stmt.setString(5, applicant.getResume());

            stmt.executeUpdate();
            System.out.println("Applicant inserted successfully.");

        } catch (InvalidEmailFormatException e) {
            System.err.println("Email validation error: " + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(emailRegex, email);
    }

    public void insertJobApplication(JobApplication application) {
        String query = "INSERT INTO Applications (JobID, ApplicantID, ApplicationDate, CoverLetter) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnutil.getConnection("db.properties");
             PreparedStatement stmt = conn.prepareStatement(query)) {

            LocalDateTime deadline = getJobDeadline(application.getJobId());
            if (deadline != null && application.getApplicationDate().isAfter(deadline)) {
                throw new ApplicationDeadlinePassedException("Application deadline has passed for job ID: " + application.getJobId());
            }

            stmt.setInt(1, application.getJobId());
            stmt.setInt(2, application.getApplicantId());
            stmt.setTimestamp(3, Timestamp.valueOf(application.getApplicationDate()));
            stmt.setString(4, application.getCoverLetter());

            stmt.executeUpdate();
            System.out.println("Application submitted successfully.");
        } catch (ApplicationDeadlinePassedException e) {
            System.err.println("Deadline error: " + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private LocalDateTime getJobDeadline(int jobId) {
        return LocalDateTime.now().plusDays(1);
    }

    @Override
    public List<JobListing> getJobListings() {
        List<JobListing> jobs = new ArrayList<>();
        String sql = "SELECT j.JobID, j.JobTitle, j.Salary, j.JobDescription, j.JobLocation, j.JobType, j.PostedDate, j.CompanyID, c.CompanyName " +
                     "FROM JobListings j " +
                     "JOIN Companies c ON j.CompanyID = c.CompanyID";

        try (Connection conn = DBConnutil.getConnection("db.properties");
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\nJob Listings:");

            while (rs.next()) {
                JobListing job = new JobListing();
                job.setJobId(rs.getInt("JobID"));
                job.setJobTitle(rs.getString("JobTitle"));
                job.setSalary(rs.getDouble("Salary"));
                job.setJobDescription(rs.getString("JobDescription"));
                job.setJobLocation(rs.getString("JobLocation"));
                job.setJobType(rs.getString("JobType"));
                job.setPostedDate(rs.getTimestamp("PostedDate").toLocalDateTime());
                job.setCompanyId(rs.getInt("CompanyID"));

                System.out.printf("Job: %s |Company: %s |Salary: %.2f%n",
                        job.getJobTitle(), rs.getString("CompanyName"), job.getSalary());

                jobs.add(job);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobs;
    }

    @Override
    public List<JobApplication> getApplicationsForJob(int jobId) {
        List<JobApplication> applications = new ArrayList<>();
        String query = "SELECT * FROM Applications WHERE JobID = ?";
        try (Connection conn = DBConnutil.getConnection("db.properties");
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, jobId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                JobApplication application = new JobApplication();
                application.setApplicationId(rs.getInt("ApplicationID"));
                application.setJobId(rs.getInt("JobID"));
                application.setApplicantId(rs.getInt("ApplicantID"));
                application.setApplicationDate(rs.getTimestamp("ApplicationDate").toLocalDateTime());
                application.setCoverLetter(rs.getString("CoverLetter"));
                applications.add(application);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applications;
    }

    @Override
    public List<Company> getCompanies() {
        List<Company> companies = new ArrayList<>();
        String query = "SELECT * FROM Companies";
        try (Connection conn = DBConnutil.getConnection("db.properties");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Company company = new Company();
                company.setCompanyId(rs.getInt("CompanyID"));
                company.setCompanyName(rs.getString("CompanyName"));
                company.setLocation(rs.getString("Location"));
                companies.add(company);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companies;
    }

    @Override
    public List<Applicant> getApplicants() {
        List<Applicant> applicants = new ArrayList<>();
        String query = "SELECT * FROM applicant";
        try (Connection conn = DBConnutil.getConnection("db.properties");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Applicant applicant = new Applicant();
                applicant.setApplicantId(rs.getInt("ApplicantID"));
                applicant.setFirstName(rs.getString("FirstName"));
                applicant.setLastName(rs.getString("LastName"));
                applicant.setEmail(rs.getString("Email"));
                applicant.setPhone(rs.getString("Phone"));
                applicant.setResume(rs.getString("Resume"));
                applicants.add(applicant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applicants;
    }
    @Override
    public void getJobsBySalaryRange(double min, double max) {
        String query = "SELECT j.JobTitle, j.Salary, c.CompanyName " +
                       "FROM JobListings j " +
                       "JOIN Companies c ON j.CompanyID = c.CompanyID " +
                       "WHERE j.Salary BETWEEN ? AND ?";

        try (Connection conn = DBConnutil.getConnection("db.properties");
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDouble(1, min);
            stmt.setDouble(2, max);

            ResultSet rs = stmt.executeQuery();

            boolean found = false;
            System.out.println("\nJobs within Salary Range:");
            while (rs.next()) {
                String title = rs.getString("JobTitle");
                double salary = rs.getDouble("Salary");
                String company = rs.getString("CompanyName");

                System.out.printf("Job Title: %-20s | Company: %-20s | Salary: ₹%.2f\n", title, company, salary);
                found = true;
            }

            if (!found) {
                System.out.println("No jobs found in this salary range.");
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }


    @Override
    public void initializeDatabase() {
    }

    @Override
    public void insertJob(int jobPostId, String title, String desc, double salary, int companyId) {}

    @Override
    public void submitApplication(int appId, int jobId, int applicantId, Date date) {}

    @Override
    public void getJobListings1() throws SQLException {}
}
