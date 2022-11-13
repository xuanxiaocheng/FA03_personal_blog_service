package app.example.demo.controller;

import java.io.*;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import app.example.demo.Service.FileService;
import javax.servlet.http.HttpServletResponse;

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

    @PostMapping("/upload")
    protected String uploadPhoto(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf('.'));
        String newFileName = new Date().getTime() + suffix;
        String path = "E:/";
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


    @GetMapping("/copyTextBtye")
    protected void copyTextBtye() throws IOException {
        InputStream in = new FileInputStream("E:/a.txt");
        OutputStream out = new FileOutputStream("E:/b.txt");
        int num = 0;
        byte[] byteSet = new byte[100];
        while ((num = in.read(byteSet)) != -1) {
            out.write(byteSet, 0, num);
        }
        out.close();
        in.close();
    }


    @GetMapping("/copyTextStr")
    protected void copyTextStr() throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(new FileInputStream("E:/a.txt")));
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("E:/b.txt"));
        BufferedWriter bw = new BufferedWriter(osw);
        String str = "";
        while ((str = rd.readLine()) != null) {
            System.out.println(str);
            bw.write(str);
        }
        bw.close();
        rd.close();
    }

}
