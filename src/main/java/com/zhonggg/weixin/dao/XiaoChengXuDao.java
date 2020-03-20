package com.zhonggg.weixin.dao;

import com.zhonggg.weixin.dto.PoemDTO;
import com.zhonggg.weixin.vo.PoemVO;

import java.util.List;

public interface XiaoChengXuDao {
    List<PoemVO> getPoemList(PoemDTO poemDTO);

    PoemVO getPoemById(Integer id);
}
