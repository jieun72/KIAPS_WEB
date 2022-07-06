package com.kiaps.controller;

import com.kiaps.form.StatusComparisonSearchForm;
import com.kiaps.service.StatusComparisonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * author         : Jieun Lee
 * date           : 2022/05/24
 * description    : 상태비교 화면 controller
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/05/24        Jieun Lee          최초 생성
 * 2022/06/23        Jieun Lee          AI QC, KPOP QC 검색기능 구현
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/statusComparison")
public class StatusComparisonController {

    /* 상태비교 화면 service */
    private final StatusComparisonService stcService;

    /*
    * GET 리퀘스트 (화면 초기화)
    * @param StatusComparisonSearchForm searchForm
    * @param Model model
    * @retuen String 화면명
    * @throws Exception 예외
    * */
    @RequestMapping(method = RequestMethod.GET)
    public String init(
            final StatusComparisonSearchForm searchForm,
            final Model model) throws Exception {

        searchForm.setFromDate("2021-06-16 00:00:00");
        searchForm.setToDate("2021-07-28 00:00:00");
        searchForm.setSurfaceList(null);
        searchForm.setSurfaceList2(null);

        model.addAttribute(searchForm);
        model.addAttribute("title", "상태비교 화면");

        return "StatusComparison";
    }

    /*
     * POST 리퀘스트 (검색)
     * @param StatusComparisonSearchForm searchForm
     * @param Model model
     * @return StatusComparisonSearchForm searchForm
     * @throws Exception 예외
     * */
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    StatusComparisonSearchForm search(
            final StatusComparisonSearchForm searchForm,
            final Model model) throws Exception {

        // 검색조건
        String fromDate = searchForm.getFromDate();
        String toDate = searchForm.getToDate();

        StatusComparisonSearchForm returnForm = this.stcService.searchStatusComparison(fromDate, toDate);

        searchForm.setSondeTotalList(returnForm.getSondeTotalList());
        searchForm.setAiQCList(returnForm.getAiQCList());
        searchForm.setKpopQCList(returnForm.getKpopQCList());
        searchForm.setSondeOnlyKpopList(returnForm.getSondeOnlyKpopList());

        model.addAttribute(searchForm);

        return searchForm;
    }
}