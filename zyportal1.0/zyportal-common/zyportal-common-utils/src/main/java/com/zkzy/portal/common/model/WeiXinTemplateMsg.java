package com.zkzy.portal.common.model;

import java.util.TreeMap;

/**
 * Created by WH on 2018/12/3.
 */
public class WeiXinTemplateMsg {

        private String touser;

        private String template_id;

        private String url;

        private TreeMap<String, String> miniprogram;

        private TreeMap<String, TreeMap<String,String>> data;



        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTouser() {
            return touser;
        }

        public void setTouser(String touser) {
            this.touser = touser;
        }


        public String getTemplate_id() {
            return template_id;
        }

        public void setTemplate_id(String template_id) {
            this.template_id = template_id;
        }

        public TreeMap<String, String> getMiniprogram() {
            return miniprogram;
        }

        public void setMiniprogram(TreeMap<String, String> miniprogram) {
            this.miniprogram = miniprogram;
        }

        public TreeMap<String, TreeMap<String, String>> getData() {
            return data;
        }

        public void setData(TreeMap<String, TreeMap<String, String>> data) {
            this.data = data;
        }

        /**
         * 参数
         * @param value
         * @param color 可不填
         * @return
         */
        public static TreeMap<String, String> item(String value, String color) {
            TreeMap<String, String> params = new TreeMap<String, String>();
            params.put("value", value);
            params.put("color", color);
            return params;
        }


}
