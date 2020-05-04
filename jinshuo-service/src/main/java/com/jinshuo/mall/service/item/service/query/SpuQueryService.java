package com.jinshuo.mall.service.item.service.query;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinshuo.core.constant.DefaultShopId;
import com.jinshuo.core.exception.item.IcBizException;
import com.jinshuo.core.exception.item.IcReturnCode;
import com.jinshuo.core.utils.UserIdUtils;
import com.jinshuo.mall.domain.item.spu.Spu;
import com.jinshuo.mall.service.item.application.assermbler.SpuAssembler;
import com.jinshuo.mall.service.item.application.dto.*;
import com.jinshuo.mall.service.item.application.qry.*;
import com.jinshuo.mall.service.item.mybatis.SpuRepo;
import com.jinshuo.mall.service.item.service.command.SpuParameterComService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Classname SpuService
 * @Description TODO
 * @Date 2019/6/16 19:54
 * @Created by dyh
 */
@Slf4j
@Service
public class SpuQueryService {

    @Autowired
    private SpuRepo repo;

    @Autowired
    private SkuQueryService skuQueryService;

    @Autowired
    private CategoryQueryService categoryQueryService;

    @Autowired
    private SpuTagQueryService spuTagQueryService;

    @Autowired
    private SpuOtherQueryService spuOtherQueryService;

    @Autowired
    private SpuDescQueryService spuDescQueryService;

    @Autowired
    private AlbumQueryService albumQueryService;

    /*@Autowired
    private UserServiceResponse userServiceResponse;*/

    //@Autowired
    //private SupplierQueryService supplierQueryService;

    @Autowired
    private FeatureQueryService featureQueryService;

    @Autowired
    private ServiceSupportQueryService serviceSupportQueryService;

    @Autowired
    private SpuParameterComService spuParameterComService;


    /**
     * 后端分页查询spu列表
     *
     * @return qry
     * @Parm PageInfo
     */
    public PageInfo getPageInfo(SpuQry qry) {
        log.info(" -- 后端分页查询spu列表,输入参数：" + JSONObject.toJSONString(qry));
        qry.setFlag(1);
        PageInfo pageInfo = repo.findAll(qry);
        List<Spu> spus = pageInfo.getList();
        List<SpuDto> dtos = spus.stream()
                .map(spu -> SpuAssembler.assembleSpuDto(spu))
                //.map(spuDto -> spuDto.changeCategoryName(categoryQueryService.getById(Long.parseLong(spuDto.getCategoryId()))))
                .collect(Collectors.toList());
        dtos.forEach(spuDto -> {
            spuDto.setSkus(skuQueryService.getSkuDtosBySpuId(Long.parseLong(spuDto.getId())));
            changeCategoryNames(spuDto);
            //spuDto.changeCategoryName(categoryQueryService.getById(Long.parseLong(spuDto.getCategoryId())));
            spuDto.changeTags(spuTagQueryService.getBySpuId(Long.parseLong(spuDto.getId())));
        });
        pageInfo.setList(dtos);
        return pageInfo;
    }

    /**
     * 根据样本查找(rpcAPI) 静态sql
     *
     * @return id
     * @Parm SpuDto
     */
    public SpuDto findByExemple(SpuQry qry) {
        log.info(" -- 根据样本查找产品信息，输入参数：" + JSONObject.toJSONString(qry));
        if (null == qry || (StringUtils.isEmpty(qry.getSkuId()) && StringUtils.isEmpty(qry.getSpuId()))) {
            throw new IcBizException(IcReturnCode.IC201009.getCode(), IcReturnCode.IC201009.getMsg());
        }
        SkuDto skuDto = null;
        Spu spu = null;
        List<SkuDto> skuDtos = new ArrayList<>();
        if (null != qry.getSkuId() && !"".equals(qry.getSkuId())) {
            skuDto = skuQueryService.getSkuDtoBySkuId(qry.getSkuId());
            if (null == skuDto) {
                return null;
            }
            skuDtos.add(skuDto);
            spu = repo.findBySpuId(Long.parseLong(skuDto.getSpuId()));
        } else {
            spu = repo.findBySpuId(qry.getSpuId());
            skuDtos = skuQueryService.getSkuDtosBySpuId(qry.getSpuId());
        }
        if (null == spu) {
            return null;
        }
        SpuDto dto = SpuAssembler.assembleSpuDto(spu, skuDtos);
        //dto.changeTags(spuTagQueryService.getBySpuId(spu.getSpuId().getId()));
        dto.changeSpuOtherDto(spuOtherQueryService.getBySpuId(spu.getSpuId().getId()));
        if (null != spu.getSupplierId()) {
            //dto.changeSupplierName(userServiceResponse.querySupplier(spu.getSupplierId()));
            //dto.changeSupplierName(supplierQueryService.getById(spu.getSupplierId()));
        }
        return dto;
    }

