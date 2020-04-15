package project1;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/***********************************************************************
 * The ChangeMakerTest class is used to test the ChangeMaker class methods.
 *
 * @author (Kyle Jacobson)
 * @version (09/14/18)
 ***********************************************************************/

public class ChangeMakerTest {

    /***********************************************************************
     * Instantiates all boolean instance variables to true before testing.
     ***********************************************************************/
    @Before
    public void initial() {
        ChangeMaker.setQuartersAvail(true);
        ChangeMaker.setDimesAvail(true);
        ChangeMaker.setNickelsAvail(true);
        ChangeMaker.setPenniesAvail(true);
    }

    /***********************************************************************
     * Tests the constructor when everything functions correctly.
     ***********************************************************************/
    @Test
    public void testConstructor1() {
        ChangeMaker s1 = new ChangeMaker(2.34);
        assertEquals(2.34, s1.getAmount(),0);

        ChangeMaker s2 = new ChangeMaker(34);
        assertEquals(34, s2.getAmount(),0);
    }

    /***********************************************************************
     * Tests the constructor when amount is equal to 0.
     ***********************************************************************/
    @Test (expected = IllegalArgumentException.class)
    public void testConstructor2() {
        new ChangeMaker(0);
    }

    /***********************************************************************
     * Tests the constructor when amount is less than 0.
     ***********************************************************************/
    @Test (expected = IllegalArgumentException.class)
    public void testConstructor3() {
        new ChangeMaker(-10);
    }

    /***********************************************************************
     * Test the constructor when amount includes more than two decimal points.
     ***********************************************************************/
    @Test (expected = IllegalArgumentException.class)
    public void testConstructor4() {
        new ChangeMaker(10.123);
    }

    /***********************************************************************
     * Test the constructor when amount includes more than two decimal points
     * again.
     ***********************************************************************/
    @Test (expected = IllegalArgumentException.class)
    public void testConstructor5() {
        new ChangeMaker(5.255);
    }

    /***********************************************************************
     * Tests the constructor when amount is equal to 1.0E15.
     ***********************************************************************/
    @Test (expected = IllegalArgumentException.class)
    public void testConstructor6() {
        new ChangeMaker(1.0E15);
    }

    /***********************************************************************
     * Tests the constructor when amount is greater than 1.0E15.
     ***********************************************************************/
    @Test (expected = IllegalArgumentException.class)
    public void testConstructor7() {
        new ChangeMaker(2.0E15);
    }

    /***********************************************************************
     * Tests the equals method when the return statement is true.
     ***********************************************************************/
    @Test
    public void testEquals() {
        ChangeMaker s1 = new ChangeMaker(2);
        ChangeMaker s2 = new ChangeMaker(2);
        assertTrue(s1.equals(s2));
    }

    /***********************************************************************
     * Tests the equals method when the return statement is false.
     ***********************************************************************/
    @Test
    public void testEquals2() {
        ChangeMaker s1 = new ChangeMaker(1);
        ChangeMaker s2 = new ChangeMaker(2);
        assertFalse(s1.equals(s2));
    }

    /***********************************************************************
     * Tests the takeOut method when everything functions correctly.
     ***********************************************************************/
    @Test
    public void takeout1() {
        ChangeMaker s1 = new ChangeMaker(.44);
        s1.takeOut(.44);
        assertEquals(0,s1.getAmount(),0);

        s1 = new ChangeMaker(2.00);
        s1.takeOut(.44);
        assertEquals(1.56,s1.getAmount(),0);

        s1 = new ChangeMaker(10);
        s1.takeOut(.5);
        assertEquals(9.5,s1.getAmount(),0);

        s1 = new ChangeMaker(.44);
        s1.takeOut(0.0);
        assertEquals(.44,s1.getAmount(),0);
    }

    /***********************************************************************
     * Tests the takeOut method when amount is less than 0.
     ***********************************************************************/
    @Test(expected = IllegalArgumentException.class)
    public void takeout2() {
        ChangeMaker s1 = new ChangeMaker(-10);
        s1.takeOut(.44);
    }

    /***********************************************************************
     * Tests the takeOut method when amount includes more than two decimal points.
     ***********************************************************************/
    @Test(expected = IllegalArgumentException.class)
    public void takeout3() {
        ChangeMaker s1 = new ChangeMaker(100);
        s1.takeOut(.443);
    }

    /***********************************************************************
     * Tests the takeOut method when amount includes more than two decimal points.
     ***********************************************************************/
    @Test (expected = IllegalArgumentException.class)
    public void takeout4() {
        ChangeMaker s1 = new ChangeMaker(100);
        s1.takeOut(.101);
    }

    /***********************************************************************
     * Tests the takeOut method when amount is greater than 1.0E15.
     ***********************************************************************/
    @Test (expected = IllegalArgumentException.class)
    public void takeout5() {
        ChangeMaker s1 = new ChangeMaker(2.0E15);
        s1.takeOut(1.10);
    }

