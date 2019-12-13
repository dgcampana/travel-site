package com.kmp.site.travelweb.domain.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "travel", url = "${backend.services.api}")
public interface TravelClient {

}
