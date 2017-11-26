package com.taotao.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.*;

/**
 * Created by ASUS on 2017/11/24.
 */
public class FreeMarkerTest {
    @Test
    public void  testFreeMarker() throws Exception{
        //创建一个configuration对象
        Configuration configuration = new Configuration(Configuration.getVersion());
        //告诉configuration对象模板文件存放的路径
        configuration.setDirectoryForTemplateLoading(new File("D:\\github\\taotao\\taotao-portal\\src\\main\\webapp\\WEB-INF\\ftl"));
        //设置config的默认字符集，utf-8
        configuration.setDefaultEncoding("utf-8");
        //从config对象中获得模板对象，需要指定一个模板文件的名字
        Template template = configuration.getTemplate("first.ftl");
        //创建模板需要的数据集，可以是一个map对象也可以是一个pojo，把模板需要的数据放入数据集
        Map root = new HashMap<>();
        root.put("hello","hello freemarker");
        //创建一个writer对象，指定生成的文件保存的路径及文件名
        Writer out = new FileWriter(new File("D:\\github\\html\\hello.html"));
        //调用模板对象的process方法生成静态文件，需要两个参数数据集和writer对象
        template.process(root,out);
        //关闭writer对象
        out.flush();
        out.close();


    }
   public class Student{
        private int id;
        private String name;
        private String address;

        public Student(int id,String name,String address){
            this.id = id;
            this.name = name;
            this.address = address;
        }
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
    @Test
    public void  testFreeMarker2() throws Exception{
        //创建一个configuration对象
        Configuration configuration = new Configuration(Configuration.getVersion());
        //告诉configuration对象模板文件存放的路径
        configuration.setDirectoryForTemplateLoading(new File("D:\\github\\taotao\\taotao-portal\\src\\main\\webapp\\WEB-INF\\ftl"));
        //设置config的默认字符集，utf-8
        configuration.setDefaultEncoding("utf-8");
        //从config对象中获得模板对象，需要指定一个模板文件的名字
        Template template = configuration.getTemplate("second.ftl");
        //创建模板需要的数据集，可以是一个map对象也可以是一个pojo，把模板需要的数据放入数据集
        Map root = new HashMap<>();
        root.put("hello","first中的hello");
       // root.put("title","hello freemarker");
        root.put("student",new Student(1,"张三","北京"));
        List<Student> stuList = new ArrayList<>();
        stuList.add(new Student(1,"张三","北京"));
        stuList.add(new Student(2,"张三2","北京2"));
        stuList.add(new Student(3,"张三3","北京3"));
        stuList.add(new Student(4,"张三4","北京4"));
        stuList.add(new Student(5,"张三5","北京5"));
        root.put("students",stuList);
       // root.put("curdate",new Date()); 没有值的时候
        //创建一个writer对象，指定生成的文件保存的路径及文件名
        Writer out = new FileWriter(new File("D:\\github\\html\\second.html"));
        //调用模板对象的process方法生成静态文件，需要两个参数数据集和writer对象
        template.process(root,out);
        //关闭writer对象
        out.flush();
        out.close();


    }
}
