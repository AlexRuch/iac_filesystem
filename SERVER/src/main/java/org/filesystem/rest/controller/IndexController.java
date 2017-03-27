package org.filesystem.rest.controller;

import org.filesystem.rest.dataForClient.FileData;
import org.filesystem.rest.dataForClient.PathData;
import org.filesystem.rest.entity.PathEntity;
import org.filesystem.rest.service.FileEntityService;
import org.filesystem.rest.service.PathEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class IndexController {

    @Autowired
    PathEntityService pathService;

    @Autowired
    FileEntityService fileEntityService;

    @RequestMapping(value = "/path", method = RequestMethod.GET)
    @ResponseBody
    public List<PathData> showDirData(HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin", "*");
        System.out.println(pathService.getAllPath().size());

        return pathService.getAllPath();
    }


    @RequestMapping(value = "/newpath", method = RequestMethod.POST)
    @ResponseBody
    public String addPath(HttpServletResponse response, @RequestBody String path) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return pathService.addPath(path);
    }

    @RequestMapping(value = "/files", method = RequestMethod.POST)
    @ResponseBody
    public List<FileData> showFiles(HttpServletResponse response, @RequestBody String path_id) {
        System.out.println(path_id);
        response.setHeader("Access-Control-Allow-Origin", "*");
        return fileEntityService.getByPath(Integer.parseInt(path_id));
    }
}
