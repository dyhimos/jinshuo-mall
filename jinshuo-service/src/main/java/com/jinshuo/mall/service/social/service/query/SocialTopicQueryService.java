package com.jinshuo.mall.service.social.service.query;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinshuo.core.utils.HtmlUtil;
import com.jinshuo.core.utils.UserIdUtils;
import com.jinshuo.mall.domain.social.topic.*;
import com.jinshuo.mall.service.social.application.assermbler.SocialTopicAssembler;
import com.jinshuo.mall.service.social.application.dto.SocialTopicCommentDto;
import com.jinshuo.mall.service.social.application.dto.SocialTopicDto;
import com.jinshuo.mall.service.social.application.dto.SocialTopicUpDto;
import com.jinshuo.mall.service.social.application.qry.SocialCommentQry;
import com.jinshuo.mall.service.social.application.qry.SocialTopicQry;
import com.jinshuo.mall.service.social.mybatis.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/12/26.
 */
@Slf4j
@Service
public class SocialTopicQueryService {

    private final static String regxpForHtml = "<([^>]*)>"; // 过滤所有以<开头以>结尾的标签

    @Autowired
    private SocialTopicRepo socialTopicRepo;

    @Autowired
    private SocialTopicUpRepo socialTopicUpRepo;

    @Autowired
    private SocialTopicCollectRepo socialTopicCollectRepo;

    @Autowired
    private SocialTopicAlbumRepo socialTopicAlbumRepo;

    @Autowired
    private SocialTopicCommentRepo socialTopicCommentRepo;

    @Autowired
    private SocialTopicReplyRepo socialTopicReplyRepo;


    /**
     * 分页查询帖子
     *
     * @param qry
     * @return
     */
    public PageInfo quryTopicPage(SocialTopicQry qry) {
        log.info(" -- 分页查询帖子，输入参数，{}", qry);
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        List<SocialTopic> socialTopicList = socialTopicRepo.queryTopicByExmple(qry);
        PageInfo pageInfo = new PageInfo(socialTopicList);
        List<SocialTopicDto> dtos = socialTopicList.stream()
                .map(socialTopic -> SocialTopicAssembler.assembleTopicDto(socialTopic))
                .map(socialTopicDto -> {
                    socialTopicDto.setUrls(quryAlbumByTopicId(socialTopicDto.getId()));
                    return socialTopicDto;
                }).collect(Collectors.toList());
        pageInfo.setList(dtos);
        return pageInfo;
    }

    /**
     * 分页查询帖子
     *
     * @param qry
     * @return
     */
    public PageInfo frontQuryTopicPage(SocialTopicQry qry) {
        log.info(" -- 客户分页查询帖子，输入参数，{}", qry);
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        qry.setShieldUserId(UserIdUtils.getUserId());
        List<SocialTopic> socialTopicList = socialTopicRepo.queryTopicByExmple(qry);
        List<SocialTopicDto> dtos = socialTopicList.stream()
                .map(socialTopic -> SocialTopicAssembler.assembleTopicDto(socialTopic))
                .map(socialTopicDto -> {
                    socialTopicDto.setUrls(quryAlbumByTopicId(socialTopicDto.getId()));
                    queryIsCollect(socialTopicDto);
                    queryIsUp(socialTopicDto);
                    socialTopicDto.setContents(interceptProfile(socialTopicDto.getContents()));
                    return socialTopicDto;
                }).collect(Collectors.toList());
        PageInfo pageInfo = new PageInfo(socialTopicList);
        pageInfo.setList(dtos);
        return pageInfo;
    }

    /**
     * 分页查询我收藏的帖子
     *
     * @param qry
     * @return
     */
    public PageInfo queryMyCollecttopic(SocialTopicQry qry) {
        log.info(" -- 分页查询我收藏的帖子，输入参数，{}", qry);
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        qry.setUserId(UserIdUtils.getUserId());
        List<SocialTopic> socialTopicList = socialTopicRepo.queryMyCollecttopic(qry);
        List<SocialTopicDto> dtos = socialTopicList.stream()
                .map(socialTopic -> SocialTopicAssembler.assembleTopicDto(socialTopic))
                .map(socialTopicDto -> {
                    socialTopicDto.setUrls(quryAlbumByTopicId(socialTopicDto.getId()));
                    socialTopicDto.setIsCollect(0);
                    queryIsUp(socialTopicDto);
                    socialTopicDto.setContents(interceptProfile(socialTopicDto.getContents()));
                    return socialTopicDto;
                }).collect(Collectors.toList());
        PageInfo pageInfo = new PageInfo(socialTopicList);
        pageInfo.setList(dtos);
        return pageInfo;
    }

