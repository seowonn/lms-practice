package com.zerobase.fastlms.admin.dto;

import com.zerobase.fastlms.member.entity.LoginHistory;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginHistoryDto {

    private String userId;
    private LocalDateTime logDt;
    private String accessIp;
    private String userAgent;

    private Long seq;
    private Long totalCount;

    public static LoginHistoryDto of(LoginHistory history) {
        return LoginHistoryDto.builder()
                .userId(history.getUserId())
                .logDt(history.getLogDt())
                .accessIp(history.getAccessIp())
                .userAgent(history.getUserAgent())
                .build();
    }

    public String getLogDtText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        return logDt != null ? logDt.format(formatter) : "";
    }
}
