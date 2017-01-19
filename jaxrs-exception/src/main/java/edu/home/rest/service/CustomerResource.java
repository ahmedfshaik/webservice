package edu.home.rest.service;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import edu.home.rest.domain.Customer;
import edu.home.rest.exception.NotFoundException;

@Path("/customers")
public class CustomerResource {

    private static final String PASSWORD = "";
    private static final String USER = "SA";
    private static final String JDBC_URL = "jdbc:hsqldb:hsql://localhost/testdb";
    private static final String JDBC_DRIVER = "org.hsqldb.jdbc.JDBCDriver";

    public CustomerResource() {
    }

    @POST
    @Consumes("application/xml")
    public Response createCustomer(Customer c) {
        // Declare resources
        Connection con = null;
        PreparedStatement pstmt = null;
        Statement stmt = null;
        ResultSet rs = null;
        Customer cust = null;
        Response resp = null;

        try {
            // Load & Register Jdbc Driver
            Class.forName(JDBC_DRIVER);

            // Establish connection
            con = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT MAX(ID) FROM CUSTOMER");
            int id;
            if (rs.next()) {
                id = rs.getInt(1);
                id++; // Primary key increment
            }
            else {
                id = 1;
            }

            // Prepare query
            String query = "INSERT INTO CUSTOMER(ID, FIRST_NAME, LAST_NAME) VALUES(?,?,?)";

            // Create JDBC Statement
            pstmt = con.prepareStatement(query);

            // set data
            pstmt.setInt(1, id);
            pstmt.setString(2, c.getFirstName());
            pstmt.setString(3, c.getLastName());

            int count = pstmt.executeUpdate();

            if (count == 1) { // process result
                resp = Response.created(URI.create("/customers/" + id)).build();
            }
            else {
                // exception code
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    @GET
    @Path("{id}")
    @Produces("application/xml")
    public Customer getCustomer(@PathParam("id") int id) {
        // Declare resources
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        Customer cust = null;

        try {
            // Load & Register Jdbc Driver
            Class.forName(JDBC_DRIVER);

            // Establish connection
            con = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

            // Prepare query
            String query = "SELECT * FROM CUSTOMER WHERE ID=" + id;

            // Create JDBC Statement
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            // System.out.println("******"+rs.next());
            if (!rs.next()) { // empty rs
                // throw new WebApplicationException(Response.Status.NOT_FOUND);
                throw new NotFoundException("Could not found customer:" + id);
            }
            else {
                cust = new Customer();
                cust.setId(rs.getInt(1));
                cust.setFirstName(rs.getString(2));
                cust.setLastName(rs.getString(3));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException c) {
            c.printStackTrace();
        }
        return cust;
    }
}
