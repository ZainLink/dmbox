package com.zkzy.portal.dumu.server.system.provider.task;

import com.zkzy.portal.common.utils.DateHelper;
import com.zkzy.portal.dumu.server.system.provider.mapper.mq.DmStrangerHMapper;
import com.zkzy.zyportal.system.api.entity.mq.DmStrangerH;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thinkpad-W530 on 2021/5/13.
 */
@Component
public class FileTask {


    @Value("${dumu.stranger}")
    private String stranger;

    @Autowired
    private DmStrangerHMapper dmStrangerHMapper;


    //每天凌晨执行一次 删除 超过1个月的图片文件
    @Scheduled(cron = "0 1 0 * * ? ")//cron表达式
    public void task() {
        File file = new File(stranger);
        File[] tempList = file.listFiles();
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isDirectory()) {
                String filename = tempList[i].getName();
                long a = DateHelper.getBetweenDate(filename);
                if (a > 10) {
                    FileTask.deleteFile(tempList[i]);
                }
            }
        }
        try {
            //处理数据库
            String time = DateHelper.get10DayTime();
            String param = " AND IMGSTATE='0' AND RECEIVETIME<='" + time + "' ";
            List<DmStrangerH> list = dmStrangerHMapper.selectAll(param);
            for (DmStrangerH h : list) {
                //图片过期
                h.setImgstate("1");
                dmStrangerHMapper.updateByPrimaryKey(h);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static boolean deleteFile(File dirFile) {
        // 如果dir对应的文件不存在，则退出
        if (!dirFile.exists()) {
            return false;
        }

        if (dirFile.isFile()) {
            return dirFile.delete();
        } else {

            for (File file : dirFile.listFiles()) {
                deleteFile(file);
            }
        }
        return dirFile.delete();
    }


    public static void main(String[] args) {
        String time = DateHelper.get31DayTime();
        String param = " AND IMGSTATE='0' AND RECEIVETIME<='" + time + "' ";
        System.out.println(param);
    }
}
