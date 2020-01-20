package com.kmp.site.travelweb.domain.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.kmp.site.travelweb.application.dto.GenericResponse;
import com.kmp.site.travelweb.application.dto.login.RequestUserCheck;
import com.kmp.site.travelweb.application.dto.login.ResponseUserCheck;

@FeignClient(name = "user", url = "${backend.services.api}")
public interface UserClient {

	@PostMapping(value = "/user/check")
	ResponseUserCheck userCheck(@RequestBody RequestUserCheck req);
	
	
	@GetMapping(value = "/user/validate")
	GenericResponse userValidate(@RequestParam("token") String token);
	
	
	
	
}
