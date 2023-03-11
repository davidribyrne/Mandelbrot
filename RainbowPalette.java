import greenfoot.Color;

public class RainbowPalette implements Palette 
{
    private Color[] colors;
    private int positions;
    
    public RainbowPalette()
    {
        positions = 30;
        generateColors();
    }
    
    public String getName()
    {
        return "Rainbow";
    }
    
    public Color getColor(int iterations, int maxIterations)
    {
        return colors[iterations % positions];
    }
    
    protected void generateColors()
    {
        int increment = 256 * 6 / positions;
        colors = new Color[positions];
        int position = 0;
        for (int i = 0; i < positions; i++)
        {
            double degrees = 360 * i / positions;
            if (degrees < 60)
            {
                // 255, 0 -> 255, 0
                colors[i] = new Color(255, (int) (255 * (degrees / 60)), 0);
            }
            else if (degrees < 120)
            {   
                // 255 -> 0, 255, 0
                colors[i] = new Color((int) (255 * (2 - (degrees / 60))), 255, 0);
            }
            else if (degrees < 180)
            {  
                // 0, 255, 0 -> 255
                colors[i] = new Color(0, 255, (int) (255 * ((degrees / 60) - 2)));
            }
            else if (degrees < 240)
            {
                // 0, 255 -> 0, 255
                colors[i] = new Color(0, (int) (255 * (4 - (degrees / 60))), 255);
            }
            else if (degrees < 300)
            {
                // 0 -> 255, 0, 255
                colors[i] = new Color((int) (255 * ((degrees / 60) - 4)), 255, 0);
            }
            else if (degrees < 360)
            {
                // 255, 0, 255 -> 0
                colors[i] = new Color((int) (255 * (6 - (degrees / 60))), 255, 0);
            }
            else
            {
                throw new IllegalStateException("The degrees should always be less than 360");
            }
        }
    }
}
