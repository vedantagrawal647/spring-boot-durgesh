package com.durgesh.durgesh3_BookRestAPI.helper;


import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadHelper {

    //static path
    // public final String UPLOAD_DIR="C:\\Users\\vedant.agrawal_spice\\Documents\\Spring Boot Durgesh\\durgesh3_BookRestAPI\\src\\main\\resources\\static\\images";

    //dynamic path
    public final String UPLOAD_DIR = new ClassPathResource("static/images/").getFile().getAbsolutePath();

    public FileUploadHelper() throws IOException{

    }
    public boolean uploadFile(MultipartFile multipartFile)
    {
        boolean f = false;

        try{

            //
           // InputStream is = multipartFile.getInputStream();
           // byte data[] =new byte[is.available()];
           // is.read(data);

           //  write
           //  FileOutputStream fos = new FileOutputStream(UPLOAD_DIR + "\\"+multipartFile.getOriginalFilename());
           //  fos.write(data);
           //  fos.close();

            //Files.copy(in,target,options);
            Files.copy(
                    multipartFile.getInputStream(),
                    Paths.get(UPLOAD_DIR+ File.separator+multipartFile.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING
            );

             f=true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return f;
    }


}
