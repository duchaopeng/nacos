package com.example.demo.csv;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CSVUtil {
    private static final Logger log = Logger.getLogger(CSVUtil.class);

    public static File createCSVFile(List<Map<String, Object>> exportData,
                                     String outPutPath, String filename) {

        File csvFile = null;
        BufferedWriter csvFileOutputStream = null;
        try {
            csvFile = new File(outPutPath + filename + ".csv");
            // csvFile.getParentFile().mkdir();
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();

            // GB2312使正确读取分隔符","
            csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(csvFile), "UTF-8"), 1024);
            /*            new FileOutputStream(csvFile), "GB2312"), 1024);
             */         // 写入文件头部
            Map<String, Object> map1 = exportData.get(0);
            if (map1 != null && map1.size() > 0) {
                int num = map1.keySet().size();
                int j = 0;
                for (String key : map1.keySet()) {
                    // 第六步，创建单元格，并设置值
                    csvFileOutputStream.write("\""
                            + key.toString() + "\"");
                    ++j;
                    if (j != num) {
                        csvFileOutputStream.write(",");
                    }
                }
            }
            csvFileOutputStream.newLine();
            // 写入文件内容
            for (Iterator iterator = exportData.iterator(); iterator.hasNext(); ) {
                // Object row = (Object) iterator.next();
                LinkedHashMap row = (LinkedHashMap) iterator.next();
                System.out.println(row);

                for (Iterator propertyIterator = row.entrySet().iterator(); propertyIterator.hasNext(); ) {
                    java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator.next();
                    // System.out.println( BeanUtils.getProperty(row, propertyEntry.getKey().toString()));
                    csvFileOutputStream.write("\""
                            + propertyEntry.getValue().toString() + "\"");
                    if (propertyIterator.hasNext()) {
                        csvFileOutputStream.write(",");
                    }
                }
                if (iterator.hasNext()) {
                    csvFileOutputStream.newLine();
                }
            }
            csvFileOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvFileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csvFile;
    }

    /*************************************************************
     生成单个CSV文件的方法
     *************************************************************/
    /*
     * List<Map<String, Object>> Map里面为 表头 数据 例：Map<"姓名","张三">
     * filename 文件名
     */
    public static String createCSVFileUrl(List<Map<String, Object>> exportData,
                                          String filename) {
        log.info("开始生成csv文件");
        File csvFile = null;
        String PATH = "";
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        BufferedWriter csvFileOutputStream = null;
        try {
            //filepath 文件路径 ：本地就写   C://Users//A//Downloads//  服务器就写：/app/file/
            PATH = "E://test//" + filename + ".csv";
            fos = new FileOutputStream(PATH);
            //追加BOM标识 不加会导致office低版本打开乱码
            fos.write(0xef);
            fos.write(0xbb);
            fos.write(0xbf);
            osw = new OutputStreamWriter(fos, "UTF-8");
            csvFileOutputStream = new BufferedWriter(osw);
            csvFile = new File(PATH);
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();
            log.info(PATH);
            // GB2312使正确读取分隔符","
            /*csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(csvFile), "UTF-8"), 1024);
            csvFileOutputStream.write(0xef);
            csvFileOutputStream.write(0xbb);
            csvFileOutputStream.write(0xbf);*/
            // 写入文件头部
            Map<String, Object> map1 = exportData.get(0);
            if (map1 != null && map1.size() > 0) {
                int num = map1.keySet().size();
                int j = 0;
                for (String key : map1.keySet()) {
                    // 第六步，创建单元格，并设置值
                    csvFileOutputStream.write("\"" + key.toString() + "\"");
                    ++j;
                    if (j != num) {
                        csvFileOutputStream.write(",");
                    }
                }
            }
            csvFileOutputStream.newLine();
//            int i = 0 ;
            // 写入文件内容
            for (Iterator iterator = exportData.iterator(); iterator.hasNext(); ) {
                // Object row = (Object) iterator.next();
                LinkedHashMap row = (LinkedHashMap) iterator.next();
//                i++;
                //System.out.println(row);
//                if (i%10000 == 0) {
//                    System.out.println("正在写第"+i+"条数据！");
//                }
                for (Iterator propertyIterator = row.entrySet().iterator(); propertyIterator.hasNext(); ) {
                    java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator.next();
                    // System.out.println( BeanUtils.getProperty(row, propertyEntry.getKey().toString()));
                    csvFileOutputStream.write("\""
                            + propertyEntry.getValue().toString() + "\"");
                    if (propertyIterator.hasNext()) {
                        csvFileOutputStream.write(",");
                    }
                }
                if (iterator.hasNext()) {
                    csvFileOutputStream.newLine();
                }
            }
            csvFileOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvFileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.info("生成csv文件结束");
        //exportObeEventDataExcel(response,csvFile);
//        List<String> list = new ArrayList<String>();
//        list.add(filepath + filename + ".csv");
//        try {
//            zipFiles(list,filepath+"csv.zip",response);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        if (csvFile.exists() && csvFile.isFile())
//        {
//            csvFile.delete();
//        }
        //返回的是文件的保存地址
        return PATH;
    }

    /*
     * 页面响应方法
     * */
    public static String exportObeEventDataExcel(HttpServletResponse response, File csvFile) {
        try {
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(csvFile));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(
                    csvFile.getName(), "UTF-8"));
            response.addHeader("Content-Length", "" + csvFile.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");

            //toClient.write(new byte []{(byte ) 0xEF ,( byte ) 0xBB ,( byte ) 0xBF });
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
            return "成功";
        } catch (IOException e) {
            String message = "export ObeEvent Data Excel failed . ";
            log.error(message, e);
            return "失败";
        }
    }

    /*
     * 页面ZIP响应方法，其实和上面一样
     */
    public static String exportObeEventDataExcelZip(HttpServletResponse response, File csvFile) {
        try {
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(csvFile));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(
                    csvFile.getName(), "UTF-8"));
            response.addHeader("Content-Length", "" + csvFile.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            //toClient.write(new byte []{(byte ) 0xEF ,( byte ) 0xBB ,( byte ) 0xBF });
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
            return "成功";
        } catch (IOException e) {
            String message = "export ObeEvent Data Excel failed . ";
            log.error(message, e);
            return "失败";
        }
    }

    /**
     * @param fileRealPathList 待压缩的文件列表
     * @param zipFileRealPath  压缩后的文件名称
     * @return boolean
     * @throws :Exception
     * @Function: zipFiles
     * @Description:多个文件的ZIP压缩
     */
    public static void zipFiles(List<String> fileRealPathList, String zipFileRealPath,
                                HttpServletResponse response)
            throws IOException {
        FileOutputStream out = null;
        ZipOutputStream zipOut = null;
        String path = "C://test//" + zipFileRealPath + ".zip";
        try {
            // 根据文件路径构造一个文件实例
            File zipFile = new File(path);
            // 判断目前文件是否存在,如果不存在,则新建一个
            if (!zipFile.exists()) {
                zipFile.createNewFile();
            }
            // 根据文件路径构造一个文件输出流
            out = new FileOutputStream(path);
            // 传入文件输出流对象,创建ZIP数据输出流对象
            zipOut = new ZipOutputStream(out);
            // 循环待压缩的文件列表
            for (String fileRealPath : fileRealPathList) {
                FileInputStream in = null;
                try {
                    File file = new File(fileRealPath);
                    if (!file.exists()) {
                        log.error("文件不存在");
                        throw new FileNotFoundException("文件不存在");
                    }

                    // 创建文件输入流对象
                    in = new FileInputStream(fileRealPath);
                    // 得到当前文件的文件名称
                    //判断操作系统
                    String separateCharacter = "";
                    String os = System.getProperty("os.name");
                    if (os.toLowerCase().startsWith("win")) {
                        //windows操作系统
                        separateCharacter = "//";
                    } else {
                        //非windows操作系统
                        separateCharacter = "/";
                    }
                    String fileName = fileRealPath.substring(
                            fileRealPath.lastIndexOf(separateCharacter) + 1, fileRealPath.length());
                    // 创建指向压缩原始文件的入口
                    ZipEntry entry = new ZipEntry(fileName);
                    zipOut.putNextEntry(entry);
                    // 向压缩文件中输出数据
                    int nNumber = 0;
                    byte[] buffer = new byte[512];
                    while ((nNumber = in.read(buffer)) != -1) {
                        zipOut.write(buffer, 0, nNumber);
                    }
                } catch (IOException e) {
                    log.error("文件压缩异常-in，原因：", e);
                    throw new IOException("文件压缩异常");
                } finally {
                    // 关闭创建的流对象
                    if (null != in) {
                        in.close();
                    }
                }
            }
        } catch (IOException e) {
            log.error("文件压缩异常-out，原因：", e);
            throw new IOException("文件压缩异常");
        } finally {
            if (null != zipOut) {
                zipOut.close();
            }
            if (null != out) {
                out.close();
            }
        }
        File fiel = new File(path);
        //调用导出到前端的方法
        exportObeEventDataExcelZip(response, fiel);
        //删除本地压缩包
        if (fiel.exists() && fiel.isFile()) {
            fiel.delete();
        }
        //删除其他文件
        for (String string : fileRealPathList) {
            File fiel1 = new File(string);
            //删除本地压缩包
            if (fiel1.exists() && fiel1.isFile()) {
                fiel1.delete();
            }
        }
    }

    public static void main(String[] args) {
        List exportData = new ArrayList<Map>();
        Map row1 = new LinkedHashMap<String, String>();
        row1.put("11", "11");
        row1.put("21", "12");
        row1.put("31", "13");
        row1.put("41", "14");
        exportData.add(row1);
        row1 = new LinkedHashMap<String, String>();
        row1.put("11", "21");
        row1.put("2", "22");
        row1.put("31", "23");
        row1.put("4", "24");
        exportData.add(row1);
        List propertyNames = new ArrayList();
        CSVUtil.createCSVFile(exportData, "C:\\Users\\A\\Downloads\\", "导出CSV文件");
    }
}
