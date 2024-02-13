package com.zerobase.fastlms.admin.controller;

import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.model.BannerInput;
import com.zerobase.fastlms.admin.model.BannerParam;
import com.zerobase.fastlms.admin.service.BannerService;
import com.zerobase.fastlms.course.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/banner")
public class AdminBannerController extends BaseController {

    private final BannerService bannerService;

    @GetMapping("/list.do")
    public String list(Model model, BannerParam parameter) {

        parameter.init();
        List<BannerDto> banners = bannerService.list(parameter);

        long totalCount = 0;
        if(banners != null && !banners.isEmpty()){
            totalCount = banners.get(0).getTotalCount();
        }

        String queryString = parameter.getQueryString();
        String pagerHtml = getPaperHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);

        model.addAttribute("list", banners);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);

        return "admin/banner/list";
    }

    @PostMapping("/delete.do")
    public String deleteBanners(Model model,
                                HttpServletRequest request,
                                @RequestParam(name = "selectedItems[]", required = false) List<String> itemIds) {
        if (itemIds == null || itemIds.isEmpty()) {
            return "redirect:/admin/banner/list.do";
        }

        bannerService.deleteSelectedBanners(itemIds);

        return "redirect:/admin/banner/list.do";
    }

    @GetMapping("/add.do")
    public String add() {
        return "admin/banner/add";
    }

    @PostMapping("/add.do")
    public String bannerSubmit(Model model, HttpServletRequest request
            , BannerInput parameter) {

        boolean result = bannerService.register(parameter);
        model.addAttribute("result", result);

        return "admin/banner/list";
    }


}
