package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.entity.Banner;
import com.zerobase.fastlms.admin.mapper.BannerMapper;
import com.zerobase.fastlms.admin.model.BannerInput;
import com.zerobase.fastlms.admin.model.BannerParam;
import com.zerobase.fastlms.admin.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BannerServiceImpl implements BannerService{

    private final BannerMapper bannerMapper;

    private final BannerRepository bannerRepository;

    @Override
    public List<BannerDto> list(BannerParam parameter){

        long totalCount = bannerMapper.selectListCount(parameter);

        List<BannerDto> list = bannerMapper.selectList(parameter);

        if(!CollectionUtils.isEmpty(list)){
            int i = 0;
            for(BannerDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }
        return list;
    }

    @Override
    public boolean register(BannerInput parameter) {
        Banner banner = Banner.builder()
                .bannerName(parameter.getBannerName())
                .bannerUrl(parameter.getBannerUrl())
                .openMode(parameter.getOpenMode())
                .sortOrder(parameter.getSortOrder())
                .showYn(parameter.getShowYn())
                .regDt(LocalDateTime.now())
                .bannerImgFile(parameter.getBannerImgFile())
                .bannerAlterText(parameter.getBannerAlterText())
                .build();
        bannerRepository.save(banner);
        return true;
    }
}
