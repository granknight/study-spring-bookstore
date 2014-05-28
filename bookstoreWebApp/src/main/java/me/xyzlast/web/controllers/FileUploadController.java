package me.xyzlast.web.controllers;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by ykyoon on 14. 4. 17.
 */
@Controller
public class FileUploadController {

    @RequestMapping(value = "/file/upload", method = RequestMethod.GET)
    public String getFileUploadPage() {
        return "rythm-fileupload";
    }

    @RequestMapping(value = "/file/upload", method = RequestMethod.POST)
    @ResponseBody
    public Object uploadFile(MultipartHttpServletRequest request) {
        //0. notice, we have used MultipartHttpServletRequest
        //1. get the files from the request object
        Iterator<String> itr =  request.getFileNames();
        if(itr.hasNext()) {
            MultipartFile mpf = request.getFile(itr.next());
            System.out.println(mpf.getOriginalFilename() +" uploaded!");
            try {
                //just temporary save file info into ufile
                System.out.println("file length : " + mpf.getBytes().length);
                System.out.println("file name : " + mpf.getOriginalFilename());

            } catch (IOException e) {
                // TODO Auto-generated catch block
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            return true;
        } else {
            return false;
        }
    }
}
