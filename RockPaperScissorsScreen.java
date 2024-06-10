import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RockPaperScissorsScreen implements ActionListener {

    private JFrame frame;
    private JLabel titleLabel, instructLabel, instruct2Label, userLabel, cpuLabel, userWinsLabel, cpuWinsLabel, drawsLabel;
    private JButton rockButton, paperButton, scissorsButton;
    private int userWins = 0, cpuWins = 0, draws = 0;

    public RockPaperScissorsScreen(String apiKey) {
        RockPaperScissors rpsGame = new RockPaperScissors(apiKey);
		
		// Create the main frame
        frame = new JFrame("Rock Paper Scissors Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 700);
        frame.setLayout(new BorderLayout());

        // Create the top panel with a title and instructions
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(45, 45, 45));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        titleLabel = new JLabel("Rock Paper Scissors");
        titleLabel.setFont(new Font("Sans Serif", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        instructLabel = new JLabel("Play Rock, Paper, and Scissors Against the Powerful AI!");
        instructLabel.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        instructLabel.setForeground(Color.LIGHT_GRAY);
        instructLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        instruct2Label = new JLabel("First to 10 wins! Good Luck!");
        instruct2Label.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        instruct2Label.setForeground(Color.LIGHT_GRAY);
        instruct2Label.setAlignmentX(Component.CENTER_ALIGNMENT);

        topPanel.add(titleLabel);
        topPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        topPanel.add(instructLabel);
        topPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        topPanel.add(instruct2Label);

        frame.add(topPanel, BorderLayout.NORTH);

        // Create the center panel with user and AI selections and the score
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(50, 50, 50));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        userLabel = new JLabel("Your Selection: ");
        userLabel.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        userLabel.setForeground(Color.WHITE);
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        cpuLabel = new JLabel("AI Selection: ");
        cpuLabel.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        cpuLabel.setForeground(Color.WHITE);
        cpuLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        userWinsLabel = new JLabel("Your Wins: " + userWins);
        userWinsLabel.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        userWinsLabel.setForeground(Color.WHITE);
        userWinsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        cpuWinsLabel = new JLabel("AI Wins: " + cpuWins);
        cpuWinsLabel.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        cpuWinsLabel.setForeground(Color.WHITE);
        cpuWinsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        drawsLabel = new JLabel("Draws: " + draws);
        drawsLabel.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        drawsLabel.setForeground(Color.WHITE);
        drawsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(userLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(cpuLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        centerPanel.add(userWinsLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(cpuWinsLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(drawsLabel);

        frame.add(centerPanel, BorderLayout.CENTER);

        // Create the bottom panel with buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3, 20, 20));
        bottomPanel.setBackground(new Color(45, 45, 45));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        rockButton = new JButton("Rock");
        rockButton.setFont(new Font("Sans Serif", Font.BOLD, 18));
        rockButton.setBackground(new Color(210, 50, 45));
        rockButton.setForeground(Color.BLACK);
        rockButton.addActionListener(this);
        rockButton.setFocusPainted(false);
        bottomPanel.add(rockButton);

        paperButton = new JButton("Paper");
        paperButton.setFont(new Font("Sans Serif", Font.BOLD, 18));
        paperButton.setBackground(new Color(45, 210, 50));
        paperButton.setForeground(Color.BLACK);
        paperButton.addActionListener(this);
        paperButton.setFocusPainted(false);
        bottomPanel.add(paperButton);

        scissorsButton = new JButton("Scissors");
        scissorsButton.setFont(new Font("Sans Serif", Font.BOLD, 18));
        scissorsButton.setBackground(new Color(50, 50, 210));
        scissorsButton.setForeground(Color.BLACK);
        scissorsButton.addActionListener(this);
        scissorsButton.setFocusPainted(false);
        bottomPanel.add(scissorsButton);

        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		String userSelection = "";
		if (e.getSource() == rockButton) {
			userSelection = "Rock";
		} else if (e.getSource() == paperButton) {
			userSelection = "Paper";
		} else if (e.getSource() == scissorsButton) {
			userSelection = "Scissors";
		}
	
		int cpuSelection = RockPaperScissors.getComputerThrow();
	
		userLabel.setText("Your Selection: " + userSelection);
		cpuLabel.setText("AI Selection: " + RockPaperScissors.getThrowName(cpuSelection));
	
		String result;
		if (userSelection.equals(RockPaperScissors.getThrowName(cpuSelection))) {
			result = "It's a draw!";
			draws++;
		} else if ((userSelection.equals("Rock") && cpuSelection == RockPaperScissors.SCISSORS) ||
				   (userSelection.equals("Paper") && cpuSelection == RockPaperScissors.ROCK) ||
				   (userSelection.equals("Scissors") && cpuSelection == RockPaperScissors.PAPER)) {
			result = "Player wins!";
			userWins++;
		} else {
			result = "Computer wins!";
			cpuWins++;
		}
	
		userWinsLabel.setText("Your Wins: " + userWins);
		cpuWinsLabel.setText("AI Wins: " + cpuWins);
		drawsLabel.setText("Draws: " + draws);
	
		JOptionPane.showMessageDialog(frame, result);
	
		if (userWins == 10) {
			JOptionPane.showMessageDialog(frame, "Congratulations! You won the game!");
			resetGame();
		} else if (cpuWins == 10) {
			JOptionPane.showMessageDialog(frame, "AI wins the game! Better luck next time.");
			resetGame();
		}
	}
	

    private void resetGame() {
        userWins = 0;
        cpuWins = 0;
        draws = 0;
        userWinsLabel.setText("Your Wins: " + userWins);
        cpuWinsLabel.setText("AI Wins: " + cpuWins);
        drawsLabel.setText("Draws: " + draws);
        userLabel.setText("Your Selection: ");
        cpuLabel.setText("AI Selection: ");
    }
}


