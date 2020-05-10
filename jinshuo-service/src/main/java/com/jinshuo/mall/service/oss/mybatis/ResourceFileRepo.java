package com.jinshuo.mall.service.oss.mybatis;

import com.jinshuo.mall.domain.oss.OssResourceFile;
import com.jinshuo.mall.service.oss.mybatis.mapper.ResourceFileMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 19458 on 2019/6/19.
 */
@Repository
public class ResourceFileRepo {

    private ResourceFileMapper mapper;

    public void saveFile(OssResourceFile resourceFile) {
        mapper.create(resourceFile);
    }

    public void updateFile(OssResourceFile resourceFile) {

    }

    public void deleteFile(String[] ids) {
        mapper.delete(ids);
    }

    public List<OssResourceFile> findByDicId(OssResourceFile dto) {
        return mapper.selectByDicId(dto);
    }

    public OssResourceFile findById(Long id) {
        return mapper.selectById(id);
    }

    public List<OssResourceFile> findByDId(Long id) {
        return mapper.selectByDId(id);
    }

    public int deleteByDicId(Long id) {
        return mapper.deleteByDicId(id);
    }
}
