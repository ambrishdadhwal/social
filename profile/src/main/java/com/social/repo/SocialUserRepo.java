package com.social.repo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.social.common.Country;
import com.social.domain.SocialUser;

@Repository
public class SocialUserRepo
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

	public Optional<SocialUser> getUser(SocialUser user)
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

		List<SocialUser> existingUser = jdbcTemplate.query(sql.toString(), new RowMapper<SocialUser>()
		{

			@Override
			public SocialUser mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				return SocialUser.builder()
					.id(rs.getLong("id"))
					.country(Country.getCountry(rs.getString("country")))
					.email(rs.getString("email"))
					.firstName(rs.getString("first_name"))
					.lastName(rs.getString("last_name"))
					.createDateTime(rs.getTimestamp("create_date_time").toLocalDateTime())
					.modifiedDateTime(rs.getTimestamp("modified_date_time").toLocalDateTime())
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
