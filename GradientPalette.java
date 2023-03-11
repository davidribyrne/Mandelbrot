import greenfoot.Color;

public class GradientPalette implements Palette 
{
    private Color[] colors;
    
    public GradientPalette()
    {
        colors = new Color[16];
        colors[0] = new Color(66, 30, 15);
        colors[1] = new Color(25, 7, 26);
        colors[2] = new Color(9, 1, 47);
        colors[3] = new Color(4, 4, 73);
        colors[4] = new Color(0, 7, 100);
        colors[5] = new Color(12, 44, 138);
        colors[6] = new Color(24, 82, 177);
        colors[7] = new Color(57, 125, 209);
        colors[8] = new Color(134, 181, 229);
        colors[9] = new Color(211, 236, 248);
        colors[10] = new Color(241, 233, 191);
        colors[11] = new Color(248, 201, 95);
        colors[12] = new Color(255, 170, 0);
        colors[13] = new Color(204, 128, 0);
        colors[14] = new Color(153, 87, 0);
        colors[15] = new Color(106, 52, 3);
    }
    
    public String getName()
    {
        return "Gradient";
    }
    
    public Color getColor(int iterations, int maxIterations)
    {
        return colors[iterations % colors.length];
    }
}
