package com.alacriti.microlending.dao;

import java.util.List;


import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.alacriti.microlending.vo.UserLogInOutputVO;
import com.alacriti.microlending.vo.UserLogInVO;
import com.alacriti.microlending.vo.UserSignUpVO;


@Repository
public class UserDAO {
	@Autowired
	JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
	public int userSearchDAO(String username){
		String sql = "select count(*) from AL372_users where username='"+username+"';";
		int preUserPresence = 0;
		preUserPresence = jdbcTemplate.queryForObject(sql,Integer.class);
		return preUserPresence;
	}
	
	public int userSignUPDAO(UserSignUpVO user){
		String sql = "insert into AL372_users(username, password, name, age, contact_number) values(?,?,?,?,?);";
		int effectedRows = jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getName(), user.getAge(), user.getContactNumber());
		return effectedRows;
	}
	
	
	public UserLogInOutputVO userLogInDAO(UserLogInVO user){
		UserLogInOutputVO userOutput;
		try{
			String sql = "Select * from AL372_users where username=? and password=?;";		
			userOutput = jdbcTemplate.queryForObject(sql, new Object[]{user.getUsername(),user.getPassword()},
	                (rs, rowNum) ->
	                       new UserLogInOutputVO(
	                                rs.getInt("user_id"),
	                                rs.getString("username"),
	                                rs.getString("password"),
	                                rs.getString("name"),
	                                rs.getString("contact_number"),
	                                rs.getInt("bank_id"),
	                                rs.getInt("age")
	                        )
	        );
			return userOutput;
		}catch(EmptyResultDataAccessException e){
			return null;
		}
	}
	
	public int userUpdateBankId(int userId,int bankId){
		String sql = "update AL372_users set bank_id = ? where user_id = ?;";
		int effectedRows = jdbcTemplate.update(sql,bankId,userId);
		return effectedRows;
	}
	
	public List<UserLogInOutputVO> userGetAllUsersDAO(int userId){
		List<UserLogInOutputVO> users;
		String sql = "select * from AL372_users where user_id <> ?";
		users = jdbcTemplate.query(sql, new Object[]{userId},
					(rs, rowNum) -> new UserLogInOutputVO(
							rs.getInt("user_id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("name"),
                            rs.getString("contact_number"),
                            rs.getInt("bank_id"),
                            rs.getInt("age")
						)
				);
		return users;
	}
	
	public UserLogInOutputVO getUserById(int userId){
		UserLogInOutputVO user;
		try{
			String sql = "select * from AL372_users where user_id =?";
			user = jdbcTemplate.queryForObject(sql, new Object[]{userId},
						(rs, rowNum)->
							new UserLogInOutputVO(
									rs.getInt("user_id"),
		                            rs.getString("username"),
		                            rs.getString("password"),
		                            rs.getString("name"),
		                            rs.getString("contact_number"),
		                            rs.getInt("bank_id"),
		                            rs.getInt("age")
							)
					);
			return user;
		}catch(EmptyResultDataAccessException e){
			return null;
		}
	}
}
