package com.example.library_community;

/**
 * ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * ╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * ██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * ╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/7
 * Time:18:10
 * author:yanghaoyang
 */
public class ARouterPath {
    public final static class Home{
        private static final String HOME = "/home";
        public static final String PAGER_HOME = HOME+"/Home";
    }
    public final static class principal{
        private static final String PRINCIPAl = "/principal";
        public static final String PAGER_PRINCIPAl = PRINCIPAl+"/Principal";
    }
    public final static class shopping{
        private static final String SHOPPING = "/shopping";
        public static final String PAGER_SHOPPING = SHOPPING+"/Shopping";
    }
    public final static class tidings{
        private static final String TIDINGS = "/tidings";
        public static final String PAGER_TIDINGS = TIDINGS+"/Tidings";
    }
    public static class classify{
        private static final String CLASSIFY = "/classify";
        public static final String PAGER_CLASSIFY = CLASSIFY+"/Classify";
    }

}