    /**
     * 后端根据id查询spu信息
     *
     * @return id
     * @Parm SpuDto
     */
    public SpuDto findBySpuId(Long id) {
        Spu spu = repo.findBySpuId(id);
        SpuDto dto = SpuAssembler.assembleSpuDto(spu);
        if (null == dto) {
            return null;
        }
        dto.changeSkus(skuQueryService.getSkuDtosBySpuId(id));
        dto.changeTags(spuTagQueryService.getBySpuId(id));
        dto.changeUrl(albumQueryService.getUrlsBySpuId(id));
        dto.setParams(spuParameterComService.getBySpuId(spu.getSpuId().getId()));
        return dto;
    }


    /**
     * 前端分页查询产品列表
     *
     * @param qry
     * @return PageInfo
     */
    public PageInfo queryPageInfo(SpuQry qry) {
        log.info(" -- (首页)-前端分页查询产品列表,输入参数：" + JSONObject.toJSONString(qry));
        changeShopId(qry);
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        List<FrontSpuDto> dtos = repo.frontFindAll(qry);
        PageInfo pageInfo = new PageInfo(dtos);
        //changeFrontListDisMoney(dtos);
        pageInfo.setList(dtos);
        return pageInfo;
    }


    /**
     * 查询推荐列表
     *
     * @return PageInfo
     */
    public PageInfo getRecommendProduct(SpuQry qry) {
        log.info(" -- 查询推荐列表");
        List<Long> tags = new ArrayList<>();
        //推荐标签值
        tags.add(107437020204236800L);
        qry.setTags(tags);
        qry.setShopId(10066L);

        changeShopId(qry);
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        List<FrontSpuDto> dtos = repo.frontFindAll(qry);
        PageInfo pageInfo = new PageInfo(dtos);
        //changeFrontListDisMoney(dtos);
        pageInfo.setList(dtos);
        return pageInfo;
    }


    /**
     * 前端查询优品
     *
     * @param qry
     * @return PageInfo
     */
    public List<FrontSpuDto> getExcellent(SpuQry qry) {
        log.info(" -- (首页)-前端查询优品或爆款,输入参数：" + JSONObject.toJSONString(qry));
        changeShopId(qry);
        qry.setPageNum(null);
        qry.setPageSize(null);
        if (null == qry.getCount()) {
            qry.setCount(5);
        }
        List<FrontSpuDto> dtos = repo.findExcellent(qry);
        //changeFrontListDisMoney(dtos);
        return dtos;
    }

