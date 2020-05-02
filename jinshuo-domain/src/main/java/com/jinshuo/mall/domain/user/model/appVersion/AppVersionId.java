package com.jinshuo.mall.domain.user.model.appVersion;
import com.jinshuo.core.model.IdentifiedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* app版本管理
ID
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppVersionId extends IdentifiedEntity {
	private Long id;
}
	
