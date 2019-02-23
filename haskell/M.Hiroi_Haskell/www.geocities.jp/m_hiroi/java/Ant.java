//
// ƒ`ƒ‡ƒƒAƒŠ‚P
//
//                Copyright (C) 2001 by M.Hiroi
//
// M.Hiroi's Home Page
// URL   : http://www.geocities.co.jp/SiliconValley-Oakland/1680/
// E-Mail: m_hiroi@geocities.co.jp
//

import java.awt.*;
import java.applet.*;
import java.util.*;

public class Ant extends Applet implements Runnable
{
  // •Ï”’è‹`
  final int SIZE = 100;
  byte plane[] = new byte[SIZE * SIZE];
  int dir = 0, x = 50, y = 50, width, height, edge;
  Image double_buffer;
  Graphics dg;
  Thread trig = null;

  // ‰Šú‰»
  public void init(){
    trig   = new Thread( this );
    width  = getSize().width;
    height = getSize().height;
    double_buffer = createImage( width, height );
    dg = double_buffer.getGraphics();
    edge = width / SIZE;

    // ‰æ–Ê‚Ì‰Šú‰»
    dg.setColor( Color.black );
    dg.fillRect( 0, 0, width, height );

    // •½–Ê‚Ì‰Šú‰»
    for( int i = 0; i < (SIZE * SIZE); i++ ){
      plane[i] = 0;
    }

    trig.start();
  }

  public void paint( Graphics g ){
    g.drawImage( double_buffer, 0, 0, null );
  }

  public void update( Graphics g ){
    paint( g );
  }

  public void run(){
    while( true ){
      try {
	Thread.sleep( 20 );
      } catch( InterruptedException e ){}
      doIt();
      doIt();
      doIt();
      doIt();
      repaint();
    }
  }

  void doIt(){
    int postion = y * SIZE + x;
    if( plane[postion] == 0 ){
      dir = (dir + 1) % 4;
      dg.setColor( Color.red );
      plane[postion] = 1;
    } else {
      dir = (dir + 3) % 4;
      dg.setColor( Color.black );
      plane[postion] = 0;
    }
    dg.fillRect( x * edge, y * edge, edge, edge );

    switch( dir ){
      case 0 :
        x = (x <= 0 ? SIZE - 1 : x - 1);
        break;
      case 1 :
        y = (y <= 0 ? SIZE - 1 : y - 1);
        break;
      case 2 :
        x = (x >= SIZE - 1 ? 0 : x + 1);
        break;
      case 3 :
        y = (y >= SIZE - 1 ? 0 : y + 1);
        break;
    }
  }
}

