package problem_set;

import java.util.Scanner;

public class Main {
    private static final char NORMAL_CELL = '.', STICKER = '*', PILLAR = '#';
    private int n, m, s, total, x, y;
    private char[][] arena;
    private DIRECTION currentDirection;

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
//                Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));


        while (true) {
            n = sc.nextInt();
            m = sc.nextInt();
            s = sc.nextInt();
            if (n == 0 && m == 0 && s == 0) {
                break;
            }
            sc.nextLine();

            arena = new char[n][m];
            for (int i = 0; i < n; i++) {
                String line = sc.nextLine().trim();
                arena[i] = line.toCharArray();
                for (int j = 0; j < m; j++) {
                    switch (line.charAt(j)) {
                        case 'N':
                            currentDirection = DIRECTION.NORTH;
                            x = i;
                            y = j;
                            arena[i][j] = NORMAL_CELL;
                            break;

                        case 'S':
                            x = i;
                            y = j;
                            currentDirection = DIRECTION.SOUTH;
                            arena[i][j] = NORMAL_CELL;
                            break;

                        case 'L':
                            x = i;
                            y = j;
                            currentDirection = DIRECTION.EAST;
                            arena[i][j] = NORMAL_CELL;
                            break;

                        case 'O':
                            x = i;
                            y = j;
                            currentDirection = DIRECTION.WEST;
                            arena[i][j] = NORMAL_CELL;
                            break;

                        default:
                            break;
                    }
                }
            }

            String order = sc.nextLine().trim();
            total = 0;
            for (int i = 0; i < s; i++) {
                char c = order.charAt(i);
                move(c);
            }
            System.out.println(total);
        }
    }

    private void move(char c) {
        if (c == 'D') {
            turnRight();
        } else if (c == 'E') {
            turnLeft();
        } else if (c == 'F') {
            moveForward();
        }
    }

    private void moveForward() {
        int nextX = getNextX();
        int nextY = getNextY();
        if(isInArena(nextX, nextY)) {
            switch (arena[nextX][nextY]){
                case NORMAL_CELL:
                    x = nextX;
                    y = nextY;
                    break;

                case STICKER:
                    total++;
                    x = nextX;
                    y = nextY;
                    arena[nextX][nextY] = NORMAL_CELL;
                    break;

                case PILLAR:
                    //do nothing
                    break;

                default:
                    break;
            }
        } else {
            //do nothing
        }
    }

    private boolean isInArena(int nextX, int nextY) {
        return (0 <= nextX && nextX <= n -1 && 0 <= nextY && nextY <= m -1);
    }

    private int getNextY() {
        switch (currentDirection) {
            case EAST:
                return y + 1;

            case WEST:
                return y -1;

            default:
                return y;

        }
    }

    private int getNextX() {
        switch (currentDirection) {
            case NORTH:
                return x - 1;

            case SOUTH:
                return x + 1;

            default:
                return x;

        }
    }

    private void turnLeft() {
        switch (currentDirection) {
            case NORTH:
                currentDirection = DIRECTION.WEST;
                break;

            case SOUTH:
                currentDirection = DIRECTION.EAST;
                break;

            case EAST:
                currentDirection = DIRECTION.NORTH;
                break;

            case WEST:
                currentDirection = DIRECTION.SOUTH;
                break;

            default:
                break;

        }
    }

    private void turnRight() {
        switch (currentDirection) {
            case NORTH:
                currentDirection = DIRECTION.EAST;
                break;

            case SOUTH:
                currentDirection = DIRECTION.WEST;
                break;

            case EAST:
                currentDirection = DIRECTION.SOUTH;
                break;

            case WEST:
                currentDirection = DIRECTION.NORTH;
                break;

            default:
                break;

        }
    }

    private enum DIRECTION {
        NORTH, SOUTH, EAST, WEST;
    }
}