    /**
     * 前端根据id查询spu信息
     *
     * @return id
     * @Parm SpuDto
     */
    public UserSpuDto findAllInfoBySpuId(Long id) {
        log.info(" -- 前端根据id查询spu信息,输入参数id：" + id);
        Spu spu = repo.findBySpuId(id);
        UserSpuDto dto = SpuAssembler.assembleUserSpuDto(spu);
        if (null == dto) {
            return null;
        }
        changeName(dto);
        dto.setParams(spuParameterComService.getBySpuId(spu.getSpuId().getId()));
        try {
            List<UserSpuDto> list = new ArrayList<>();
            list.add(dto);
            //changeDisMoney(list);
            dto = list.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    /**
     * 根据id查询spu信息
     *
     * @return id
     * @Parm SpuDto
     */
    public Spu getBySpuId(Long id) {
        Spu spu = repo.findBySpuId(id);
        return spu;
    }


    /**
     * 前端分页查询产品列表(测试版，加shopId后使用)
     *
     * @param qry
     * @return PageInfo
     */
    public PageInfo queryPageInfoNew(SpuQry qry) {
        log.info(" -- 前端分页查询产品列表,输入参数：" + JSONObject.toJSONString(qry));
        PageInfo pageInfo = repo.findAllNew(qry);
        List<Spu> spus = pageInfo.getList();
        List<UserSpuDto> dtos = spus.stream()
                .map(spu -> SpuAssembler.assembleUserSpuDto(spu))
                //.map(spuDto -> spuDto.changeCategoryName(categoryQueryService.getById(Long.parseLong(spuDto.getCategoryId()))))
                .collect(Collectors.toList());
        dtos.forEach(spuDto -> {
            changeName(spuDto);
            /*spuDto.setSkus(skuQueryService.getUserSkuDtosBySpuId(Long.parseLong(spuDto.getId())));
            spuDto.changeCategoryName(categoryQueryService.getById(Long.parseLong(spuDto.getCategoryId())));
            spuDto.changeTags(spuTagQueryService.getBySpuId(Long.parseLong(spuDto.getId())));
            spuDto.changeSpuOtherDto(spuOtherQueryService.getBySpuId(Long.parseLong(spuDto.getId())));*/
        });
        pageInfo.setList(dtos);
        return pageInfo;
    }


    /**
     * 前端统一设置产品其他细节
     *
     * @param dto
     * @return
     */
    public UserSpuDto changeName(UserSpuDto dto) {
        dto.setSkus(skuQueryService.getUserSkuDtosBySpuId(Long.parseLong(dto.getId())));
        //dto.changeCategoryName(categoryQueryService.getById(Long.parseLong(dto.getCategoryId())));
        //dto.changeTags(spuTagQueryService.getBySpuId(Long.parseLong(dto.getId())));
        dto.changeSpuOtherDto(spuOtherQueryService.getBySpuId(Long.parseLong(dto.getId())));
        dto.changeUrl(albumQueryService.getUrlsBySpuId(Long.parseLong(dto.getId())));
        if (!StringUtils.isEmpty(dto.getFeatureId())) {
            //dto.changeFeatrue(featureQueryService.getById(Long.parseLong(dto.getFeatureId())));
        }
        try {
            dto.changeSpuDescDto(spuDescQueryService.findBySpuId(Long.parseLong(dto.getId())));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (!StringUtils.isEmpty(dto.getServices())) {
            dto.setServicesName(serviceSupportQueryService.changeServiceName(dto.getServices()));
        }
        return dto;
    }


    /**
     * 设置产品佣金信息
     *
     * @param dtos
     * @return
     */
    public List<UserSpuDto> changeDisMoney(List<UserSpuDto> dtos) {
        log.info(" -- 设置产品佣金信息,");
        Long userId = null;
        try {
            userId = UserIdUtils.getUserId();
        } catch (Exception e) {
            log.info(" -- 客户尚未登陆，不查询佣金信息！");
        }
        if (null == userId) {
            return null;
        }
        /*try {
            if (null != userId) {
                DisSalemanDto disSalemanDto = disServiceResponse.getByMemId(userId);
                if (null != disSalemanDto) {
                    for (UserSpuDto dto : dtos) {
                        DisSharProdutRpcDto disSharProdutRpcDto = disServiceResponse.queryComBySpuId(Long.parseLong(dto.getId()));
                        if (null != disSharProdutRpcDto && null != disSharProdutRpcDto.getDisPerP1() && null != dto.getPrice()) {
                            dto.setDisMoney(MathUtil.multiply(disSharProdutRpcDto.getDisPerP1(), dto.getPrice(), 1));
                            log.info("设置的佣金金额为：" + dto.getDisMoney());
                            for (UserSkuDto sku : dto.getSkus()) {
                                sku.setDisMoney(MathUtil.multiply(disSharProdutRpcDto.getDisPerP1(), sku.getPrice(), 1));
                            }
                        }
                    }
                }
            }
        } catch (NumberFormatException e) {
            log.error(" -- 设置产品佣金信息错误，", e);
        }*/
        return dtos;
    }

    /**
     * 设置产品佣金信息(列表使用)
     *
     * @param dtos
     * @return
     */
    public List<FrontSpuDto> changeFrontListDisMoney(List<FrontSpuDto> dtos) {
        log.info(" -- 设置产品佣金信息,");
        Long userId = null;
        try {
            userId = UserIdUtils.getUserId();
        } catch (Exception e) {
            log.info(" -- 客户尚未登陆，不查询佣金信息！");
        }
        if (null == userId) {
            return null;
        }
        /*try {
            if (null != userId) {
                DisSalemanDto disSalemanDto = disServiceResponse.getByMemId(userId);
                if (null != disSalemanDto) {
                    for (FrontSpuDto dto : dtos) {
                        DisSharProdutRpcDto disSharProdutRpcDto = disServiceResponse.queryComBySpuId(dto.getId());
                        if (null != disSharProdutRpcDto && null != disSharProdutRpcDto.getDisPerP1() && null != dto.getPrice()) {
                            dto.setDisMoney(MathUtil.multiply(disSharProdutRpcDto.getDisPerP1(), dto.getPrice(), 1));
                            log.info("设置的佣金金额为：" + dto.getDisMoney());
                        }
                    }
                }
            }
        } catch (NumberFormatException e) {
            log.error(" -- 设置产品佣金信息错误，", e);
        }*/
        return dtos;
    }


    /**
     * 活动主题查询产品
     *
     * @param qry
     * @return
     */
    public List<TopicProductDto> getTopicProduct(TopicProductQry qry) {
        return repo.findTopicProduct(qry);
    }


    /**
     * 活动尚未被选中的活动主题产品
     *
     * @param qry
     * @return
     */
    public PageInfo getNotYetTopicProduct(TopicProductPageQry qry) {
        log.info(" -- 活动尚未被选中的活动主题产品," + JSONObject.toJSONString(qry));
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        List<TopicProductDto> list = repo.getNotYetTopicProduct(qry);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 前端根据主题查询产品
     *
     * @param qry
     * @return
     */
    public List<TopicProductDto> getTopicProductByFront(TopicProductQry qry) {
        return repo.findTopicProductByFront(qry);
    }

    public SpuQry changeShopId(SpuQry spuQry) {
        if (null == spuQry.getShopId()) {
            try {
                spuQry.setShopId(10066L);
            } catch (Exception e) {
                e.printStackTrace();
                throw new IcBizException(IcReturnCode.IC201014.getMsg());
            }
        }
        return spuQry;
    }


    /**
     * 分页查询分销产品查询接口
     *
     * @param spuQry
     * @return
     */
    public PageInfo queryDisProduct(SpuQry spuQry) {
        log.info(" -- 分页查询分销产品查询接口");
        spuQry.setShopId(DefaultShopId.SHOPID);
        changeShopId(spuQry);
        PageHelper.startPage(spuQry.getPageNum(), spuQry.getPageSize());
        List<FrontSpuDto> spus = repo.findDis(spuQry);
        PageInfo pageInfo = new PageInfo(spus);
        /*DisSalemanDto disSaleman = disServiceResponse.getByMemId(UserIdUtils.getUserId());
        List<UserDisShareProductDto> dtos = new ArrayList<>();
        UserDisShareProductDto disShareProductDto;
        try {
            if (null != disSaleman) {
                for (FrontSpuDto dto : spus) {
                    disShareProductDto = new UserDisShareProductDto();
                    BeanUtils.copyProperties(dto, disShareProductDto);
                    disShareProductDto.setProductId(dto.getId().toString());
                    disShareProductDto.setProductName(dto.getName());
                    if (null != disSaleman.getRankId() && (1l == disSaleman.getRankId() || 2l == disSaleman.getRankId())) {
                        BeanUtils.copyProperties(dto, disShareProductDto);
                        DisSharProdutRpcDto disSharProdutRpcDto = disServiceResponse.queryComBySpuId(dto.getId());
                        if (null != disSharProdutRpcDto && null != disSharProdutRpcDto.getDisPerP1()) {
                            disShareProductDto.setDisMoney(MathUtil.multiply(disSharProdutRpcDto.getDisPerP1(), dto.getPrice(), 1));
                            dtos.add(disShareProductDto);
                        }
                    }
                }
            }
        } catch (BeansException e) {
            e.printStackTrace();
            log.error(" -- 产品分销产品（分享）失败", e);
        }
        pageInfo.setList(dtos);*/
        return pageInfo;
    }

    /**
     * 茶叶店（根据类目查询产品一次性返回，30个兜底）
     *
     * @param spuQry
     * @return
     */
    public List<CategorySpu> getByCategory(SpuQry spuQry) {
        log.info(" -- 根据类目查询产品一次性返回,输入参数：" + JSONObject.toJSONString(spuQry));
        CategoryQry categoryQry = CategoryQry.builder().shopId(spuQry.getShopId()).pageSize(30).build();
        List<CategoryDto> categoryDtos = categoryQueryService.getFirstPageCategorys(categoryQry);
        List<CategorySpu> categorySpus = new ArrayList<>();
        CategorySpu categorySpu;
        for (CategoryDto dto : categoryDtos) {
            categorySpu = new CategorySpu();
            categorySpu.setCategoryId(dto.getId());
            categorySpu.setCategoryName(dto.getName());
            spuQry.setCategoryId(Long.parseLong(dto.getId()));
            spuQry.setPageSize(30);
            List<FrontSpuDto> spus = repo.frontFindAll(spuQry);
            if (null != spus) {
                categorySpu.setSpus(spus);
            }
            categorySpus.add(categorySpu);
        }
        return categorySpus;
    }

    /**
     * 分页查询供应商下的产品
     *
     * @param qry
     * @return
     */
    public PageInfo findBySupplierId(PageQuery qry) {
        log.info(" -- 分页查询供应商下的产品{}", qry);
        SpuQry spuQry = SpuQry.builder()
                .pageNum(qry.getPageNum())
                .pageSize(qry.getPageSize())
                //.supplierId(UserIdUtils.getSupplierByUserId().getSupplierId())
                .build();
        PageInfo pageInfo = getPageInfo(spuQry);
        return pageInfo;
    }


    /**
     * 分页查询供应商下的产品
     *
     * @param qry
     * @return
     */
    public CartItemDto getDtoSpuById(SpuQry qry) {
        SpuDto dto = findByExemple(qry);
        CartItemDto cartItemDto = CartItemDto.builder()
                .pictureUrl(dto.getPictureUrl())
                .skuId(dto.getSkus().get(0).getId())
                .skuName(dto.getSkus().get(0).getName())
                .spuName(dto.getName())
                .goodsPrice(dto.getSkus().get(0).getPrice())
                .stock(dto.getSkus().get(0).getStock())
                .spuId(dto.getId())
                .build();
        return cartItemDto;
    }

    public SpuQry querySpuQry(SpuQry qry) {
        List<Long> longs = repo.querySpuWithParam(qry);
        if (null == longs || longs.size() < 1) {
            return qry;
        }
        String s = "(";
        for (Long l : longs) {
            s += l.toString() + ",";
        }
        s = s.substring(0, s.length() - 1);
        s = s + ")";
        qry.setValueIds(s);
        return qry;
    }

    /**
     * 转换为类目名称
     *
     * @param spuDto
     * @param spuDto
     */
    public void changeCategoryNames(SpuDto spuDto) {
        if (null == spuDto || null == spuDto.getCategoryId() || spuDto.getCategoryId().size() < 1) {
            return;
        }
        for (String categoryId : spuDto.getCategoryId()) {
            spuDto.setCategoryName(categoryQueryService.getById(Long.parseLong(categoryId)).getName() + ",");
        }
        if (null != spuDto.getCategoryName() && spuDto.getCategoryName().length() > 0) {
            spuDto.setCategoryName(spuDto.getCategoryName().substring(0, spuDto.getCategoryName().length() - 1));
        }
    }

    /**
     * 查询售罄数量
     *
     * @param shopId
     * @return
     */
    public int getSoldOutCount(Long shopId) {
        return repo.getSoldOutCount(shopId);
    }
}
