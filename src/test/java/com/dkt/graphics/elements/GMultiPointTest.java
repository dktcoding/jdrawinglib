/*
 *                      ..::jDrawingLib::..
 *
 * Copyright (C) Federico Vera 2012 - 2018 <fede@riddler.com.ar>
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.dkt.graphics.elements;

import com.dkt.graphics.exceptions.InvalidArgumentException;
import java.awt.Graphics2D;
import java.util.Random;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Federico Vera {@literal<fede@riddler.com.ar>}
 */
public class GMultiPointTest {
    public static double DELTA = 1e-8;

    @Test
    @DisplayName("Test constructor")
    public void testConstructor1() {
        GMultiPoint mp = new GMultiPoint(10) {
            @Override public GFillableE clone() {return null;}
            @Override public void draw(Graphics2D g) {}
        };
        assertNotNull(mp);
    }

    @Test
    @DisplayName("Test constructor < 0")
    public void testConstructor2() {
        assertThrows(NegativeArraySizeException.class, () -> {
            GMultiPoint mp = new GMultiPoint(-10) {
                @Override public GFillableE clone() {return null;}
                @Override public void draw(Graphics2D g) {}
            };
        });
    }

    @Test
    @DisplayName("Test constructor different lenghts")
    public void testConstructor3() {
        int[] XX = {0, 1, 2, 3, 4, 5};
        int[] YY = {0, 1, 2, 3, 4, 5, 6};
        assertThrows(InvalidArgumentException.class, () -> {
            GMultiPoint mp = new GMultiPoint(XX, YY) {
                @Override public GFillableE clone() {return null;}
                @Override public void draw(Graphics2D g) {}
            };
        });
    }

    @Test
    @DisplayName("Test constructor different lenghts")
    public void testConstructor4() {
        int[] XX = {0, 1, 2, 3, 4, 5, 6};
        int[] YY = {0, 1, 2, 3, 4, 5};
        assertThrows(InvalidArgumentException.class, () -> {
            GMultiPoint mp = new GMultiPoint(XX, YY) {
                @Override public GFillableE clone() {return null;}
                @Override public void draw(Graphics2D g) {}
            };
        });
    }

    @Test
    @DisplayName("Test constructor data")
    public void testConstructor5() {
        int[] XX = {0, 1, 2, 3, 4};
        int[] YY = {5, 6, 7, 8, 9};
        GMultiPoint mp = new GMultiPoint(XX, YY) {
            @Override public GFillableE clone() {return null;}
            @Override public void draw(Graphics2D g) {}
        };

        assertNotNull(mp);
        assertEquals(5, mp.size());
        XX[0] = 10000;
        assertEquals(0, mp.getPointAt(0).x());
    }

    @Test
    @DisplayName("Index of empty")
    public void testIndexOf1() {
        GMultiPoint mp = new GMultiPoint(10) {
            @Override public GFillableE clone() {return null;}
            @Override public void draw(Graphics2D g) {}
        };

        assertNotNull(mp);
        assertEquals(-1, mp.indexOf(new GPoint(0, 0)));
    }

    @Test
    @DisplayName("Index of first")
    public void testIndexOf2() {
        int[] XX = {0, 1, 2, 3, 4};
        int[] YY = {5, 6, 7, 8, 9};
        GMultiPoint mp = new GMultiPoint(XX, YY) {
            @Override public GFillableE clone() {return null;}
            @Override public void draw(Graphics2D g) {}
        };

        assertNotNull(mp);
        assertEquals(5, mp.size());
        assertEquals(0, mp.indexOf(new GPoint(0, 5)));
    }

    @Test
    @DisplayName("Index of last")
    public void testIndexOf3() {
        int[] XX = {0, 1, 2, 3, 4};
        int[] YY = {5, 6, 7, 8, 9};
        GMultiPoint mp = new GMultiPoint(XX, YY) {
            @Override public GFillableE clone() {return null;}
            @Override public void draw(Graphics2D g) {}
        };

        assertNotNull(mp);
        assertEquals(5, mp.size());
        assertFalse(mp.isEmpty());
        assertEquals(4, mp.indexOf(new GPoint(4, 9)));
    }

