
public enum BaseColor  
{
    RED("Red"), GREEN("Green"), BLUE("Blue"), WHITE("White");
    
    private String name;
    private BaseColor(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return name;
    }
    
}
