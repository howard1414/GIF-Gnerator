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
    select,
    resize,
    move,
}
