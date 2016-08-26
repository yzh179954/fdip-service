package com.trusdom.fdip.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import com.trusdom.fdip.model.Account3RD;

public interface Account3RDMapper {
	
	@Insert({"insert into 3rd_account (channel,account,accountInfo,userId) values (#{channel.id},#{account.id},#{accountInfo},#{userId})"})
	@Options(keyProperty="id",keyColumn="id",useGeneratedKeys=true)
	void insert(Account3RD account3RD);
	
	@Select({"select * from 3rd_account where id = #{id}"})
	@Results({
		@Result(property="channel",column="channel",one=@One(select="com.trusdom.fdip.mappers.ChannelMapper.findById",fetchType=FetchType.EAGER)),
		@Result(property="account",column="account",one=@One(select="com.trusdom.fdip.mappers.AccountMapper.findById",fetchType=FetchType.EAGER))
	})
	Account3RD findById(Long id);
	
	@Select({"select * from 3rd_account where channel = #{channelId} and account = #{accountId}"})
	@Results({
		@Result(property="channel",column="channel",one=@One(select="com.trusdom.fdip.mappers.ChannelMapper.findById",fetchType=FetchType.EAGER)),
		@Result(property="account",column="account",one=@One(select="com.trusdom.fdip.mappers.AccountMapper.findById",fetchType=FetchType.EAGER))
	})
	Account3RD findByChannlAndAccount(@Param("channelId") Long channelId,@Param("accountId") Long accountId);
	
	@Select({"select * from 3rd_account where channel = #{channelId} "})
	@Results({
		@Result(property="channel",column="channel",one=@One(select="com.trusdom.fdip.mappers.ChannelMapper.findById",fetchType=FetchType.EAGER)),
		@Result(property="account",column="account",one=@One(select="com.trusdom.fdip.mappers.AccountMapper.findById",fetchType=FetchType.EAGER))
	})
	List<Account3RD> findByChannl(@Param("channelId") Long channelId);
	
	
	@Select({"select * from 3rd_account where channel = #{channelId} and account = (select id from account where accountNo = #{accountNo})"})
	@Results({
		@Result(property="channel",column="channel",one=@One(select="com.trusdom.fdip.mappers.ChannelMapper.findById",fetchType=FetchType.EAGER)),
		@Result(property="account",column="account",one=@One(select="com.trusdom.fdip.mappers.AccountMapper.findById",fetchType=FetchType.EAGER))
	})
	Account3RD findByChannlAndAccount2(@Param("channelId") Long channelId,@Param("accountNo") String accountNo);
	
	@Select("select account from 3rd_account where userId = #{userId}")
	long findAccountByAccount3RD(@Param("userId") String userId);
	
	@Select("select * from 3rd_account where userId = #{userId}")
	@Results({
		@Result(property="channel",column="channel",one=@One(select="com.trusdom.fdip.mappers.ChannelMapper.findById",fetchType=FetchType.EAGER)),
		@Result(property="account",column="account",one=@One(select="com.trusdom.fdip.mappers.AccountMapper.findById",fetchType=FetchType.EAGER))
	})
	Account3RD findBy3rd(@Param("userId") String userId);
}
