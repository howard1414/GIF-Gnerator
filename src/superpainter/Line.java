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
    Point firstpoint;
    Point lastpoint;
    Pattern Pattern;
    Color Color;
    float BasicStroke;
    BufferedImage Image;
      Line(Point start ,Point end,Pattern pat,Color color,float BS){
          firstpoint = start;
          lastpoint = end;
          Pattern = pat;
          Color = color;
          BasicStroke = BS;
          
      }
      Line(Pattern pat,BufferedImage Im){
          int x,y;
          Pattern = pat;
          Image = Im;
      }
}
