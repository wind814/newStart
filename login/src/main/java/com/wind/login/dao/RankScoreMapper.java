package com.wind.login.dao;

import com.wind.login.entity.RankScore;
import io.lettuce.core.dynamic.annotation.Param;
import tk.mybatis.mapper.common.Mapper;

public interface RankScoreMapper extends Mapper<RankScore> {


    String getById(@Param("id") Long id);
}

