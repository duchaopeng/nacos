package com.example.demo.controller.portal;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.controller.CommonController;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 文件上传下载
 */
@Controller
public class FileController extends CommonController {
    /**
     * 下载
     */
    @RequestMapping(value = "/downFile", produces = {"image/jpg;charset=UTF-8"})
    protected byte[] downFile(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "a.jpg";
//        String realPath = request.getSession().getServletContext().getRealPath("");
        String realPath = "D:\\file\\";
        String source = realPath + fileName;

        FileInputStream workInputStream = null;
        ByteArrayOutputStream imageOutputStream = null;
        try {
            response.setContentType("image/jpg");
            response.setHeader("Content-Type", "image/jpg");
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
            workInputStream = new FileInputStream(source);
            imageOutputStream = new ByteArrayOutputStream();
            return imageOutputStream.toByteArray();
        } catch (Exception e) {
            return null;
        } finally {
            IOUtils.closeQuietly(workInputStream);
            IOUtils.closeQuietly(imageOutputStream);
        }
    }

    /**
     * 图片上传 返回路径
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile uploadfile, HttpServletRequest request) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        JSONObject jsonData = new JSONObject();
        try {
            if (uploadfile.isEmpty()) {
                jsonData.put("code", "2");
                jsonData.put("msg", "未检测到图片");
                return jsonData.toJSONString();
            }
            String originalName = uploadfile.getOriginalFilename();
            if (StringUtils.isEmpty(originalName)) {
                jsonData.put("code", "4");
                jsonData.put("msg", "文件名不能为空");
                return jsonData.toJSONString();
            }
            String suffix = originalName.substring(originalName.lastIndexOf("."));
            String suffixs = ".bmp.jpg.png.BMP.JPG.PNG.jpeg";
            BufferedImage image = ImageIO.read(uploadfile.getInputStream());
            if (!suffixs.contains(suffix)) {

                jsonData.put("code", "0");
                jsonData.put("msg", "图片格式有误");
                return jsonData.toJSONString();
            }
            if (image.getWidth() != 200) {
                jsonData.put("code", "2");
                jsonData.put("msg", image.getWidth() + "");
                return jsonData.toJSONString();
            }
            if (image.getHeight() != 200) {
                jsonData.put("code", "21");
                jsonData.put("msg", image.getHeight() + "");
                return jsonData.toJSONString();
            }
            if (uploadfile.getSize() > 1024 * 1000) {
                jsonData.put("code", "3");
                jsonData.put("msg", "图片的大小不能超过10k");
                return jsonData.toJSONString();
            }
            StringBuilder fileName = new StringBuilder(sdf.format(new Date()));
            fileName.append("-").append(UUID.randomUUID().toString().replace("-", "")).append(suffix);
            // uuid 生成路径名
            String fileSavePath = "E:\\file";
            File file = new File(fileSavePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            // 存到服务器
            String imgRealPath = fileSavePath + fileName.toString();
            File imageFile = new File(imgRealPath);
            uploadfile.transferTo(imageFile);
            jsonData.put("code", "1");
            jsonData.put("msg", "上传成功");
            jsonData.put("fileUrl", imgRealPath);
            //
            return jsonData.toJSONString();

            //
        } catch (Exception e) {
            jsonData.put("code", "5");
            jsonData.put("msg", "图片上传失败");
            return jsonData.toJSONString();
        }
    }
}
