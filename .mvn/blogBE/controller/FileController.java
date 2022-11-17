package app.example.blogBE.controller;

import java.io.*;
import java.util.Date;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import app.example.blogBE.service.FileService;
import javax.servlet.http.HttpServletResponse;

@Api(tags = "文件管理")
@RestController()
public class FileController {

    @Autowired
    FileService fileService;

    @GetMapping("/file/img/{imgUrl:[a-zA-Z0-9_.]+}")
    protected void getquestion(HttpServletResponse response, @PathVariable("imgUrl") String imgUrl) throws Exception {
        File directory = new File("");
        String path = directory.getAbsolutePath() + "/file/img/" + imgUrl;
        System.out.println(path);
        fileService.byteStream(path, response);
    }

    @PostMapping("manage/upload")
    protected String uploadPhoto(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf('.'));
        String newFileName = new Date().getTime() + suffix;
        File directory = new File("");
        String path = directory.getAbsolutePath()+"/file/img";
        File newFile = new File(path + newFileName);
        try {
            file.transferTo(newFile);
            return "成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "失败";
        }
    }

    @GetMapping("/path")
    protected String getPath() throws IOException {
        File directory = new File("");
        String path = directory.getAbsolutePath();
        return path;
    }

}