    @Test
    @DisplayName("Index with initial search")
    public void testIndexOf4() {
        int[] XX = {0, 1, 2, 3, 4, 3};
        int[] YY = {5, 6, 7, 8, 9, 8};
        GMultiPoint mp = new GMultiPoint(XX, YY) {
            @Override public GFillableE clone() {return null;}
            @Override public void draw(Graphics2D g) {}
        };

        assertNotNull(mp);
        assertEquals(6, mp.size());
        assertFalse(mp.isEmpty());
        assertEquals(5, mp.indexOf(new GPoint(3, 8), 4));
        assertEquals(3, mp.indexOf(new GPoint(3, 8)));
    }

    @Test
    @DisplayName("Remove out of bounds")
    public void testRemove1() {
        int[] XX = {0, 1, 2, 3, 4, 3};
        int[] YY = {5, 6, 7, 8, 9, 8};
        GMultiPoint mp = new GMultiPoint(XX, YY) {
            @Override public GFillableE clone() {return null;}
            @Override public void draw(Graphics2D g) {}
        };

        assertNotNull(mp);
        assertEquals(6, mp.size());
        assertFalse(mp.isEmpty());

        assertThrows(IndexOutOfBoundsException.class, () -> {
            mp.remove(-1);
        });
    }

    @Test
    @DisplayName("Remove out of bounds")
    public void testRemove2() {
        int[] XX = {0, 1, 2, 3, 4, 3};
        int[] YY = {5, 6, 7, 8, 9, 8};
        GMultiPoint mp = new GMultiPoint(XX, YY) {
            @Override public GFillableE clone() {return null;}
            @Override public void draw(Graphics2D g) {}
        };

        assertNotNull(mp);
        assertEquals(6, mp.size());
        assertFalse(mp.isEmpty());

        assertThrows(IndexOutOfBoundsException.class, () -> {
            mp.remove(10);
        });
    }

    @Test
    @DisplayName("Remove out of bounds")
    public void testRemove3() {
        GMultiPoint mp = new GMultiPoint(4) {
            @Override public GFillableE clone() {return null;}
            @Override public void draw(Graphics2D g) {}
        };

        assertNotNull(mp);
        assertEquals(0, mp.size());
        assertTrue(mp.isEmpty());

        assertThrows(IndexOutOfBoundsException.class, () -> {
            mp.remove(1);
        });
    }

    @Test
    @DisplayName("Remove first")
    public void testRemove4() {
        int[] XX = {0, 1, 2, 3, 4, 3};
        int[] YY = {5, 6, 7, 8, 9, 8};
        GMultiPoint mp = new GMultiPoint(XX, YY) {
            @Override public GFillableE clone() {return null;}
            @Override public void draw(Graphics2D g) {}
        };

        assertNotNull(mp);
        assertEquals(6, mp.size());
        assertFalse(mp.isEmpty());

        mp.remove(0);

        assertEquals(5, mp.size());
        assertFalse(mp.isEmpty());

        for (int i = 1; i < YY.length; i++) {
            GPoint p1 = mp.getPointAt(i - 1);
            GPoint p2 = new GPoint(XX[i], YY[i]);
            assertEquals(p1, p2);
        }
    }

    @Test
    @DisplayName("Remove last")
    public void testRemove5() {
        int[] XX = {0, 1, 2, 3, 4, 3};
        int[] YY = {5, 6, 7, 8, 9, 8};
        GMultiPoint mp = new GMultiPoint(XX, YY) {
            @Override public GFillableE clone() {return null;}
            @Override public void draw(Graphics2D g) {}
        };

        assertNotNull(mp);
        assertEquals(6, mp.size());
        assertFalse(mp.isEmpty());

        mp.remove(5);

        assertEquals(5, mp.size());
        assertFalse(mp.isEmpty());

        for (int i = 0; i < YY.length - 1; i++) {
            GPoint p1 = mp.getPointAt(i);
            GPoint p2 = new GPoint(XX[i], YY[i]);
            assertEquals(p1, p2);
        }
    }

