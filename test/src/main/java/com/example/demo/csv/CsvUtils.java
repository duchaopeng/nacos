package com.example.demo.csv;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class CsvUtils {
    private static final Logger logger = LogManager.getLogger(CsvUtils.class);


    /**
     * 解析csv文件并转成bean（方法二）
     *
     * @param file csv文件
     * @return 数组
     */
    public static List<String[]> getCsvDataMethod2(MultipartFile file) {

        List<String[]> list = new ArrayList<String[]>();
        int i = 0;
        try {
            CSVReader csvReader = new CSVReaderBuilder(
                    new BufferedReader(
                            new InputStreamReader(file.getInputStream(), "utf-8"))).build();
            Iterator<String[]> iterator = csvReader.iterator();
            while (iterator.hasNext()) {
                String[] next = iterator.next();
                //去除第一行的表头，从第二行开始
                if (i >= 1) {
                    list.add(next);
                }
                i++;
            }
            return list;
        } catch (Exception e) {
            System.out.println("CSV文件读取异常");
            return list;
        }
    }


    /**
     * 解析csv文件并转成bean（方法三）
     *
     * @param file  csv文件
     * @param clazz 类
     * @param <T>   泛型
     * @return 泛型bean集合
     */
    public static <T> List<T> getCsvDataMethod3(MultipartFile file, Class<T> clazz) {
        InputStreamReader in = null;
        CsvToBean<T> csvToBean = null;
        try {
            in = new InputStreamReader(file.getInputStream(), "utf-8");
            HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(clazz);
            csvToBean = new CsvToBeanBuilder<T>(in).withMappingStrategy(strategy).build();
        } catch (Exception e) {
            logger.error("数据转化失败");
            return null;
        }
        return csvToBean.parse();
    }


    /**
     * 解析csv文件并转成bean（方法一）
     *
     * @param file
     * @return
     */
    public static List<CsvFile> getCsvDataMethod1(MultipartFile file) {
        ArrayList<CsvFile> csvFileList = new ArrayList<>();

        InputStreamReader in = null;
        String s = null;
        try {
            in = new InputStreamReader(file.getInputStream(), "utf-8");
            BufferedReader bufferedReader = new BufferedReader(in);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split(",");
                CsvFile csvFile = new CsvFile();
                csvFile.setName(splitResult(split[0]));
                csvFile.setTitle(splitResult(split[1]));
                csvFile.setNumber(splitResult(split[2]));
                csvFile.setType(splitResult(split[3]));
                csvFile.setPersonnel(splitResult(split[4]));
                csvFile.setTime(splitResult(split[5]));
                csvFileList.add(csvFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvFileList;
    }

    private static String splitResult(String once) {
        String result = "";
        for (int i = 0; i < once.length(); i++) {
            if (once.charAt(i) != '"') {
                result += once.charAt(i);
            }
        }
        return result;
    }
}