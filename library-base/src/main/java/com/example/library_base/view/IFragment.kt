package com.example.library_base.view

import android.view.View
import androidx.annotation.IdRes

/**
 *     ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 *     ╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 *      ╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 *       ╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 *        ██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 *        ╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/7
 * Time:19:33
 * author:yanghaoyang
 */
public interface IFragment :IActivity{
    fun <T : View?> findViewById(@IdRes id : Int):T;

}