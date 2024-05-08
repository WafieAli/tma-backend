package com.tma.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tma.backend.dto.TaskDTO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

@RestController
@RequestMapping(value = "/api/v1/tma")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TaskController {
	
	@Value("${spring.datasource.url}")
	private String url;
	
	@Value("${spring.datasource.username}")
	private String username;
	
	@Value("${spring.datasource.password}")
	private String password;
	
	@GetMapping(value="/")
	public String getPage() {
		return "Welcome to Task Management Application";
	}
	
	@GetMapping(value="/tasks")
	public Object getTasks() throws IOException, InterruptedException {
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(url, username, password);
			String query = "SELECT * FROM tb_tasks";
			st = conn.createStatement();
			rs = st.executeQuery(query);
			
			while(rs.next()) {
				JSONObject record = new JSONObject();
				record.put("TaskID", rs.getString("task_id"));
				record.put("Title", rs.getString("title"));
				record.put("Description", rs.getString("description"));
				record.put("Status", rs.getString("status"));
				record.put("Completed", rs.getString("completed"));
				record.put("CreatedDate", rs.getString("created_dtm"));
				record.put("UpdatedDate", rs.getString("updated_dtm"));
				array.put(record);
			}
			obj.put("Data", array);
			obj.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
		}
		
		return obj.toMap();
	}
	
	//post TMA new task
	@PostMapping(value="/tasks/new")
	public Object postTask(@Valid @RequestBody TaskDTO task) throws IOException, InterruptedException {
		
		JSONArray array = new JSONArray();
    	JSONObject obj = new JSONObject(task);
    	
    	int rtCode;

    	Connection conn = null;    	
		Statement st = null;
		ResultSet rs = null;
		
    	Date date = new Date();
    	Timestamp timestamp = new Timestamp(date.getTime());

		String query = "INSERT INTO tb_tasks (title, description, status, created_dtm) VALUES (?,?,?,?)";
		PreparedStatement pstmt;
		try {
			conn = DriverManager.getConnection(url, username, password); 
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, task.getTitle());
			pstmt.setString(2, task.getDescription());
			pstmt.setString(3, task.getStatus());
			pstmt.setTimestamp(4, timestamp);
			pstmt.executeUpdate();
			pstmt.close();
			
			rtCode = 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			rtCode = 1;
		}
		
//			System.out.println(obj);
		return rtCode;
	}
	
	//edit TMA Task
	@PutMapping(value="/tasks/{id}/edit")
	public Object editTask(@PathVariable(value = "id") Long editId, @Valid @RequestBody TaskDTO task) throws IOException, InterruptedException {
		
		JSONArray array = new JSONArray();
    	JSONObject obj = new JSONObject(task);

    	Connection conn = null;    	
		Statement st = null;
		ResultSet rs = null;
		
    	Date date = new Date();
    	Timestamp updatedDT = new Timestamp(date.getTime());

		String query = "UPDATE tb_tasks SET title=?, description=?, status=?, updated_dtm=? WHERE task_id=?";
		PreparedStatement pstmt;
		try {
			conn = DriverManager.getConnection(url, username, password); 
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, task.getTitle());
			pstmt.setString(2, task.getDescription());
			pstmt.setString(3, task.getStatus());
			pstmt.setTimestamp(4, updatedDT);
			pstmt.setLong(5, editId);
			pstmt.executeUpdate();
			pstmt.close();
			
			obj.put("Data", "Successful");
			obj.toString();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return obj.toMap();
	}
	
	//clear TMA Task
	@DeleteMapping(value="/tasks/{id}/delete")
	public Object clearTask(@PathVariable(value = "id") Long deleteId) throws IOException, InterruptedException {
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();
		
		System.out.println(deleteId);
		
		int rtCode;

    	Connection conn = null;    	
		Statement st = null;
		ResultSet rs = null;

		String query = "DELETE FROM tb_tasks WHERE task_id = ?";
		PreparedStatement pstmt;
		try {
			conn = DriverManager.getConnection(url, username, password);
		    pstmt = conn.prepareStatement(query);
		    pstmt.setLong(1, deleteId);
		    int affectedRows = pstmt.executeUpdate();
		    if (affectedRows == 0) {
		        System.out.println("No rows were deleted.");
		        obj.put("Data", "No rows were deleted.");
		        obj.toString();
		    } else {
		        System.out.println("The row was deleted successfully.");
		        obj.put("Data", "The row was deleted successfully.");
		        obj.toString();
		    }
		    pstmt.close();
		    
		    rtCode = 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		    e.printStackTrace();
		    
		    rtCode = 1;
		}

		 return obj.toMap();
	}
}
