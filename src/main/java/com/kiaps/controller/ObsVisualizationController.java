package com.kiaps.controller;

import com.kiaps.form.ObsVisualizationSearchForm;
import com.kiaps.service.ObsVisualizationService;
import com.kiaps.vo.ResponseSondeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

/**
 * author         : Jieun Lee
 * date           : 2022/05/17
 * description    : 이종관측 시각화 화면 controller
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/05/17        Jieun Lee          최초 생성
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/obsVisualization")
public class ObsVisualizationController {

    /* 이종관측 시각화 화면 service */
    private final ObsVisualizationService obsService;

    /*
    * GET 리퀘스트 (화면 초기화)
    * @param ObsVisualizationSearchForm searchForm
    * @param Model model
    * @retuen String 화면명
    * @throws Exception 예외
    * */
    @RequestMapping(method = RequestMethod.GET)
    public String init(
            final ObsVisualizationSearchForm searchForm,
            final Model model) throws Exception {

        searchForm.setSearchDate("2020-06-01 06:00:00");
        searchForm.setSearchType("1");
        searchForm.setSondeList(null);
        searchForm.setSurfaceList(null);
        searchForm.setSurfaceList2(null);

        model.addAttribute(searchForm);
        model.addAttribute("title", "이종관측 시각화 화면");

        return "ObsVisualization";
    }

    /*
    * POST 리퀘스트 (검색)
    * @param ObsVisualizationSearchForm searchForm
    * @param Model model
    * @return ObsVisualizationSearchForm searchForm
    * @throws Exception 예외
    * */
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody ObsVisualizationSearchForm search(
            final ObsVisualizationSearchForm searchForm,
            final Model model) throws Exception {
        
        // 검색조건
        String datetime = searchForm.getSearchDate();

        ObsVisualizationSearchForm returnForm = this.obsService.searchObsVisualization(datetime);

        searchForm.setSurfaceList(returnForm.getSurfaceList());
        searchForm.setSondeList(returnForm.getSondeList());
        searchForm.setSurfaceList2(returnForm.getSurfaceList2());

        model.addAttribute(searchForm);

        return searchForm;
    }
}