package com.kmp.site.travelweb.domain.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.kmp.site.travelweb.application.dto.login.RequestUserCheck;
import com.kmp.site.travelweb.application.dto.login.ResponseUserCheck;

@FeignClient(name = "login", url = "${backend.services.api}")
public interface LoginClient {

	@PostMapping(value = "/user/check")
	ResponseUserCheck userCheck(@RequestBody RequestUserCheck req);
	
	
	
}
