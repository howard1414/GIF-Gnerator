/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superpainter;

/**
 *
 * @author HALUNA
 */
public enum Status {
    active,       //待機
    drawpencil,   //畫筆工具狀態
    drawline,    //畫線工具狀態
    drawRect,   //矩形工具狀態
    drawOval,   //圓形工具狀態
    drawimage,  //圖片放置狀態 
    select,     //選取狀態
    P1resize,   //P1點重設大小狀態
    P2resize,   //P2點重設大小狀態
    P3resize,   //P3點重設大小狀態
    P4resize,   //P4點重設大小狀態
    P5resize,   //P5點重設大小狀態
    P6resize,   //P6點重設大小狀態
    P7resize,   //P7點重設大小狀態
    P8resize,   //P8點重設大小狀態
    move,     //移動狀態
}
