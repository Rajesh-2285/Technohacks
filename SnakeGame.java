import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class SnakeGame extends JFrame implements KeyListener {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private static final int CELL_SIZE = 20;
    private static final int INITIAL_DELAY = 200;
    private static final int MIN_DELAY = 50;

    private ArrayList<Point> snake;
    private Point food;
    private char direction;
    private boolean gameOver;
    private Timer timer;
    private int score;
    private JLabel scoreLabel;

    public SnakeGame() {
        setTitle("Snake Game");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        addKeyListener(this);

        snake = new ArrayList<>();
        snake.add(new Point(10, 15));
        direction = 'R';
        spawnFood();
        score = 0;

        timer = new Timer(INITIAL_DELAY, new ActionListener() {
                      public void actionPerformed(ActionEvent e) {
                if (!gameOver) {
                    move();
                    checkCollision();
                    updateScore();
                    repaint();
                }
            }
        });
        timer.start();

        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(scoreLabel, BorderLayout.NORTH);
    }

    private void updateScore() {
        scoreLabel.setText("Score: " + score);
    }

    public void paint(Graphics g) {
        super.paint(g);
        draw(g);
    }

    private void draw(Graphics g) {
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // Draw snake
        g.setColor(Color.GREEN);
        for (Point p : snake) {
            g.fillRect(p.x * CELL_SIZE, p.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }

        // Draw food
        g.setColor(Color.RED);
        g.fillRect(food.x * CELL_SIZE, food.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);

        // Draw game over text
        if (gameOver) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Game Over!", WIDTH / 2 - 100, HEIGHT / 2);
            g.drawString("Score: " + score, WIDTH / 2 - 60, HEIGHT / 2 + 40);
        }
    }

    private void move() {
        Point head = snake.get(0);
        Point newHead = new Point(head);

        switch (direction) {
            case 'U':
                newHead.y--;
                break;
            case 'D':
                newHead.y++;
                break;
            case 'L':
                newHead.x--;
                break;
            case 'R':
                newHead.x++;
                break;
        }

        snake.add(0, newHead);

        if (!newHead.equals(food)) {
            snake.remove(snake.size() - 1);
        } else {
            if (score % 50 == 0 && score > 0) { // Bonus food after every 50 points
                spawnBonusFood();
            } else {
                spawnFood();
            }
            score += 10; // Increase score when food is eaten
            int newDelay = Math.max(timer.getDelay() - 10, MIN_DELAY); // Make the game slightly faster
            timer.setDelay(newDelay);
        }
    }

    private void spawnFood() {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(WIDTH / CELL_SIZE);
            y = rand.nextInt(HEIGHT / CELL_SIZE);
        } while (snake.contains(new Point(x, y)));
        food = new Point(x, y);
    }

    private void spawnBonusFood() {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(WIDTH / CELL_SIZE);
            y = rand.nextInt(HEIGHT / CELL_SIZE);
        } while (snake.contains(new Point(x, y)) || food.equals(new Point(x, y))); // Ensure bonus food does not overlap with snake or existing food
        food = new Point(x, y);
    }

    private void checkCollision() {
        Point head = snake.get(0);

        // Check if snake hits wall
        if (head.x < 0 || head.x >= WIDTH / CELL_SIZE || head.y < 0 || head.y >= HEIGHT / CELL_SIZE) {
            gameOver = true;
            timer.stop();
            return;
        }

        // Check if snake hits itself
        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                gameOver = true;
                timer.stop();
                return;
            }
        }
    }

  
    public void keyTyped(KeyEvent e) {}

    
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_UP:
                if (direction != 'D')
                    direction = 'U';
                break;
            case KeyEvent.VK_DOWN:
                if (direction != 'U')
                    direction = 'D';
                break;
            case KeyEvent.VK_LEFT:
                if (direction != 'R')
                    direction = 'L';
                break;
            case KeyEvent.VK_RIGHT:
                if (direction != 'L')
                    direction = 'R';
                break;
        }
    }


    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SnakeGame().setVisible(true);
            }
        });
    }
}
