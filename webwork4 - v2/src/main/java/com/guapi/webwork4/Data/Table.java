package com.guapi.webwork4.Data;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

@Data
public class Table implements Serializable {

    private Vector<ContactInfor> tableinfo;

    public Table() {
        tableinfo = new Vector<ContactInfor>();
        tableinfo.add(new ContactInfor("张三", "13612130234", "17341933@qq.com"
                , "北京市海淀区红联北村", "17341933", null));
        tableinfo.add(new ContactInfor("李四", "13412219418", "sdfsdf@126.com"
                , "北京市西城区新街口外大街", "472810191", null));
        tableinfo.add(new ContactInfor("王五", "13378919111", "fdh123@163.com"
                , "北京市昌平区顺沙北路", "67123891", null));
        tableinfo.add(new ContactInfor("瓜皮", "13161109663", "1838831597@qq.com"
                , "北京市昌平区顺沙路19号院", "1838831597", null));
    }
}
