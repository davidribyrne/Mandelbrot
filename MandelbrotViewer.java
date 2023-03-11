import greenfoot.*;

public class MandelbrotViewer extends World
{
    private MandelbrotSet mSet;
    private boolean dragging;
    private int dragStartX, dragStartY;
    private InformationBox info;
    private int infoX;
    private int infoY;
    private int keyCountdown = 0;
    private static final int KEY_DELAY = 300000;
    
    public MandelbrotViewer()
    {    
        super(1000, 750, 1); 
        mSet = new MandelbrotSet(this, 100, new RainbowPalette(), -2, 1, -1);
        info = new InformationBox(mSet);
        setBackground(mSet);
        addObject(info, 200, 150);
        Greenfoot.start();
    }
  
    public void act()
    {
        if (keyCountdown > 0)
        {
            keyCountdown--;
        }
        else
        {
            handleKeyboard();
        }
        
        if (Greenfoot.mouseDragged(info))
        {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            info.setLocation(mouse.getX(), mouse.getY());
        }
        if (Greenfoot.mousePressed(this))
        {
            handleMouseClick();
        }
    }
    
    private void handleKeyboard()
    {
        if (Greenfoot.isKeyDown("escape"))
        {
            if (getObjects(InformationBox.class).contains(info))
            {
                infoX = info.getX();
                infoY = info.getY();
                removeObject(info);
            }
            else
            {
                addObject(info, infoX, infoY);
            }
            keyCountdown = KEY_DELAY;
        }
        
        
        if (Greenfoot.isKeyDown("space"))
        {
            promptMaxIterations();
        }   

        if (Greenfoot.isKeyDown("enter"))
        {
            promptColorPalette();
        }   
    }
    
    
    private void handleMouseClick()
    {
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();
        
        int mouseX = mouseInfo.getX();
        int mouseY = mouseInfo.getY();
        double factor;
        if (mouseInfo.getButton() == 1)
        {
            factor = 0.8;
        }
        else if (mouseInfo.getButton() == 3)
        {
            factor = 1.25;
        }
        else
        {
            return;
        }
        mSet.zoom(mouseX, mouseY, factor);
    }
    
    private void promptColorPalette()
    {
        String error = "";
        Palette newPalette = null;
        while (newPalette == null)
        {
            String answer = Greenfoot.ask(error + 
                "Enter new palette number:\n" +
                "1 - Rainbow\n" +
                "2 - Gradient\n" +
                "3 - Red\n" +
                "4 - Green\n" +
                "5 - Blue\n" +
                "6 - White");
                
            switch(answer)
            {
                case "1" : newPalette = new RainbowPalette(); break;
                case "2" : newPalette = new GradientPalette(); break;
                case "3" : newPalette = new RGBPalette(BaseColor.RED); break;
                case "4" : newPalette = new RGBPalette(BaseColor.GREEN); break;
                case "5" : newPalette = new RGBPalette(BaseColor.BLUE); break;
                case "6" : newPalette = new RGBPalette(BaseColor.WHITE); break;
            }
        } 
        mSet.setPalette(newPalette);
        info.update();
    }
    
    private void promptMaxIterations()
    {
        int newMax = -1;
        String error = "";
        while (newMax < 0)
        {
            String answer = Greenfoot.ask(error + "New max iterations?");
            try
            {
                newMax = Integer.parseInt(answer);
                if (newMax < 2)
                {
                    error = "Max iterations must be at least 2. ";
                    newMax = -1;
                }
            }
            catch (NumberFormatException nfe)
            {
                error = "That wasn't a valid integer. ";
            }
        } 
        mSet.setMaxIterations(newMax);
        info.update();
    }
}
