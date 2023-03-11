import greenfoot.Color;

public class RGBPalette implements Palette 
{
    private BaseColor baseColor;

    public RGBPalette(BaseColor baseColor)
    {
        this.baseColor = baseColor;
    }
    
    public Color getColor(int iterations, int maxIterations)
    {
        int intensity = (int) (255 * iterations / maxIterations);
        switch(baseColor)
        {
            case RED: return new Color(intensity, 0, 0);
            case GREEN: return new Color(0, intensity, 0);
            case BLUE: return new Color(0, 0, intensity);
            case WHITE: return new Color(intensity, intensity, intensity);
        }
        
        throw new IllegalStateException("Missing logic for " + baseColor.getName());
    }
    
    public String getName()
    {
        return baseColor.getName();
    }
}
