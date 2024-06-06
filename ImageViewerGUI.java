import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;

public class ImageViewerGUI extends JFrame implements ActionListener {


    // if you can't see the buttons make it bigger on your screen
    JButton selectFileButton=new JButton("Choose Image");
    JButton showImageButton=new JButton("Show Image");
    JButton resizeButton=new JButton("Resize");
    JButton grayscaleButton=new JButton("Gray Scale");
    JButton brightnessButton=new JButton("Brightness");
    JButton closeButton=new JButton("Exit");
    JButton showResizeButton=new JButton("Show Result");
    JButton showBrightnessButton=new JButton("Result");
    JButton backButton=new JButton("Back");
    JTextField widthTextField=new JTextField();
    JTextField heightTextField=new JTextField();
    JTextField brightnessTextField = new JTextField();
    String filePath = "/Users/Andishe/Download/wallpaper";
    File file;
    JFileChooser fileChooser = new JFileChooser(filePath);
    int h = 900;
    int w = 1200;
    float brightenFactor = 1;

    ImageViewerGUI(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Image Viewer");
        this.setSize(700, 300);
        this.setVisible(true);
        this.setResizable(true);

        mainPanel();
    }

    public void mainPanel(){
        // Create main panel for adding to Frame
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Create Grid panel for adding buttons to it, then add it all to main panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 2));
        selectFileButton.addActionListener(this);
        showImageButton.addActionListener(this);
        grayscaleButton.addActionListener(this);
        closeButton.addActionListener(this);
        brightnessButton.addActionListener(this);
        resizeButton.addActionListener(this);
        backButton.addActionListener(this);
        showResizeButton.addActionListener(this);
        showBrightnessButton.addActionListener(this);

        // Adding all buttons to Grid panel
        buttonsPanel.add(this.selectFileButton);
        buttonsPanel.add(this.showImageButton);
        buttonsPanel.add(this.brightnessButton);
        buttonsPanel.add(this.grayscaleButton);
        buttonsPanel.add(this.resizeButton);
        buttonsPanel.add(this.closeButton);

        // add Grid panel that contains 6 buttons to main panel
        mainPanel.add(buttonsPanel);

        // add main panel to our frame
        this.add(mainPanel);
    }

    public void resizePanel(){
        JPanel resizePanel = new JPanel();
        resizePanel.setLayout(null);
        JLabel widthlabel=new JLabel("Width:");
        widthlabel.setBounds(200,80, 100,50);
        JLabel heightLabel=new JLabel("Height:");
        heightLabel.setBounds(200,120,100,50);
        resizePanel.add(widthlabel);
        this.widthTextField.setBounds(300,80,200,38);
        resizePanel.add(this.widthTextField);
        resizePanel.add(heightLabel);
        this.heightTextField.setBounds(300,120,200,38);
        resizePanel.add(this.heightTextField);
        this.backButton.setBounds(200,160,100,40);
        this.showResizeButton.setBounds(350,160,100,40);

        resizePanel.add(this.backButton);
        resizePanel.add(this.showResizeButton);

        this.getContentPane().removeAll();
        this.add(resizePanel);
        this.repaint();
        this.revalidate();
    }
    public void brightnessPanel(){
        JPanel brightnessPanel = new JPanel();
        brightnessPanel.setSize(700 ,300);
        brightnessPanel.setLayout(null);
        JLabel label=new JLabel("Enter f(must be between 0 and 1)");
        label.setBounds(100,80, 300,50);
        brightnessTextField.setBounds(400,80,200,50);
        backButton.setBounds(115,180,100,38);
        showBrightnessButton.setBounds(475,180,100,38);
        brightnessPanel.add(label);
        brightnessPanel.add(this.brightnessTextField);
        brightnessPanel.add(this.backButton);
        brightnessPanel.add(this.showBrightnessButton);


        this.getContentPane().removeAll();
        this.add(brightnessPanel);
        this.repaint();
        this.revalidate();
    }

    public void chooseFileImage(){
        fileChooser.setAcceptAllFileFilterUsed(false);
        int option = fileChooser.showOpenDialog(ImageViewerGUI.this);
        if(option== JFileChooser.APPROVE_OPTION){
            file= fileChooser.getSelectedFile();
        }
    }
    public void showOriginalImage(){
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();
        JLabel pictureLabel =new JLabel();

        tempPanel.add(pictureLabel);
        ImageIcon imageIcon=new ImageIcon(new ImageIcon(String.valueOf(file)).getImage().getScaledInstance(600,700,Image.SCALE_DEFAULT));
        pictureLabel.setIcon(imageIcon);

        tempFrame.setLayout(new BorderLayout());
        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }

    public void grayScaleImage() throws IOException {
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();
        JLabel pictureLabel=new JLabel();


        file = fileChooser.getSelectedFile();
        BufferedImage bufferedImage= ImageIO.read(file);
        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        ColorConvertOp op = new ColorConvertOp(cs, null);
        BufferedImage image = op.filter(bufferedImage, null);
        ImageIcon imageIcon=new ImageIcon(image);
        pictureLabel.setIcon(imageIcon);


        tempPanel.add(pictureLabel);
        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }
    public void showResizeImage(int w, int h) throws IOException {
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();
        JLabel pictureLabel=new JLabel();
        file = fileChooser.getSelectedFile();
        BufferedImage bufferedImage= ImageIO.read(file);
        Image image=bufferedImage.getScaledInstance(w,h,Image.SCALE_DEFAULT);
        ImageIcon imageIcon=new ImageIcon(image);
        pictureLabel.setIcon(imageIcon);





        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
        tempPanel.add(pictureLabel);
    }
    public void showBrightnessImage(float f) throws IOException {
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();
        JLabel pictureLabel=new JLabel();
        file = fileChooser.getSelectedFile();
        BufferedImage bufferedImage= ImageIO.read(file);
        RescaleOp op = new RescaleOp(f, 0, null);
        bufferedImage = op.filter(bufferedImage, bufferedImage);
        ImageIcon imageIcon=new ImageIcon(bufferedImage);
        pictureLabel.setIcon(imageIcon);

        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
        tempPanel.add(pictureLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==resizeButton){
            resizePanel();

        }else if(e.getSource()== showImageButton){
            showOriginalImage();


        }else if(e.getSource()==grayscaleButton){
            try {
                grayScaleImage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }else if(e.getSource()== showResizeButton){
            w=Integer.parseInt(widthTextField.getText());
            h=Integer.parseInt(heightTextField.getText());
            try {
                showResizeImage(w,h);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }else if(e.getSource()==brightnessButton){
            brightnessPanel();

        }else if(e.getSource()== showBrightnessButton){
            brightenFactor =Float.parseFloat(brightnessTextField.getText());
            try {
                showBrightnessImage(brightenFactor);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }else if(e.getSource()== selectFileButton){
            chooseFileImage();

        }else if(e.getSource()==closeButton){
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
        else if(e.getSource()==backButton){
            this.getContentPane().removeAll();
            this.mainPanel();
            this.revalidate();
            this.repaint();
        }
    }
}
