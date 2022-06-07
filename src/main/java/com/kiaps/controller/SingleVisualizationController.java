package com.kiaps.controller;

import com.kiaps.form.SingleVisualizationSearchForm;
import com.kiaps.service.SingleVisualizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * author         : Jieun Lee
 * date           : 2022/06/03
 * description    : 단일종 시각화 화면 controller
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/03        Jieun Lee          최초 생성
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/singleVisualization")
public class SingleVisualizationController {

    /* 단일종 시각화 화면 service */
    private final SingleVisualizationService singleService;

    /*
    * GET 리퀘스트 (화면 초기화)
    * @param SingleVisualizationSearchForm searchForm
    * @param Model model
    * @retuen String 화면명
    * @throws Exception 예외
    * */
    @RequestMapping(method = RequestMethod.GET)
    public String init(
            final SingleVisualizationSearchForm searchForm,
            final Model model) throws Exception {

        searchForm.setSearchDate("2021-06-15 18:00:00");
        searchForm.setSearchType("1");
        searchForm.setChannelType("ob(1)");
        searchForm.setSondeList(null);
        searchForm.setSurfaceList(null);
        searchForm.setAmsuaList(null);

        model.addAttribute(searchForm);
        model.addAttribute("title", "단일종 시각화 화면");

        return "SingleVisualization";
    }

    /*
    * POST 리퀘스트 (검색)
    * @param SingleVisualizationSearchForm searchForm
    * @param Model model
    * @return SingleVisualizationSearchForm searchForm
    * @throws Exception 예외
    * */
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody SingleVisualizationSearchForm search(
            final SingleVisualizationSearchForm searchForm,
            final Model model) throws Exception {
        
        // 검색조건
        String datetime = searchForm.getSearchDate();
        String searchType = searchForm.getSearchType();
        String channelType = searchForm.getChannelType();

        SingleVisualizationSearchForm returnForm = new SingleVisualizationSearchForm();
        // 검색조건 체크
        if(searchType.equals("1") && !channelType.equals("")) {
            // AMSU-A 검색
            returnForm = this.singleService.searchAmsua(datetime, channelType);
        } else if(searchType.equals("2")) {
            // SONDE 검색
            // TODO:연직시각화
        } else {
            // SURFACE 검색
            returnForm = this.singleService.searchSurface(datetime);
        }

        searchForm.setSurfaceList(returnForm.getSurfaceList());
        searchForm.setSondeList(returnForm.getSondeList());
        searchForm.setAmsuaList(returnForm.getAmsuaList());

        model.addAttribute(searchForm);

        return searchForm;
    }
}