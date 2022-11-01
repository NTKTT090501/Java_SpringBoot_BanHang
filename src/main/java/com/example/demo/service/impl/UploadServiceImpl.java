package com.example.demo.service.impl;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.UploadService;

@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    ServletContext app;

    @Override
    public File save(MultipartFile file, String folder) {
    	Path staticPath = Paths.get("static");
        File dir = new File(app.getRealPath("/assets/pages/img/" + folder));
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String s = System.currentTimeMillis() + file.getOriginalFilename();
        String name = Integer.toHexString(s.hashCode()) + s.substring(s.lastIndexOf("."));
        try {
        	FileCopyUtils.copy(file.getBytes(), new File("C:/Users/84334/eclipse-workspace/Oganic/src/main/resources/static/assets/pages/img/products",name));
            File saveFile = new File(dir, name);
            file.transferTo(saveFile);
            System.out.println(s);
            return saveFile;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param file
     * @param folder
     * @return
     */

}
