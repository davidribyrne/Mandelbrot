import greenfoot.GreenfootImage;
import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.Font;


public class InformationBox extends Actor
{
    private GreenfootImage background;
    private MandelbrotSet mandelbrot;
    private Font font;
    public InformationBox(MandelbrotSet mandelbrot)
    {
        this.mandelbrot = mandelbrot;
        font = new Font(false, false, 20);
        background = new GreenfootImage(330, 220);
        background.setFont(font);
        update();
        
    }
    
    public void update()
    {
        background.setColor(Color.WHITE);
        background.fill();
        background.setColor(Color.BLACK);
        background.drawString(toString(), 10, 30);
        setImage(background);
    }
    
    public String toString()
    {
        return String.format(
            "Info box can be dragged\n" +
            "Click to zoom in\n" +
            "Right-click to zoom out\n" +
            "Esc - Hide / show info box\n" +
            "Space - Change max iterations\n" +
            "Enter - Change palette\n" +
            "Current max iterations - %s\n" +
            "Current palette - %s",
            mandelbrot.getMaxIterations(), mandelbrot.getPalette().getName());
    }
    
}
