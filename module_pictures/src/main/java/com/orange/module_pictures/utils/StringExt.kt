package com.orange.module_pictures.utils

/**
 * created by czh on 2019/1/30
 */

fun String.getSamllUrl():String{
    return this.replace("mw600", "small")
            .replace("mw1200", "small")
            .replace("mw1024", "small")
            .replace("large","small")
}