    @Test
    @DisplayName("Remove middle")
    public void testRemove6() {
        int[] XX = {0, 1, 2, 3, 4, 3};
        int[] YY = {5, 6, 7, 8, 9, 8};
        GMultiPoint mp = new GMultiPoint(XX, YY) {
            @Override public GFillableE clone() {return null;}
            @Override public void draw(Graphics2D g) {}
        };

        assertNotNull(mp);
        assertEquals(6, mp.size());
        assertFalse(mp.isEmpty());

        mp.remove(3);

        assertEquals(5, mp.size());
        assertFalse(mp.isEmpty());

        assertEquals(new GPoint(XX[0], YY[0]), mp.getPointAt(0));
        assertEquals(new GPoint(XX[1], YY[1]), mp.getPointAt(1));
        assertEquals(new GPoint(XX[2], YY[2]), mp.getPointAt(2));
        assertEquals(new GPoint(XX[4], YY[4]), mp.getPointAt(3));
        assertEquals(new GPoint(XX[5], YY[5]), mp.getPointAt(4));
    }

    @Test
    @DisplayName("Remove first")
    public void testRemove7() {
        int[] XX = {0, 1, 2, 3, 4, 3};
        int[] YY = {5, 6, 7, 8, 9, 8};
        GMultiPoint mp = new GMultiPoint(XX, YY) {
            @Override public GFillableE clone() {return null;}
            @Override public void draw(Graphics2D g) {}
        };

        assertNotNull(mp);
        assertEquals(6, mp.size());
        assertFalse(mp.isEmpty());

        mp.remove(0, 5);

        assertEquals(5, mp.size());
        assertFalse(mp.isEmpty());

        for (int i = 1; i < YY.length; i++) {
            GPoint p1 = mp.getPointAt(i - 1);
            GPoint p2 = new GPoint(XX[i], YY[i]);
            assertEquals(p1, p2);
        }
    }

    @Test
    @DisplayName("Remove last")
    public void testRemove8() {
        int[] XX = {0, 1, 2, 3, 4};
        int[] YY = {5, 6, 7, 8, 9};
        GMultiPoint mp = new GMultiPoint(XX, YY) {
            @Override public GFillableE clone() {return null;}
            @Override public void draw(Graphics2D g) {}
        };

        assertNotNull(mp);
        assertEquals(5, mp.size());
        assertFalse(mp.isEmpty());

        mp.remove(4, 9);

        assertEquals(4, mp.size());
        assertFalse(mp.isEmpty());

        for (int i = 0; i < YY.length - 1; i++) {
            GPoint p1 = mp.getPointAt(i);
            GPoint p2 = new GPoint(XX[i], YY[i]);
            assertEquals(p1, p2);
        }
    }

    @Test
    @DisplayName("Remove middle")
    public void testRemove9() {
        int[] XX = {0, 1, 2, 3, 4, 3};
        int[] YY = {5, 6, 7, 8, 9, 8};
        GMultiPoint mp = new GMultiPoint(XX, YY) {
            @Override public GFillableE clone() {return null;}
            @Override public void draw(Graphics2D g) {}
        };

        assertNotNull(mp);
        assertEquals(6, mp.size());
        assertFalse(mp.isEmpty());

        mp.remove(2, 7);

        assertEquals(5, mp.size());
        assertFalse(mp.isEmpty());

        assertEquals(new GPoint(XX[0], YY[0]), mp.getPointAt(0));
        assertEquals(new GPoint(XX[1], YY[1]), mp.getPointAt(1));
        assertEquals(new GPoint(XX[3], YY[3]), mp.getPointAt(2));
        assertEquals(new GPoint(XX[4], YY[4]), mp.getPointAt(3));
        assertEquals(new GPoint(XX[5], YY[5]), mp.getPointAt(4));
    }

