package com.jinshuo.mall.domain.user.model.cpmplain;

import com.jinshuo.core.model.IdentifiedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* 举报ID
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComplainImageId extends IdentifiedEntity {
	private Long id;
}
	
