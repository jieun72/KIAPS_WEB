package com.kiaps.controller;

import com.kiaps.service.ObsVisualizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    * @param Model model
    * @retuen String 화면명
    * @throws Exception 예외
    * */
    @RequestMapping(method = RequestMethod.GET)
    public String init(final Model model) throws Exception {

        model.addAttribute("title", "이종관측 시각화 화면");

        return "ObsVisualization";
    }

    /*
    * POST 리퀘스트 (검색)
    * @param Model model
    * @param
    * @return String 화면명
    * @throws Exception 예외
    * */
    @RequestMapping(method = RequestMethod.POST)
    public String search(final Model model) throws Exception {

        List<String> list = this.obsService.searchObsVisualization();
        System.out.print(list.get(0));

        return "ObsVisualization";
    }
}