    @Test
    @DisplayName("Append to empty")
    public void testAppend1() {
        GMultiPoint mp = new GMultiPoint(0) {
            @Override public GFillableE clone() {return null;}
            @Override public void draw(Graphics2D g) {}
        };

        assertNotNull(mp);
        assertEquals(0, mp.size());
        assertTrue(mp.isEmpty());
        mp.append(1,2);
        assertEquals(1, mp.size());
        assertFalse(mp.isEmpty());
        mp.append(3,4);
        assertEquals(2, mp.size());
        assertFalse(mp.isEmpty());
        mp.append(3,4);
        assertEquals(3, mp.size());
        assertFalse(mp.isEmpty());

        assertEquals(new GPoint(1, 2), mp.getPointAt(0));
        assertEquals(new GPoint(3, 4), mp.getPointAt(1));
        assertEquals(new GPoint(3, 4), mp.getPointAt(2));

    }

    @Test
    @DisplayName("Append to data")
    public void testAppend2() {
        int[] XX = {0, 1, 2, 3, 4, 3};
        int[] YY = {5, 6, 7, 8, 9, 8};
        GMultiPoint mp = new GMultiPoint(XX, YY) {
            @Override public GFillableE clone() {return null;}
            @Override public void draw(Graphics2D g) {}
        };

        assertNotNull(mp);
        assertEquals(6, mp.size());
        assertFalse(mp.isEmpty());
        mp.append(1,2);
        assertEquals(7, mp.size());
        assertFalse(mp.isEmpty());
        mp.append(3,4);
        assertEquals(8, mp.size());
        assertFalse(mp.isEmpty());

        assertEquals(new GPoint(1, 2), mp.getPointAt(6));
        assertEquals(new GPoint(3, 4), mp.getPointAt(7));

    }

    @Test
    @DisplayName("AppendNR to empty")
    public void testAppendNR1() {
        GMultiPoint mp = new GMultiPoint(0) {
            @Override public GFillableE clone() {return null;}
            @Override public void draw(Graphics2D g) {}
        };

        assertNotNull(mp);
        assertEquals(0, mp.size());
        assertTrue(mp.isEmpty());
        mp.appendNR(1,2);
        assertEquals(1, mp.size());
        assertFalse(mp.isEmpty());
        mp.appendNR(3,4);
        assertEquals(2, mp.size());
        assertFalse(mp.isEmpty());
        mp.appendNR(3,4);
        assertEquals(2, mp.size());
        assertFalse(mp.isEmpty());

        assertEquals(new GPoint(1, 2), mp.getPointAt(0));
        assertEquals(new GPoint(3, 4), mp.getPointAt(1));

    }

    @Test
    @DisplayName("AppendNR to data")
    public void testAppendNR2() {
        int[] XX = {0, 1, 2, 3, 4, 3};
        int[] YY = {5, 6, 7, 8, 9, 8};
        GMultiPoint mp = new GMultiPoint(XX, YY) {
            @Override public GFillableE clone() {return null;}
            @Override public void draw(Graphics2D g) {}
        };

        assertNotNull(mp);
        assertEquals(6, mp.size());
        assertFalse(mp.isEmpty());
        mp.appendNR(1,2);
        assertEquals(7, mp.size());
        assertFalse(mp.isEmpty());
        mp.appendNR(1,6);
        assertEquals(7, mp.size());
        assertFalse(mp.isEmpty());

        assertEquals(new GPoint(1, 2), mp.getPointAt(6));
    }

    @Test
    @DisplayName("Sort by X")
    public void testSortByX() {
        int[] XX = {0, 1, 2, 3, 4, 3};
        int[] YY = {5, 6, 7, 8, 9, 8};
        GMultiPoint mp = new GMultiPoint(XX, YY) {
            @Override public GFillableE clone() {return null;}
            @Override public void draw(Graphics2D g) {}
        };

        assertNotNull(mp);
        assertEquals(6, mp.size());
        assertFalse(mp.isEmpty());
        mp.sortByX();
        assertEquals(6, mp.size());
        assertFalse(mp.isEmpty());
        assertEquals(0, mp.indexOf(0, 5));
        assertEquals(1, mp.indexOf(1, 6));
        assertEquals(2, mp.indexOf(2, 7));
        assertEquals(3, mp.indexOf(3, 8));
        assertEquals(4, mp.indexOf(3, 8), 4);
        assertEquals(5, mp.indexOf(4, 9));
    }


}
