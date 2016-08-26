package com.trusdom.fdip.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.trusdom.fdip.model.Channel;

public interface ChannelMapper {
	@Select({"select * from channel where id = #{id}"})
	Channel findById(Long id);
	
	@Select({"select * from channel where code = #{code}"})
	Channel findByCode(String code);
	
	@Insert({"insert into channel (code,name,accountNo) values (#{code},#{name},#{accountNo})"})
	@Options(keyProperty="id",keyColumn="id",useGeneratedKeys=true)
	Long insert(Channel channel);
}