    /***********************************************************************
     * Tests the takeOut method when no coins are selected.
     ***********************************************************************/
    @Test
    public void takeout6 () {
        ChangeMaker s1 = new ChangeMaker(1.0);
        ChangeMaker.setQuartersAvail(false);
        ChangeMaker.setDimesAvail(false);
        ChangeMaker.setNickelsAvail(false);
        ChangeMaker.setPenniesAvail(false);
        s1.takeOut(.25);
    }

    /***********************************************************************
     * Tests the takeOut method when only two quarters are withdrawn.
     ***********************************************************************/
    @Test
    public void takeout7 () {
        ChangeMaker s1 = new ChangeMaker(1.0);
        ChangeMaker.setDimesAvail(false);
        ChangeMaker.setNickelsAvail(false);
        ChangeMaker.setPenniesAvail(false);
        s1.takeOut(.50);
        assertEquals(0.50, s1.getAmount(), 0);
    }

    /***********************************************************************
     * Tests the takeOut method when only five dimes are withdrawn.
     ***********************************************************************/
    @Test
    public void takeout8 () {
        ChangeMaker s1 = new ChangeMaker(1.0);
        ChangeMaker.setQuartersAvail(false);
        ChangeMaker.setNickelsAvail(false);
        ChangeMaker.setPenniesAvail(false);
        s1.takeOut(.50);
        assertEquals(0.50, s1.getAmount(), 0);
    }

    /***********************************************************************
     * Tests the takeOut method when only ten nickels are withdrawn.
     ***********************************************************************/
    @Test
    public void takeout9 () {
        ChangeMaker s1 = new ChangeMaker(1.0);
        ChangeMaker.setQuartersAvail(false);
        ChangeMaker.setDimesAvail(false);
        ChangeMaker.setPenniesAvail(false);
        s1.takeOut(.50);
        assertEquals(0.50, s1.getAmount(), 0);
    }

    /***********************************************************************
     * Tests the takeOut method when only fifty pennies are withdrawn.
     ***********************************************************************/
    @Test
    public void takeout10 () {
        ChangeMaker s1 = new ChangeMaker(1.0);
        ChangeMaker.setQuartersAvail(false);
        ChangeMaker.setDimesAvail(false);
        ChangeMaker.setNickelsAvail(false);
        s1.takeOut(.50);
        assertEquals(0.50, s1.getAmount(), 0);
    }

    /***********************************************************************
     * Tests the takeOut method when only quarters and dimes are withdrawn.
     ***********************************************************************/
    @Test
    public void takeout11 () {
        ChangeMaker s1 = new ChangeMaker(4.5);
        ChangeMaker.setNickelsAvail(false);
        ChangeMaker.setPenniesAvail(false);
        s1.takeOut(.25);
        assertEquals(4.25, s1.getAmount(), 0);
    }

    /***********************************************************************
     * Tests the takeOut method when only quarters and nickels are withdrawn.
     ***********************************************************************/
    @Test
    public void takeout12 () {
        ChangeMaker s1 = new ChangeMaker(4.5);
        ChangeMaker.setDimesAvail(false);
        ChangeMaker.setPenniesAvail(false);
        s1.takeOut(.25);
        assertEquals(4.25, s1.getAmount(), 0);
    }

    /***********************************************************************
     * Tests the takeOut method when only quarters and pennies are withdrawn.
     ***********************************************************************/
    @Test
    public void takeout13 () {
        ChangeMaker s1 = new ChangeMaker(4.5);
        ChangeMaker.setDimesAvail(false);
        ChangeMaker.setNickelsAvail(false);
        s1.takeOut(.25);
        assertEquals(4.25, s1.getAmount(), 0);
    }

    /***********************************************************************
     * Tests the takeOut method when only dimes and nickels are withdrawn.
     ***********************************************************************/
    @Test
    public void takeout14 () {
        ChangeMaker s1 = new ChangeMaker(4.5);
        ChangeMaker.setQuartersAvail(false);
        ChangeMaker.setPenniesAvail(false);
        s1.takeOut(.25);
        assertEquals(4.25, s1.getAmount(), 0);
    }

    /***********************************************************************
     * Tests the takeOut method when only dimes and pennies are withdrawn.
     ***********************************************************************/
    @Test
    public void takeout15 () {
        ChangeMaker s1 = new ChangeMaker(4.5);
        ChangeMaker.setQuartersAvail(false);
        ChangeMaker.setNickelsAvail(false);
        s1.takeOut(.25);
        assertEquals(4.25, s1.getAmount(), 0);
    }

    /***********************************************************************
     * Tests the takeOut method when only nickels and pennies are withdrawn.
     ***********************************************************************/
    @Test
    public void takeout16 () {
        ChangeMaker s1 = new ChangeMaker(4.5);
        ChangeMaker.setQuartersAvail(false);
        ChangeMaker.setDimesAvail(false);
        s1.takeOut(.25);
        assertEquals(4.25, s1.getAmount(), 0);
    }

