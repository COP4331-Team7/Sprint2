package com.team7.tests;

import com.team7.model.Tile;
import com.team7.model.terrain.Desert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by anip on 12/03/17.
 */
public class TileTest {

    @Test
    public void calculateDistance() throws Exception{
        Tile start = new Tile(new Desert(), 21,19);
        Tile finish = new Tile(new Desert(), 22,19);
        int x1=0,y1=0;
        int x2=0,y2=0;
        x1 = start.getxCoordinate();
        y1 = start.getyCoordinate();
        x2 = finish.getxCoordinate();
        y2 = finish.getyCoordinate();

        int d1 = Math.abs(x1-x2)*2;
        int d2 = Math.abs(y1-y2)/2;
        int d3 = Math.abs((x1-x2)*2+(y1-y2)/2);
        System.out.println(x1);
        System.out.println(y1);

  System.out.println(x2);
        System.out.println(y2);
        if(x1%2==0 && y1%2==0 && y2%2==0 && x2%2== 1)
            System.out.println((d1+d2+d3)/2-1);
        else if(x1%2!=0 && y1%2!= 0 && y1==y2) {
            System.out.println((d1 + d2 + d3) / 2 + 1);
        }
        else if(x1==x2 && y2%2==1)
            System.out.println((d1+d2+d3)/2+1);

        else if(y1%2==1 && y2%2==0)
            System.out.println((d1+d2+d3)/2+1);
        else if(y2%2==1 && y1%2== 1)
            System.out.println((d1+d2+d3)/2-1);
        else if(y2%2!=0 && x2%2!= 0) {
            System.out.println((d1 + d2 + d3) / 2 + 2);
        }

        else {
            System.out.println((d1 + d2 + d3) / 2);
        }
    }
}
