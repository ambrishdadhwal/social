package com.social.repository;

import com.social.domain.Country;
import com.social.domain.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProfileRepo
{

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	DataSource dataProdSource;

	public long totalUsers()
	{
		long count = jdbcTemplate.queryForObject("select count(*) from social_user", Long.class);

		return count;
	}

	public Optional<Profile> getUser(Profile user)
	{
		List<Object> args = new ArrayList<>();

		StringBuilder sql = new StringBuilder("select * from social_user where is_active = true ");

		if (user.getUserName() != null)
		{
			args.add(user.getUserName());
			sql.append(" AND email = ?");
		}

		if (user.getEmail() != null)
		{
			args.add(user.getEmail());
			sql.append(" AND email = ?");
		}

		if (user.getEmail() != null)
		{
			args.add(user.getPassword());
			sql.append(" AND password = ?");
		}

		List<Profile> existingUser = jdbcTemplate.query(sql.toString(), new RowMapper<Profile>()
		{

			@Override
			public Profile mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				return Profile.builder()
					.id(rs.getLong("id"))
					.country(Country.getCountry(rs.getString("country")))
					.email(rs.getString("email"))
					.firstName(rs.getString("first_name"))
					.lastName(rs.getString("last_name"))
					.createDateTime(rs.getDate("create_date_time").toLocalDate())
					.modifiedDateTime(rs.getDate("modified_date_time").toLocalDate())
					.dob(rs.getDate("dob").toLocalDate())
					.isActive(rs.getBoolean("is_active"))
					.build();
			}
		}, args.toArray());
		
		if(existingUser.size() > 0)
		{
			return Optional.ofNullable(existingUser.get(0));
		}

		return Optional.empty();
	}
}
