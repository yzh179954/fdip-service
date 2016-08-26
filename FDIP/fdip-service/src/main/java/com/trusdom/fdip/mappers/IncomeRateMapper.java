package com.trusdom.fdip.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.trusdom.fdip.model.IncomeRate;

public interface IncomeRateMapper {

	@Insert("insert into income_rate(channel, fund, day, millionIncomeRate, annualIncome7d, createTime) values(#{channel}, #{fund}, #{day}, #{millionIncomeRate}, #{annualIncome7d}, #{createTime})")
	@Options(keyProperty="id", keyColumn="id", useGeneratedKeys=true)
	void save(IncomeRate incomeRate);
	
	@Select("select * from income_rate where channel = #{channel} and fund = #{fund} and day = #{day} limit 0,1")
	IncomeRate findByDay(@Param("channel") long channel, @Param("fund") long fund, @Param("day") String day);
	
	@Select("select * from income_rate where channel = #{channel} and fund = #{fund} and day >= #{beginDate} and day <= #{endDate}")
	List<IncomeRate> findByDate(@Param("channel") long channel, @Param("fund") long fund, @Param("beginDate") String beginDate, @Param("endDate") String endDate);
}