    /**
     * 根据帖子id查询评论
     *
     * @param qry
     * @return
     */
    public PageInfo quryTopicCommentPage(SocialTopicQry qry) {
        log.info(" -- 根据帖子id查询评论，输入参数，{}", qry);
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        List<SocialTopicComment> list = socialTopicCommentRepo.findByTopicId(qry.getId());
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 后台管理查询评论
     *
     * @param qry
     * @return
     */
    public PageInfo quryTopicCommentPageManager(SocialCommentQry qry) {
        log.info(" -- 后台管理查询评论，输入参数，{}", qry);
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        SocialTopicComment temp = new SocialTopicComment();
        BeanUtils.copyProperties(qry, temp);
        List<SocialTopicComment> list = socialTopicCommentRepo.findByExmple(temp);
        List<SocialTopicCommentDto> dtos = list.stream()
                .map(socialTopicComment -> SocialTopicAssembler.assembleCommentDto(socialTopicComment))
                .map(socialTopicCommentDto -> {
                    SocialTopic socialTopic = socialTopicRepo.findById(socialTopicCommentDto.getTopicId());
                    if (null != socialTopic) {
                        socialTopicCommentDto.setTitle(socialTopic.getTitle());
                    }
                    return socialTopicCommentDto;
                })
                .collect(Collectors.toList());
        PageInfo pageInfo = new PageInfo(list);
        pageInfo.setList(dtos);
        return pageInfo;
    }

    /**
     * 分页查询帖子
     *
     * @param qry
     * @return
     */
    public PageInfo frontQuryTopicCommentPage(SocialTopicQry qry) {
        log.info(" -- 客户分页查询评论(已通过审核)，输入参数，{}", qry);
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        List<SocialTopicComment> list = socialTopicCommentRepo.findReviewedByTopicId(qry.getId());
        List<SocialTopicCommentDto> dtos = list.stream()
                .map(socialTopicComment -> SocialTopicAssembler.assembleCommentDto(socialTopicComment))
                .map(socialTopicCommentDto -> {
                    queryCommentIsUp(socialTopicCommentDto);
                    return socialTopicCommentDto;
                })
                .collect(Collectors.toList());
        PageInfo pageInfo = new PageInfo(list);
        pageInfo.setList(dtos);
        return pageInfo;
    }


    /**
     * 查询帖子详情
     *
     * @param qry
     * @return
     */
    public SocialTopicDto quryByTopicId(SocialTopicQry qry) {
        log.info(" -- 查询帖子详情，输入参数，{}", qry);
        SocialTopic socialTopic = socialTopicRepo.findById(qry.getId());
        SocialTopicDto dto = SocialTopicAssembler.assembleTopicDto(socialTopic);
        queryIsCollect(dto);
        queryIsUp(dto);
        dto.setUrls(quryAlbumByTopicId(dto.getId()));
        return dto;
    }


    /**
     * 查询帖子图片列表
     *
     * @param topicId
     * @return
     */
    public List<String> quryAlbumByTopicId(Long topicId) {
        log.info(" -- 查询帖子图片列表，输入参数，{}", topicId);
        List<SocialTopicAlbum> socialTopicAlbums = socialTopicAlbumRepo.findByTopicId(topicId);
        List<String> urls = null;
        if (null != socialTopicAlbums && socialTopicAlbums.size() > 0) {
            urls = socialTopicAlbums.stream().map(SocialTopicAlbum::getPhotoUrl).collect(Collectors.toList());
        }
        return urls;
    }

    /**
     * 查询帖子是否点赞
     *
     * @param dto
     * @return
     */
    public int queryIsUp(SocialTopicDto dto) {
        SocialTopicUp socialTopicUp = null;
        try {
            socialTopicUp = socialTopicUpRepo.findByType(UserIdUtils.getUserId(), dto.getId());
        } catch (Exception e) {
        }
        if (null == socialTopicUp) {
            dto.setIsUp(1);
            return 1;
        }
        dto.setIsUp(0);
        return 0;
    }

    /**
     * 查询评论是否点赞
     *
     * @param dto
     * @return
     */
    public int queryCommentIsUp(SocialTopicCommentDto dto) {
        SocialTopicUp socialTopicUp = null;
        try {
            socialTopicUp = socialTopicUpRepo.findByType(UserIdUtils.getUserId(), dto.getId());
        } catch (Exception e) {
        }
        if (null == socialTopicUp) {
            dto.setIsUp(1);
            return 1;
        }
        dto.setIsUp(0);
        return 0;
    }

    /**
     * 查询是否收藏
     *
     * @param dto
     * @return
     */
    public int queryIsCollect(SocialTopicDto dto) {
        if (null == dto) {
            return 0;
        }
        SocialTopicCollect socialTopicCollect = null;
        try {
            socialTopicCollect = socialTopicCollectRepo.findByType(UserIdUtils.getUserId(), dto.getId());
        } catch (Exception e) {
        }
        if (null == socialTopicCollect) {
            dto.setIsCollect(1);
            return 1;
        }
        dto.setIsCollect(0);
        return 0;
    }

    /**
     * 查询我的评论
     *
     * @return
     */
    public PageInfo quryMyCommentPage(SocialTopicQry qry) {
        log.info(" -- 查询我的评论,输入参数{}，", qry);
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        List<SocialTopicComment> list = socialTopicCommentRepo.findByUserId(UserIdUtils.getUserId());
        PageInfo pageInfo = new PageInfo(list);
        List<SocialTopicCommentDto> dtos = list.stream()
                .map(socialTopicComment -> SocialTopicAssembler.assembleCommentDto(socialTopicComment))
                .map(socialTopicCommentDto -> {
                    SocialTopic socialTopic = socialTopicRepo.findById(socialTopicCommentDto.getTopicId());
                    socialTopicCommentDto.setTitle(socialTopic.getTitle());
                    socialTopicCommentDto.setContents(socialTopic.getContents());
                    return socialTopicCommentDto;
                })
                .collect(Collectors.toList());
        pageInfo.setList(dtos);
        return pageInfo;
    }

    /**
     * 分页查询我的点赞
     *
     * @param qry
     * @return
     */
    public PageInfo quryMyUpPage(SocialTopicQry qry) {
        log.info(" -- 分页查询我的点赞,输入参数{}", qry);
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        List<SocialTopicUp> socialTopicUps = socialTopicUpRepo.findByUserId(UserIdUtils.getUserId(), qry.getOperateType());
        List<SocialTopicUpDto> dtos = socialTopicUps.stream()
                .map(socialTopicUp -> SocialTopicAssembler.assembleUpDto(socialTopicUp))
                .map(socialTopicUpDto -> {
                    socialTopicUpDto.setIsUp(0);
                    //点赞类型 0点赞帖子 1点赞评论
                    if (1 == socialTopicUpDto.getOperateType()) {
                        SocialTopicComment socialTopicComment = socialTopicCommentRepo.findById(socialTopicUpDto.getTopicId());
                        if (null != socialTopicComment) {
                            socialTopicUpDto.setContents(socialTopicComment.getContent());
                            socialTopicUpDto.setUpCount(socialTopicComment.getUpCount());
                        }
                    } else {
                        SocialTopic socialTopic = socialTopicRepo.findById(socialTopicUpDto.getTopicId());
                        if (null != socialTopic) {
                            socialTopicUpDto.setTitle(socialTopic.getTitle());
                            socialTopicUpDto.setContents(interceptProfile(socialTopic.getContents()));
                            socialTopicUpDto.setUpCount(socialTopic.getUpCount());
                        }
                    }
                    return socialTopicUpDto;
                })
                .collect(Collectors.toList());
        PageInfo pageInfo = new PageInfo(socialTopicUps);
        pageInfo.setList(dtos);
        return pageInfo;
    }

    /**
     * 查询我的被点赞
     *
     * @param qry
     * @return
     */
    public PageInfo quryMyUpedPage(SocialTopicQry qry) {
        log.info(" -- 查询我被点赞的帖子评论,输入参数，{}", qry);
        List<SocialTopic> socialTopicList = socialTopicRepo.quryMyUpedPage(UserIdUtils.getUserId());
        List<SocialTopicDto> dtos = socialTopicList.stream()
                .map(socialTopic -> SocialTopicAssembler.assembleTopicDto(socialTopic))
                .map(socialTopicDto -> {
                    socialTopicDto.setUrls(quryAlbumByTopicId(socialTopicDto.getId()));
                    queryIsCollect(socialTopicDto);
                    queryIsUp(socialTopicDto);
                    return socialTopicDto;
                }).collect(Collectors.toList());
        PageInfo pageInfo = new PageInfo(socialTopicList);
        pageInfo.setList(dtos);
        return pageInfo;
    }


    public String interceptProfile(String contents) {
        if (!StringUtils.isNotBlank(contents)) {
            return contents;
        }
        contents = HtmlUtil.getTextFromHtml(contents);
        if (contents.length() > 80) {
            contents = contents.substring(0, 79);
        }
        return contents;
    }
}
