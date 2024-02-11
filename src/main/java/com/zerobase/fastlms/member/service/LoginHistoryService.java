package com.zerobase.fastlms.member.service;

import com.zerobase.fastlms.admin.dto.LoginHistoryDto;
import com.zerobase.fastlms.admin.model.MemberParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LoginHistoryService {
    List<LoginHistoryDto> list(MemberParam parameter);
}
