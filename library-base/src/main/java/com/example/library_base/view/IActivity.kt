package com.example.library_base.view

import androidx.annotation.LayoutRes

/**
 *     ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 *     ╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 *      ╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 *       ╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 *        ██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 *        ╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/7
 * Time:19:32
 * author:yanghaoyang
 */
public interface IActivity :IView {
    @LayoutRes
    fun bindLayout():Int;
    fun initView();
    fun initData();
}