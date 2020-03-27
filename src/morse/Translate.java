package morse;

import javax.swing.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.stream.Stream;



public class Translate {
    private HashMap<String, String> engMors=new HashMap<>(), morseEng=new HashMap<>();

    {
        engMors.put("A", ".-");
        engMors.put("B", "-...");
        engMors.put("C", "-.-.");
        engMors.put("D", "-..");
        engMors.put("E", ".");
        engMors.put("F", "..-.");
        engMors.put("G", "--.");
        engMors.put("H", "....");
        engMors.put("I", "..");
        engMors.put("J", ".---");
        engMors.put("K", "-.-");
        engMors.put("L", ".-..");
        engMors.put("M", "--");
        engMors.put("N", "-.");
        engMors.put("O", "---");
        engMors.put("P", ".--.");
        engMors.put("Q", "--.-");
        engMors.put("R", ".-.");
        engMors.put("S", "...");
        engMors.put("T", "-");
        engMors.put("U", "..-");
        engMors.put("V", "...-");
        engMors.put("W", ".--");
        engMors.put("X", "-..-");
        engMors.put("Y", "-.--");
        engMors.put("Z", "--..");

        engMors.put("0", "-----");
        engMors.put("1", ".----");
        engMors.put("2", "..---");
        engMors.put("3", "...--");
        engMors.put("4", "....-");
        engMors.put("5", ".....");
        engMors.put("6", "-....");
        engMors.put("7", "--...");
        engMors.put("8", "---..");
        engMors.put("9", "----.");

        engMors.put(".", ".-.-.-");
        engMors.put(",", "--..--");
        engMors.put("?", "..--..");
        engMors.put(":", "---...");
        engMors.put("-", "-....-");
        engMors.put("@", ".--.-.");
        engMors.put("error", "........");

        List<Object> val = Arrays.asList(engMors.values().toArray());
        List<Object> key = Arrays.asList(engMors.keySet().toArray());
        for (int q = 0; q < val.size(); q++) {
            morseEng.put(val.get(q).toString(), key.get(q).toString());
        }
    }

    private String info = "Morse Translator";

    public void translator() {
        JTextArea englishTextArea = new JTextArea(20,20);
            englishTextArea.setText("Hello World");
            englishTextArea.setLineWrap(true);
            englishTextArea.setWrapStyleWord(true);
            englishTextArea.setMargin(new Insets(5, 5, 5,5));
        JLabel englishTextLabel = new JLabel("English Text");
            englishTextLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton clearEnglishText = new JButton("<< Clear Text");
        JButton englishToMorseBt = new JButton("English >> Morse");

        JPanel englishControlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            englishControlPanel.add(clearEnglishText);
            englishControlPanel.add(englishToMorseBt);


        JPanel englishTextPanel = new JPanel();
            englishTextPanel.setLayout(new BorderLayout());
            englishTextPanel.add(englishTextLabel, BorderLayout.NORTH);
            englishTextPanel.add(new JScrollPane(englishTextArea), BorderLayout.CENTER);
            englishTextPanel.add(englishControlPanel, BorderLayout.SOUTH);

        JTextArea morseTextArea = new JTextArea();
            morseTextArea.setText(".-");
            morseTextArea.setLineWrap(true);
            morseTextArea.setWrapStyleWord(true);
            morseTextArea.setLineWrap(true);
            morseTextArea.setMargin(new Insets(5, 5, 5,5));
            morseTextArea.setFont(new Font("", 0, 20));

        JLabel morseTextLabel = new JLabel("Morse Code");
            morseTextLabel.setHorizontalAlignment(SwingConstants.CENTER);


        JButton morseToEnglishBt = new JButton("Morse >> English");
        JButton clearMorseText = new JButton("Clear Text >>");

        JPanel morseControlPanel = new JPanel();
            morseControlPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            morseControlPanel.add(morseToEnglishBt);
            morseControlPanel.add(clearMorseText);

        JPanel morseTextPanel = new JPanel();
            morseTextPanel.setLayout(new BorderLayout());
            morseTextPanel.add(morseTextLabel, BorderLayout.NORTH);
            morseTextPanel.add(new JScrollPane(morseTextArea), BorderLayout.CENTER);
            morseTextPanel.add(morseControlPanel, BorderLayout.SOUTH);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, englishTextPanel, morseTextPanel);
            splitPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JTextArea infoTextArea = new JTextArea();
            infoTextArea.setLineWrap(true);
            infoTextArea.setWrapStyleWord(true);
            infoTextArea.setText(info);
            infoTextArea.setBackground(new Color(241,241,241));
            infoTextArea.setEditable(false);
            infoTextArea.setMargin(new Insets(5, 5, 5,5));


        JPanel infoPanel = new JPanel(new BorderLayout());
            infoPanel.add(infoTextArea, BorderLayout.CENTER);


        JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.add(infoPanel, BorderLayout.NORTH);
            mainPanel.add(splitPane, BorderLayout.CENTER);

        morseToEnglishBt.addActionListener((w) -> {
            String morse = morseTextArea.getText().trim();
            englishTextArea.setText(morse_english(morse));
        });
        englishToMorseBt.addActionListener((e) -> {
            String english = englishTextArea.getText().trim();
            morseTextArea.setText(english_morse(english));
        });

        englishTextArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent r) {
                if(Character.isWhitespace(r.getKeyChar()) || r.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    morseTextArea.setText(english_morse(englishTextArea.getText()));
                }
            }
        });

        morseTextArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent t) {
                if(Character.isWhitespace(t.getKeyChar()) || t.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    englishTextArea.setText(morse_english(morseTextArea.getText()));
                }
            }
        });

        clearEnglishText.addActionListener((y) -> {
            englishTextArea.setText(null);
        });
        clearMorseText.addActionListener((u) -> {
            morseTextArea.setText(null);
        });

        JFrame frame = new JFrame();
        frame.setTitle("Morse Translator");
        frame.setLayout(new BorderLayout());
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setSize(new Dimension(800, 650));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        splitPane.setDividerLocation(frame.getWidth() / 2);
        englishToMorseBt.doClick();
    }

    public String morse_english(String i) {
        StringBuffer o = new StringBuffer();
        Stream.of(i.split("[ \n]")).forEach(p -> {
            for(char a: p.toCharArray()) {
                o.append(engMors.containsKey(String.valueOf(a).toUpperCase()) ? engMors.get(String.valueOf(a).toUpperCase()) + " " : "? ");
            }
            o.append(" / ");
        });
        return o.toString();
    }

    public String english_morse(String s) {
        StringBuffer d = new StringBuffer();
        Stream.of(s.split("[\\s\\n]")).filter(f -> f != null && !f.isEmpty()).forEach(g -> {
            if (g.equalsIgnoreCase("/") || g.equalsIgnoreCase("|")) {
                d.append(" ");
            }
            else {d.append((morseEng.containsKey(g) ? morseEng.get(g) : "? ").toLowerCase());}
        });
        return d.toString();
    }
}