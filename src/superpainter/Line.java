/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superpainter;
import java.awt.*;
import java.awt.image.BufferedImage;
/**
 *
 * @author HALUNA
 */
public class Line {       
    int label;
    int stack;    
    Point firstpoint;
    Point lastpoint;
    Pattern Pattern;
    Color Color;
    float BasicStroke;
    BufferedImage Image;
    
    
      Line(Point start ,Point end,Pattern pat,Color color,float BS,int sck){
          firstpoint = start;
          lastpoint = end;
          Pattern = pat;
          Color = color;
          BasicStroke = BS;
          stack = sck;
          switch(pat){
              case Pencil:
                  label=1;
                  break;
              case Line:
                  label=2;
                  break;
              case Ovil:
                  label=3;
                  break;
              case Rect:
                  label=4;
                  break;
          }
      }
      
      
      Line(Point start ,Point end,Pattern pat,BufferedImage Im,int sck){
          firstpoint = start;
          lastpoint = end;
          Pattern = pat;
          Image = Im;
          label=5;
          stack = sck;
      }
}
