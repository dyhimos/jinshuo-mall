package com.jinshuo.mall.service.social.mybatis;

import com.jinshuo.mall.domain.social.topic.SocialTopicAlbum;
import com.jinshuo.mall.service.social.mybatis.mapper.SocialTopicAlbumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 19458 on 2019/12/25.
 * @author dyh
 */
@Repository
public class SocialTopicAlbumRepo {

    @Autowired
    private SocialTopicAlbumMapper socialTopicAlbumMapper;

    public int create(SocialTopicAlbum socialTopicAlbum) {
        return socialTopicAlbumMapper.create(socialTopicAlbum);
    }

    public SocialTopicAlbum findById(Long id) {
        return socialTopicAlbumMapper.queryById(id);
    }

    public List<SocialTopicAlbum> findByTopicId(Long topicId) {
        return socialTopicAlbumMapper.queryByTopicId(topicId);
    }

    public int deleteById(Long id) {
        return socialTopicAlbumMapper.deleteById(id);
    }
}