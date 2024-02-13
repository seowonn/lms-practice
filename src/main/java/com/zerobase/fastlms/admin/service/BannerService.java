package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.model.BannerInput;
import com.zerobase.fastlms.admin.model.BannerParam;

import java.util.List;

public interface BannerService{
    List<BannerDto> list(BannerParam parameter);
    boolean register(BannerInput parameter);
    void deleteSelectedBanners(List<String> parameters);
}
