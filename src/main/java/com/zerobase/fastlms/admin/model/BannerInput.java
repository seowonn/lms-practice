package com.zerobase.fastlms.admin.model;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Data
public class BannerInput {

    Long id;

    String bannerName;
    String bannerUrl;
    String openMode;
    String sortOrder;
    String showYn;

    LocalDateTime regDt;

    String bannerImgFile;
    String bannerAlterText;
}
