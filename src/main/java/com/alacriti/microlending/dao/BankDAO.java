package com.alacriti.microlending.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.annotation.PostConstruct;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.alacriti.microlending.vo.BankAddingVO;
import com.alacriti.microlending.vo.BankGettingVO;
import com.alacriti.microlending.vo.UserLogInOutputVO;

@Repository
public class BankDAO {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
    UserDAO userDAO = new UserDAO();
    
	public ResponseEntity<?> bankAddDAO(int userId,BankAddingVO bankDetails){
		String sql = "insert into AL372_bank_details(account_number,bank_name,branch,ifsccode) values(?,?,?,?)";
		KeyHolder holder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {           

		                @Override
		                public PreparedStatement createPreparedStatement(Connection connection)
		                        throws SQLException {
		                    PreparedStatement ps = connection.prepareStatement(sql.toString(),
		                    		new String[] { "bank_id"}); 
		                    ps.setString(1, bankDetails.getAccountNumber());
		                    ps.setString(2, bankDetails.getBankName());
		                    ps.setString(3, bankDetails.getBranch());
		                    ps.setString(4, bankDetails.getIfscCode());
		                    return ps;
		                }
		            }, holder);
		Number bankId = holder.getKey();
		if(bankId == null){
			return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
		}
		else{
			int bankIdValue = bankId.intValue();
			String sql2 = "update AL372_users set bank_id = ? where user_id = ?;";
			int effectedRows = jdbcTemplate.update(sql2,bankIdValue,userId);
			if(effectedRows == 1){
				return new ResponseEntity<>(HttpStatus.ACCEPTED);
			}
			else{
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			}
		}
	}

	public BankGettingVO bankGetDetails(int bankId) {
		try{
			BankGettingVO bankDetailsOutput;
			String sql = "Select * from AL372_bank_details where bank_id =?;";		
			bankDetailsOutput =  jdbcTemplate.queryForObject(sql, new Object[]{bankId}, 
						(rs, rowNum) -> 
							new BankGettingVO(
								rs.getString("account_number"),
								rs.getString("bank_name"),
								rs.getString("branch"),
								rs.getString("ifsccode"),
								rs.getInt("bank_id")
							)
					);
			return bankDetailsOutput;
		}catch(EmptyResultDataAccessException e){
			return null;
		}
	}
}


