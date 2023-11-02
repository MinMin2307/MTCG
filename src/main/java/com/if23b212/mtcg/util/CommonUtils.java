package com.if23b212.mtcg.util;

import java.util.List;

public class CommonUtils {

    public boolean checkList(List list) {
        return list != null && list.size() > 0;
    }

    public boolean compareString(String s1, String s2) {
        return s1 != null && s2 != null && s1.equals(s2);
    }

    public boolean checkString(String s) {
        return  s != null && s.length() > 0;
    }


}
