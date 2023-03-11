import greenfoot.World;
import greenfoot.GreenfootImage;
import greenfoot.Color;

public class MandelbrotSet extends GreenfootImage
{
    private double xMin, yMin, xMax, yMax;
    private int maxIterations;
    private double increment;
    private int[][] iterationCounts;
    private int highestIteration;
    private Palette palette;
    private Color innerColor;

    public MandelbrotSet(World parent, int maxIterations, Palette palette, double xMin, double xMax, double yMin)
    {
        super(parent.getWidth(), parent.getHeight());
        this.maxIterations = maxIterations;
        this.palette = palette;
        innerColor = Color.BLACK;
        recenter(xMin, xMax, yMin); 
    }
    

    private void recenter(double xMin, double xMax, double yMin)
    {
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        increment = (xMax - xMin) / this.getWidth();
        this.yMax = yMin + this.getHeight() * increment;
        displaySet();
    }
    
    public String toString()
    {
        return "xMin - " + xMin + "\n" +
                "xMax - " + xMax + "\n" +
                "yMin - " + yMin + "\n" +
                "yMax - " + yMax + "\n" +
                "increment - " + increment + "\n";
    }
    
    public void zoom(int xPixel, int yPixel, double factor)
    {
        // We want to keep the clicked pixel's coordinates the same
        Coordinate location = getCoordinates(xPixel, yPixel);

        double newWidth = (xMax - xMin) * factor;
        double newHeight = (yMax - yMin) * factor;
        
        double leftMargin = location.x - xMin;
        double topMargin = location.y - yMin;
        
        double newLeftMargin = leftMargin * factor;
        double newTopMargin = topMargin * factor;
        
        double newXMin = location.x - newLeftMargin;
        double newXMax = newXMin + newWidth;
        
        double newYMin = location.y - newTopMargin;
        
        recenter(newXMin, newXMax, newYMin);
    }
    
    
    private Coordinate getCoordinates(int xPixel, int yPixel)
    {
        double x = xPixel * increment + xMin;
        double y = yPixel * increment + yMin;
        return new Coordinate(x, y);
    }
    
    
    private void calculateSet()
    {
        iterationCounts = new int[this.getWidth()][this.getHeight()];
        highestIteration = 0;
        for (int xPixel = 0; xPixel < this.getWidth(); xPixel++)
        {
            double x = xPixel * increment + xMin;
            for (int yPixel = 0; yPixel < this.getHeight(); yPixel++)
            {
                double y = yPixel * increment + yMin;
                int iterations = calculatePoint(x, y);
                iterationCounts [xPixel][yPixel] = iterations;
                if (iterations > highestIteration)
                {
                    highestIteration = iterations;
                }
            }
        }        
    }
    
    
    private int[] calculateHistogram()
    {
        int[] histogram = new int[highestIteration + 1];
        int total = 0;
        for (int xPixel = 0; xPixel < this.getWidth(); xPixel++) 
        {
            for (int yPixel = 0; yPixel < this.getHeight(); yPixel++)
            {
                int iterations = iterationCounts[xPixel][yPixel];
                histogram[iterations]++;
                total += iterations;
            }
        }
        
        return histogram;
    }
    
    
    private void displaySet()
    {
        calculateSet();
        setColors();
    }
    

    private void setColors()
    {
        int[][] hue = new int[this.getWidth()][this.getHeight()];
        for (int xPixel = 0; xPixel < this.getWidth(); xPixel++) 
        {
            for (int yPixel = 0; yPixel < this.getHeight(); yPixel++)
            {
                Color color;
                int iterations = iterationCounts[xPixel][yPixel];
                if (iterations == maxIterations)
                {
                    color = innerColor;
                }
                else
                {
                    color = palette.getColor(iterations, maxIterations);
                }
                this.setColorAt(xPixel, yPixel, color);
            }
        }
    }
    
    
    private int calculatePoint(double x, double y)
    {
        double x2 = 0;
        double y2 = 0;
        int iteration = 0;
        while (x2 * x2 + y2 * y2 < 4 && iteration < maxIterations)
        {
            double xTemp = x2 * x2 - y2 * y2 + x;
            y2 = 2 * x2 * y2 + y;
            x2 = xTemp;
            iteration ++;
        }
        return iteration;
    }
    
    
    public int getMaxIterations()
    {
        return maxIterations;
    }
    
    public void setMaxIterations(int maxIterations)
    {
        this.maxIterations = maxIterations;
        displaySet();
    }
    
    public Palette getPalette()
    {
        return palette;
    }
    
    public void setPalette(Palette palette)
    {
        this.palette = palette;
        setColors();
    }
}

