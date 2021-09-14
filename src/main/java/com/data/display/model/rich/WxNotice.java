package com.data.display.model.rich;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname WxNotice
 * @Description TODO
 * @Date 2019/6/22 14:17
 * @Created by Administrator
 */
public class WxNotice {

    String openid;
    String template_id;
    String form_id;

    List<KeyWord> keyWords;

    Map data;

    public WxNotice() {
        data = new HashMap();
        keyWords = new ArrayList<>();
    }

    public void addKeyWords(KeyWord keyWord){
        keyWords.add(keyWord);
    }

    public void addKeyWords(String value,String color){
        keyWords.add(new KeyWord(value,color));
    }

    public Map getData() {
        final int size = keyWords.size();
        for (int i = 0; i < size; i++) {
            int j = i + 1;
            data.put("keyword"+j,keyWords.get(i));
        }
        return data;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getForm_id() {
        return form_id;
    }

    public void setForm_id(String form_id) {
        this.form_id = form_id;
    }


    class KeyWord{
        String value;
        String color;

        public KeyWord(String value, String color) {
            this.value = value;
            this.color = color;
        }

        public KeyWord() {
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }

}
