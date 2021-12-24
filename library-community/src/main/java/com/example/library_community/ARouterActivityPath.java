package com.example.library_community;

/**
 * _ ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * __╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ___╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ____╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * _____██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * _____╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/17
 * Time:19:43
 * author:YangHaoYang
 * Package com.example.library_community
 */
public class ARouterActivityPath {
    public final static class Pay{
        private static final String PAY = "/Pay";
        public static final String PAY_HOME = PAY+"/Pay";
    }
    public final static class login{
        private static final String LOGIN = "/Login";
        public static final String LOGIN_HOME = LOGIN+"/Login";
    }
    public final static class mine{
        private static final String MINE = "/Mine";
        public static final String MINE_HOME = MINE+"/Mine";
    }
}
