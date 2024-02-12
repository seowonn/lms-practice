package com.zerobase.fastlms.admin.dto;

import com.zerobase.fastlms.admin.entity.Banner;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BannerDto {

    Long id;

    String bannerName;
    String bannerUrl;
    String openMode;
    String sortOrder;
    String showYn;

    LocalDateTime regDt;

    String bannerImgFile;
    String bannerAlterText;

    long totalCount;
    long seq;


    public static BannerDto of(Banner banner) {
        return BannerDto.builder()
                .id(banner.getId())
                .bannerName(banner.getBannerName())
                .bannerUrl(banner.getBannerUrl())
                .openMode(banner.getOpenMode())
                .sortOrder(banner.getSortOrder())
                .showYn(banner.getShowYn())
                .regDt(banner.getRegDt())
                .bannerImgFile(banner.getBannerImgFile())
                .bannerAlterText(banner.getBannerAlterText())
                .build();
    }

    public String getRegDtText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        return regDt != null ? regDt.format(formatter) : "";
    }
}
