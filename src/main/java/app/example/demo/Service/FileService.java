package app.example.demo.Service;

import org.springframework.stereotype.Service;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Service
public class FileService {

    public void byteStream(String path, HttpServletResponse response) throws Exception
    {
        FileInputStream in = new FileInputStream(path);
        //创建一个输出流到浏览器
        ServletOutputStream out = response.getOutputStream();
        byte[] buffer = new byte[1024];
        while ((in.read(buffer)) != -1) {
            out.write(buffer);
        }
        in.close();
    }
}
