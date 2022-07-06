import static org.junit.jupiter.api.Assertions.*;

class SquarelotronTest {
    Squarelotron squarelotron;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        squarelotron = new Squarelotron(5);
    }

    @org.junit.jupiter.api.Test
    void upsideDownFlip() {
        assertEquals(5, squarelotron.grid.length); // assumes 5x5 as below tests are setup for 5x5

        // Outer ring flip
        int[][] res_grid_outer = squarelotron.upsideDownFlip(1); // flip outer ring
        assertArrayEquals(res_grid_outer[0], new int[]{21, 22, 23, 24, 25});  // outer ring flips, bottom row on top
        assertArrayEquals(res_grid_outer[4], new int[]{1, 2, 3, 4, 5});  // outer ring flips, bottom row on top
        assertArrayEquals(res_grid_outer[1], new int[]{16, 7, 8, 9, 20});  // inner ring doesn't flip, checking second row
        assertEquals(13, res_grid_outer[2][2]); // middle doesn't move

        // Flip the second ring (starting from reset grid)
        int[][] res_grid_second = squarelotron.upsideDownFlip(2); // flip second ring
        assertArrayEquals(res_grid_second[0], new int[]{1, 2, 3, 4, 5});  // top row should stay as original
        assertEquals(13, res_grid_second[2][2]); // middle doesn't move
        assertArrayEquals(res_grid_second[1], new int[]{6, 17, 18, 19, 10});  // checking second row had 2nd ring flipped
    }

    @org.junit.jupiter.api.Test
    void mainDiagonalFlip() {
        assertEquals(5, squarelotron.grid.length); // assumes 5x5 as below tests are setup for 5x5

        // Outer ring diagonal flip
        int[][] res_grid_outer = squarelotron.mainDiagonalFlip(1); // flip outer ring
        assertArrayEquals(res_grid_outer[0], new int[]{1, 6, 11, 16, 21});
        assertEquals(13, res_grid_outer[2][2]); // middle doesn't move
        assertArrayEquals(res_grid_outer[1], new int[]{2, 7, 8, 9, 22});  // checking second row had 2nd ring flipped

        // Second ring diagonal flip
        int[][] res_grid_second = squarelotron.mainDiagonalFlip(2); // flip second ring
        assertArrayEquals(res_grid_second[1], new int[]{6, 7, 12, 17, 10});
        assertEquals(13, res_grid_second[2][2]); // middle doesn't move
        assertArrayEquals(res_grid_second[0], new int[]{1, 2, 3, 4, 5});  // top row should not change
    }

    @org.junit.jupiter.api.Test
    void rotateRight() {
        assertEquals(5, squarelotron.grid.length); // assumes 5x5 as below tests are setup for 5x5

        // One turn
        int[][] res_grid_oneturn = squarelotron.rotateRight(1); // rotate entire square by 90 degrees
        assertArrayEquals(res_grid_oneturn[0], new int[]{21, 16, 11, 6, 1});  // top row should change
        assertEquals(13, res_grid_oneturn[2][2]); // middle doesn't move
        assertArrayEquals(res_grid_oneturn[1], new int[]{22, 17, 12, 7, 2});  // checking second row had changed

        // Two turns
        int[][] res_grid_twoturn = squarelotron.rotateRight(2); // rotate entire square by 180 degrees
        assertArrayEquals(res_grid_twoturn[0], new int[]{25, 24, 23, 22, 21});  // top row be original bottom row
        assertEquals(13, res_grid_twoturn[2][2]); // middle doesn't change
        assertArrayEquals(res_grid_twoturn[3], new int[]{10, 9, 8, 7, 6});  // second bottom row should be original second row
    }
}