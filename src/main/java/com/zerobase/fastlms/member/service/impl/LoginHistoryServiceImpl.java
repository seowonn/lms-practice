package com.zerobase.fastlms.member.service.impl;

import com.zerobase.fastlms.admin.dto.LoginHistoryDto;
import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.member.entity.LoginHistory;
import com.zerobase.fastlms.member.repository.LoginHistoryRepository;
import com.zerobase.fastlms.member.service.LoginHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LoginHistoryServiceImpl implements LoginHistoryService {

    private final LoginHistoryRepository loginHistoryRepository;

    @Override
    public List<LoginHistoryDto> list(MemberParam parameter) {
        long totalCount =
                loginHistoryRepository.countByUserId(parameter.getUserId());

        Optional<List<LoginHistory>> allByUserId =
                loginHistoryRepository.findAllByUserId(parameter.getUserId());
        if(!allByUserId.isPresent()) {
            return null;
        }

        List<LoginHistoryDto> loginHistoryDtoList = new ArrayList<>();
        for(LoginHistory x : allByUserId.get()){
            loginHistoryDtoList.add(LoginHistoryDto.of(x));
        }

        int i = 0;
        for(LoginHistoryDto x : loginHistoryDtoList) {
            x.setTotalCount(totalCount);
            x.setSeq(totalCount - parameter.getPageStart() - i);
            i++;
        }
        return loginHistoryDtoList;
    }
}