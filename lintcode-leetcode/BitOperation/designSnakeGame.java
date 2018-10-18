/** 353. Design Snake Game(leetcode)
 *      Design a Snake game that is played on a device with screen size = width x height. Play the game online if 
 *  you are not familiar with the game.
 *      The snake is initially positioned at the top left corner (0,0) with length = 1 unit.
 *      You are given a list of food's positions in row-column order. When a snake eats the food, its length and 
 *  the game's score both increase by 1.
 *      Each food appears one by one on the screen. For example, the second food will not appear until the first 
 *  food was eaten by the snake.
 *      When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the
 *  snake.
 *
 *      Example: Given width = 3, height = 2, and food = [[1,2],[0,1]].
 *               Snake snake = new Snake(width, height, food);
 *               Initially the snake appears at position (0,0) and the food at (1,2).
 *                 |S| | |
 *                 | | |F|
 *               snake.move("R"); -> Returns 0
 *                 | |S| |
 *                 | | |F|
 *               snake.move("D"); -> Returns 0
 *                 | | | |
 *                 | |S|F|
 *               snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, 
 *                                                    the second food appears at (0,1) )
 *                 | |F| |
 *                 | |S|S|
 *               snake.move("U"); -> Returns 1
 *                 | |F|S|
 *                 | | |S|
 *               snake.move("L"); -> Returns 2 (Snake eats the second food)
 *                 | |S|S|
 *                 | | |S|
 *               snake.move("U"); -> Returns -1 (Game over because snake collides with border)
 */

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */
 
 //My Method:
 class SnakeGame {
    int[] snake_head;
    List<int[]> snake_body;
    int score;
    Queue<int[]> foodlist;
    int row;
    int col;
    
    /** Initialize your data structure here.
        @param width - screen width
        @param height - screen height 
        @param food - A list of food positions
        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public SnakeGame(int width, int height, int[][] food) {
        row = height;
        col = width;
        snake_head = new int[2];// snake initial is in [0,0]
        snake_body = new ArrayList<int[]>(); 
        score = 0;
        foodlist = new LinkedList<int[]>();
        
        for(int i = 0; i < food.length; i++){
            foodlist.offer(food[i]);
        }
    }
    
    /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
        @return The game's score after the move. Return -1 if game over. 
        Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        int[] pre = new int[2];
        pre[0] = snake_head[0];
        pre[1] = snake_head[1];
        snake_body.add(0, pre);
        
        if(direction.equals("U")){
            snake_head[0]--;
            if(snake_head[0] < 0){
                return -1;
            }
        }
        else if(direction.equals("D")){
            snake_head[0]++;
            if(snake_head[0] == row){
                return -1;
            }
        }
        else if(direction.equals("L")){
            snake_head[1]--;
            if(snake_head[1] < 0){
                return -1;
            }
        }
        else if(direction.equals("R")){
            snake_head[1]++;
            if(snake_head[1] == col){
                return -1;
            }
        }
        
        
        
        if(!foodlist.isEmpty()){
            int[] food = foodlist.peek();
            if(food[0] == snake_head[0] && food[1] == snake_head[1]){
                score++;
                foodlist.poll();
            }
            else{
                snake_body.remove(snake_body.size() - 1);
            }
        }
        else{
        	snake_body.remove(snake_body.size() - 1);
        }
        for(int i = 0; i < snake_body.size(); i++){
            if(snake_body.get(i)[0] == snake_head[0] && snake_body.get(i)[1] == snake_head[1]){
                return -1;
            }
        }
        
        return score;
    }
}
