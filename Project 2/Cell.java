package project2;

/***********************************************************************
 * The Cell class contains methods for the Cell arrayList.
 *
 * @author (Kyle Jacobson)
 * @version (09/25/28)
 ***********************************************************************/

public class Cell implements Comparable<Cell> {
    public int row, column, value;

    public Cell()
    {
        this(0,0,0);
    }
    public Cell(int r, int c, int v)
    {
        row = r;
        column = c;
        value = v;
    }

    /* must override equals to ensure List::contains() works
     * correctly
     */
    @Override
    public int compareTo (Cell other) {
        if (this.row < other.row) return -1;
        if (this.row > other.row) return +1;

        /* break the tie using column */
        if (this.column < other.column) return -1;
        if (this.column > other.column) return +1;

        return this.value - other.value;
    }
}
