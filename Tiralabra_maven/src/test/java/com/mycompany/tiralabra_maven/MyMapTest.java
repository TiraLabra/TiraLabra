package com.mycompany.tiralabra_maven;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class MyMapTest extends TestCase
{
    /** Test that the same point returns true
     */
    public void testMapCanFindSameCoordinate()
    {
        String map = "o_____x";
        Node node = new Node(1,0);
        MyMap myMap = new MyMap();
        myMap.createMap(map);
        assertTrue(myMap.isSamePointOnMap(1, 0, node));
    }

    /** Test that the created map is same as original map:
     */
    public void testMapCreatesEqualMap()
    {

        String map = "_______\n" +
                     "___#___\n" +
                     "x__#o__\n" +
                     "___#___";

        MyMap myMap = new MyMap();
        myMap.createMap(map);

        Node[][] table = myMap.getMap();
        String map2 = "";

        for (int y = 0; y < myMap.getMaxY(); y++) {
            for (int x = 0; x < myMap.getMaxX(); x++) {
                map2 += table[y][x].getCharacter();
            }
            map2 += "\n";
        }
        /** Add extra line break for the original map because for-loop creates one for map2 aswell: */
        map += "\n";
        assertEquals(map, map2);
    }

    /** Test that the start and end point are configured correctly:
     */
    public void testMapCreatesStartAndEndCorrectly()
    {

        String map = "_______\n" +
                     "___#___\n" +
                     "x__#o__\n" +
                     "___#___";

        MyMap myMap = new MyMap();
        myMap.createMap(map);

        assertTrue(myMap.isSamePointOnMap(4,2,myMap.getStart()));
        assertTrue(myMap.isSamePointOnMap(0,2,myMap.getEnd()));


    }
}