    /***********************************************************************
     * Tests the compareTo method to check multiple conditions:
     * - s1 and s2 must be equal.
     * - s1 must be greater than s3.
     * - s3 must be less than s1.
     ***********************************************************************/
    @Test
    public void testCompareTo() {
        ChangeMaker s1 = new ChangeMaker(2);
        ChangeMaker s2 = new ChangeMaker(2);
        ChangeMaker s3 = new ChangeMaker(1);

        assertEquals(0, s1.compareTo(s2));
        assertTrue(s1.compareTo(s3) > 0);
        assertTrue(s3.compareTo(s1) < 0);
    }

    /***********************************************************************
     * Tests the compareTo method to check a condition:
     * - s1 must be less than s2.
     ***********************************************************************/
    @Test
    public void testCompareTo2() {
        ChangeMaker s1 = new ChangeMaker(1);
        ChangeMaker s2 = new ChangeMaker(2);

        assertEquals(-1, s1.compareTo(s2));
    }

    /***********************************************************************
     * Tests the compareTo method to check a condition:
     * - s1 must be greater than s2.
     ***********************************************************************/
    @Test
    public void testCompareTo3() {
        ChangeMaker s1 = new ChangeMaker(3);
        ChangeMaker s2 = new ChangeMaker(2);

        assertEquals(1, s1.compareTo(s2));
    }

    /***********************************************************************
     * Tests the compareTo method to check a condition:
     * - s1 and s2 must be equal.
     ***********************************************************************/
    @Test
    public void testCompareTo4() {
        ChangeMaker s1 = new ChangeMaker(2);
        ChangeMaker s2 = new ChangeMaker(2);

        assertEquals(0, s1.compareTo(s2));
    }

    /***********************************************************************
     * Tests the loadMachine method to see if valid amount is added.
     ***********************************************************************/
    @Test
    public void testLoadMachine1 () {
        ChangeMaker s1 = new ChangeMaker();
        s1.loadMachine(123.12);
        assertEquals(123.12, s1.getAmount(), 0.0);
    }

    /***********************************************************************
     * Tests the loadMachine method to check when negative amount is added.
     ***********************************************************************/
    @Test (expected = IllegalArgumentException.class)
    public void testLoadMachine2 () {
        ChangeMaker s1 = new ChangeMaker();
        s1.loadMachine(-10);
    }

    /***********************************************************************
     * Tests the loadMachine method to check when nothing is added.
     ***********************************************************************/
    @Test (expected = IllegalArgumentException.class)
    public void testLoadMachine3 () {
        ChangeMaker s1 = new ChangeMaker();
        s1.loadMachine(0);
    }

    /***********************************************************************
     * Tests the loadMachine method to check when amount has more than 2
     * decimal points.
     ***********************************************************************/
    @Test (expected = IllegalArgumentException.class)
    public void testLoadMachine4 () {
        ChangeMaker s1 = new ChangeMaker();
        s1.loadMachine(117.432);
    }

    /***********************************************************************
     * Tests the loadMachine method to check when amount has more than 2
     * decimal points again.
     ***********************************************************************/
    @Test (expected = IllegalArgumentException.class)
    public void testLoadMachine5 () {
        ChangeMaker s1 = new ChangeMaker();
        s1.loadMachine(5.755);
    }

    /***********************************************************************
     * Tests the loadMachine method to check when the amount is equal to
     * 1.0E15.
     ***********************************************************************/
    @Test (expected = IllegalArgumentException.class)
    public void testLoadMachine6 () {
        ChangeMaker s1 = new ChangeMaker();
        s1.loadMachine(1.0E15);
    }

    /***********************************************************************
     * Tests the loadMachine method to check when the amount is greater
     * than 1.0E15.
     ***********************************************************************/
    @Test (expected = IllegalArgumentException.class)
    public void testLoadMachine7 () {
        ChangeMaker s1 = new ChangeMaker();
        s1.loadMachine(2.0E15);
    }

    /***********************************************************************
     * Tests the loadTest method with a proper String for fileName.
     ***********************************************************************/
    @Test
    public void loadTest1 () {
        ChangeMaker s1 = new ChangeMaker(4.4);
        s1.setAmount(0);
        s1.load("file.txt");
        assertEquals(4.4, s1.getAmount(), 0);
    }

    /***********************************************************************
     * Tests the loadTest method without a proper String for fileName.
     ***********************************************************************/
    @Test (expected = IllegalArgumentException.class)
    public void loadTest2 () {
        ChangeMaker s1 = new ChangeMaker(4.4);
        s1.setAmount(0);
        s1.load("");
        assertEquals(4.4, s1.getAmount(), 0);
    }

    /***********************************************************************
     * Tests the saveTest method with a proper String for fileName.
     ***********************************************************************/
    @Test
    public void saveTest1 () {
        ChangeMaker s1 = new ChangeMaker(4.4);
        s1.save("file.txt");
    }

    /***********************************************************************
     * Tests the saveTest method without a proper String for fileName.
     ***********************************************************************/
    @Test (expected = IllegalArgumentException.class)
    public void saveTest2 () {
        ChangeMaker s1 = new ChangeMaker(4.4);
        s1.save("");
    }
}