package com.jinshuo.mall.service.user.mybatis;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.user.model.cpmplain.Complain;
import com.jinshuo.mall.domain.user.model.cpmplain.ComplainId;
import com.jinshuo.mall.service.user.mybatis.mapper.ComplainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* app版本管理Repo
* @Classname ComplainRepo
* @Description TODO
* @Date 2019/6/16 20:07
* @Created by mgh
*/
@Repository
public class ComplainRepo {

	@Autowired
	private ComplainMapper mapper;

	/**
	* 生成id

	*/
	public ComplainId nextId() {
		return new ComplainId(CommonSelfIdGenerator.generateId());
	}

	/**
	 * 查询全部投诉图片
	 * @param complainId
	 * @return
	 */
	public List<String> findComplainImages(Long complainId) {
		return mapper.selectCompainImages(complainId);
	}

	/**
	* 查询全部
	* @param complain
	* @return
	*/
	public List<Complain> findAll(Complain complain) {
		return mapper.selectAll(complain);
	}

	/**
	* 保存或更新
	* @param complain
	*/
	@Transactional
	public void save(Complain complain){
		if(complain.getComplainId()==null){
			ComplainId complainId =  nextId();
			complain.setComplainId(complainId);
			mapper.save(complain);

			/**
			 * 保存投诉图片
			 */
			if(complain.getImagePaths().size()>0){
				mapper.saveCompainImages(complainId.getId(),complain.getImagePaths());
			}
		}else{
			complain.preUpdate();
			/**
			 * 保存投诉图片
			 */
			if(complain.getImagePaths().size()>0){
				mapper.deleteCompainImages(complain.getId());
				mapper.saveCompainImages(complain.getId(),complain.getImagePaths());
			}
			mapper.update(complain);
		}
	}

	/**
	* 根据id查询记录
	* @param id
	* @return
	*/
	public Complain findById(Long id){
		return mapper.findById(id);
	}
}

	
