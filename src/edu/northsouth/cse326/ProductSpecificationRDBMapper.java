package edu.northsouth.cse326;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductSpecificationRDBMapper implements IMapper {

	@Override
	public Object get(int OID) {
		ProductSpecification ps = null;
		Connection con = new Connect("pos").getCon();
		try {
			Statement st = con.createStatement();
			String sql = "SELECT * FROM ps WHERE id = " + OID;
		    ResultSet rs = st.executeQuery(sql);
		    
		    while( rs.next() ) {
		    	ps = new ProductSpecification(rs.getInt("id"), rs.getString("name"), rs.getString("des"), rs.getInt("price"));
		    }
		    
		} catch (SQLException s) {
			System.out.println("SQL statement is not executed!");
		}
		
		return ps;
	}

	@Override
	public void put(int OID, Object object) {
		ProductSpecification ps = (ProductSpecification) object;
		Connection con = new Connect("pos").getCon();
		try {
			Statement st = con.createStatement();
			String sql = "INSERT INTO  ps VALUES (" + ps.getId() + ", '" + ps.getName() + "', '" + ps.getDescription() + "', " + ps.getPrice() + " )";
			System.out.println(sql);
			int val = st
					.executeUpdate(sql);
			System.out.println("1 row affected");
		} catch (SQLException s) {
			System.out.println("SQL statement is not executed!");
		}
	}
	
	public void update(int OID, Object object) {
		ProductSpecification ps = (ProductSpecification) object;
		Connection con = new Connect("pos").getCon();
		try {
			Statement st = con.createStatement();
			String sql = "UPDATE ps " +
	                   "SET name = '" + ps.getName() + "', des = '" + ps.getDescription() + "', price = " + ps.getPrice() + " WHERE id = " + ps.getId();
			st.executeUpdate(sql);
			System.out.println("1 row affected");
		} catch (SQLException s) {
			System.out.println("SQL statement is not executed!");
		}
	}
	
	public void delete(int OID) {
		Connection con = new Connect("pos").getCon();
		try {
			Statement st = con.createStatement();
			String sql = "DELETE FROM ps WHERE id = " + OID;
			System.out.println(sql);
			st.executeUpdate(sql);
			System.out.println("1 row affected");
		} catch (SQLException s) {
			System.out.println("SQL statement is not executed!");
		}
	}

}
